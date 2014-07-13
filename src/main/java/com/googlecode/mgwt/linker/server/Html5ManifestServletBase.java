package com.googlecode.mgwt.linker.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.mgwt.linker.linker.ManifestWriter;
import com.googlecode.mgwt.linker.linker.PermutationMapLinker;
import com.googlecode.mgwt.linker.linker.XMLPermutationProvider;
import com.googlecode.mgwt.linker.linker.XMLPermutationProviderException;
import com.googlecode.mgwt.linker.server.propertyprovider.MgwtOsPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;

public class Html5ManifestServletBase extends HttpServlet {

  private static final long serialVersionUID = -2540671294104865306L;
  private XMLPermutationProvider permutationProvider;

  private Map<String, PropertyProvider> propertyProviders = new HashMap<String, PropertyProvider>();

  public Html5ManifestServletBase() {
    permutationProvider = new XMLPermutationProvider();
  }

  protected void addPropertyProvider(PropertyProvider propertyProvider) {
    propertyProviders.put(propertyProvider.getPropertyName(), propertyProvider);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String moduleName = getModuleName(req);

    String baseUrl = getBaseUrl(req);

    Set<BindingProperty> computedBindings = calculateBindinPropertiesForClient(req);

    String strongName = getPermutationStrongName(baseUrl, moduleName, computedBindings);

    if (strongName != null) {
      String manifest = readManifest(baseUrl + moduleName + "/" + strongName + PermutationMapLinker.PERMUTATION_MANIFEST_FILE_ENDING);
      serveStringManifest(req, resp, manifest);
      return;
    }

    boolean isIPhoneWithoutCookie = isIphoneWithoutCookie(computedBindings);
    boolean isIPadWithoutCookie = isIpadWithoutCookie(computedBindings);

    if (isIPhoneWithoutCookie || isIPadWithoutCookie) {
      Set<BindingProperty> nonRetinaMatch = new HashSet<BindingProperty>();
      Set<BindingProperty> retinaMatch = new HashSet<BindingProperty>();

      if (isIPhoneWithoutCookie) {
        computedBindings.remove(MgwtOsPropertyProvider.iPhone_undefined);
        nonRetinaMatch.add(MgwtOsPropertyProvider.iPhone);
        retinaMatch.add(MgwtOsPropertyProvider.retina);
      }

      if (isIPadWithoutCookie) {
        computedBindings.remove(MgwtOsPropertyProvider.iPad_undefined);
        nonRetinaMatch.add(MgwtOsPropertyProvider.iPad);
        retinaMatch.add(MgwtOsPropertyProvider.iPad_retina);
      }

      nonRetinaMatch.addAll(computedBindings);
      retinaMatch.addAll(computedBindings);

      String moduleNameNonRetina = getPermutationStrongName(baseUrl, moduleName, nonRetinaMatch);
      String moduleNameRetina = getPermutationStrongName(baseUrl, moduleName, retinaMatch);

      if (moduleNameNonRetina != null && moduleNameRetina != null) {

        // load files for both permutations
        Set<String> filesForPermutation = getFilesForPermutation(baseUrl, moduleName, moduleNameNonRetina);
        filesForPermutation.addAll(getFilesForPermutation(baseUrl, moduleName, moduleNameRetina));

        // dynamically write a new manifest..
        ManifestWriter manifestWriter = new ManifestWriter();
        String writeManifest = manifestWriter.writeManifest(new HashSet<String>(), filesForPermutation);
        serveStringManifest(req, resp, writeManifest);
        return;
      }
    }

    // if we got here we just don`t know the device react with 500 -> no
    // manifest...
    throw new ServletException("unkown device");
  }

  protected String getBaseUrl(HttpServletRequest req) {
    String base = req.getServletPath();
    // cut off module
    return base.substring(0, base.lastIndexOf("/") + 1);
  }

  public boolean isIphoneWithoutCookie(Set<BindingProperty> bps) {
    for (BindingProperty bp : bps) {
      if ("mgwt.os".equals(bp.getName())) {
        if ("iphone_undefined".equals(bp.getValue())) {
          // oh shit this is an iphone
          // so now we need to serve two manifests
          // retina...
          // non retina

          return true;

        }
      }
    }
    return false;
  }

  public boolean isIpadWithoutCookie(Set<BindingProperty> bps) {
    for (BindingProperty bp : bps) {
      if ("mgwt.os".equals(bp.getName())) {
        if ("ipad_undefined".equals(bp.getValue())) {
          // oh shit this is an ipad
          // so now we need to serve two manifests
          // retina...
          // non retina
          return true;
        }
      }
    }
    return false;
  }

  public Set<String> getFilesForPermutation(String baseUrl, String moduleName, String permutation) throws ServletException {
    String fileName = baseUrl + moduleName + "/" + permutation + PermutationMapLinker.PERMUTATION_FILE_ENDING;
    XMLPermutationProvider xmlPermutationProvider = new XMLPermutationProvider();

    InputStream inputStream = null;
    try {
      File file = new File(getServletContext().getRealPath(fileName));
      inputStream = new FileInputStream(file);
      return xmlPermutationProvider.getPermutationFiles(inputStream);
    } catch (XMLPermutationProviderException e) {
      log("can not read permutation file");
      throw new ServletException("can not read permutation file", e);
    } catch (FileNotFoundException e) {
      log("can not read permutation file");
      throw new ServletException("can not read permutation file", e);
    } finally {
      closeQuitly(inputStream);
    }
  }

  public String readManifest(String filePath) throws ServletException {
    File manifestFile = new File(getServletContext().getRealPath(filePath));

    StringWriter manifestWriter = new StringWriter();
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(manifestFile), "UTF-8"));

      String line = null;

      while ((line = br.readLine()) != null) {
        manifestWriter.append(line + "\n");
      }

      return manifestWriter.toString();
    } catch (FileNotFoundException e) {
      log("could not find manifest file", e);
      throw new ServletException("can not find manifest file", e);
    } catch (IOException e) {
      log("error while reading manifest file", e);
      throw new ServletException("error while reading manifest file", e);
    } finally {
      closeQuitly(br);
    }
  }

  /**
   * @param req
   * @return
   * @throws PropertyProviderException
   */
  public Set<BindingProperty> calculateBindinPropertiesForClient(HttpServletRequest req) throws ServletException {

    try {
      Set<BindingProperty> computedBindings = new HashSet<BindingProperty>();

      Set<Entry<String, PropertyProvider>> set = propertyProviders.entrySet();
      for (Entry<String, PropertyProvider> entry : set) {
        String varValue = entry.getValue().getPropertyValue(req);
        BindingProperty bindingProperty = new BindingProperty(entry.getKey(), varValue);
        computedBindings.add(bindingProperty);
      }
      return computedBindings;
    } catch (PropertyProviderException e) {
      log("cam not calculate properties for client", e);
      throw new ServletException("can not calculate properties for client", e);
    }
  }

  public void serveStringManifest(HttpServletRequest req, HttpServletResponse resp, String manifest) throws ServletException {
    resp.setHeader("Cache-Control", "no-cache");
    resp.setHeader("Pragma", "no-cache");
    resp.setDateHeader("Expires", new Date().getTime());

    resp.setContentType("text/cache-manifest");

    try {
      InputStream is = new ByteArrayInputStream(manifest.getBytes("UTF-8"));
      ServletOutputStream os = resp.getOutputStream();
      byte[] buffer = new byte[1024];
      int bytesRead;

      while ((bytesRead = is.read(buffer)) != -1) {
        os.write(buffer, 0, bytesRead);
      }
      return;
    } catch (UnsupportedEncodingException e) {
      log("can not write manifest to output stream", e);
      throw new ServletException("can not write manifest to output stream", e);
    } catch (IOException e) {
      log("can not write manifest to output stream", e);
      throw new ServletException("can not write manifest to output stream", e);
    }
  }

  public String getPermutationStrongName(String baseUrl, String moduleName, Set<BindingProperty> computedBindings) throws ServletException {

    if (moduleName == null) {
      throw new IllegalArgumentException("moduleName can not be null");
    }

    if (computedBindings == null) {
      throw new IllegalArgumentException("computedBindings can not be null");
    }

    String realPath = getServletContext().getRealPath(baseUrl + moduleName + "/" + PermutationMapLinker.MANIFEST_MAP_FILE_NAME);

    FileInputStream fileInputStream = null;
    try {

      fileInputStream = new FileInputStream(realPath);

      Map<String, List<BindingProperty>> map = permutationProvider.getBindingProperties(fileInputStream);
      for (Entry<String, List<BindingProperty>> entry : map.entrySet()) {
        List<BindingProperty> value = entry.getValue();
        if (value.containsAll(computedBindings) && value.size() == computedBindings.size()) {
          return entry.getKey();
        }
      }
      return null;
    } catch (FileNotFoundException e) {
      log("can not find file: '" + realPath + "'", e);
      throw new ServletException("can not find permutation file", e);
    } catch (XMLPermutationProviderException e) {
      log("can not read xml file", e);
      throw new ServletException("can not read permutation information", e);
    } finally {
      closeQuitly(fileInputStream);
    }
  }

  public String getModuleName(HttpServletRequest req) throws ServletException {
    if (req == null) {
      throw new IllegalArgumentException("reqeust can not be null");
    }

    // request url should be something like .../modulename.manifest" within
    // the same folder of your host page...
    Pattern pattern = Pattern.compile("/([a-zA-Z0-9_]+)\\.manifest$");
    Matcher matcher = pattern.matcher(req.getServletPath());
    if (!matcher.find()) {
      log("can not calculate module base from url: '" + req.getServletPath() + "'");
      throw new ServletException("can not calculate module base from url: '" + req.getServletPath() + "'");
    }

    String module = matcher.group(1);
    return module;
  }

  private void closeQuitly(BufferedReader br) {
    if (br != null) {
      try {
        br.close();
      } catch (IOException ignored) {
      }
    }
  }

  private void closeQuitly(InputStream stream) {
    if (stream != null) {
      try {
        stream.close();
      } catch (IOException ignored) {
      }
    }
  }
}
