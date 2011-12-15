package com.googlecode.mgwt.linker.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.mgwt.linker.linker.PermutationMapLinker;
import com.googlecode.mgwt.linker.linker.XMLPermutationProvider;
import com.googlecode.mgwt.linker.linker.XMLPermutationProviderException;
import com.googlecode.mgwt.linker.server.propertyprovider.MgwtOsPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.MobileUserAgentProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;
import com.googlecode.mgwt.linker.server.propertyprovider.UserAgentPropertyProvider;

public class Html5ManifestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540671294104865306L;
	private XMLPermutationProvider permutationProvider;
	private ServletContext context;

	private Map<String, PropertyProvider> propertyProviders = new HashMap<String, PropertyProvider>();

	public Html5ManifestServlet() {
		permutationProvider = new XMLPermutationProvider();

		MgwtOsPropertyProvider mgwtOsPropertyProvider = new MgwtOsPropertyProvider();
		propertyProviders.put(mgwtOsPropertyProvider.getPropertyName(), mgwtOsPropertyProvider);

		UserAgentPropertyProvider userAgentPropertyProvider = new UserAgentPropertyProvider();
		propertyProviders.put(userAgentPropertyProvider.getPropertyName(), userAgentPropertyProvider);

		MobileUserAgentProvider mobileUserAgentProvider = new MobileUserAgentProvider();
		propertyProviders.put(mobileUserAgentProvider.getPropertyName(), mobileUserAgentProvider);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Set<BindingProperty> computedBindings = new HashSet<BindingProperty>();

			Set<Entry<String, PropertyProvider>> set = propertyProviders.entrySet();
			for (Entry<String, PropertyProvider> entry : set) {
				String varValue = entry.getValue().getPropertyValue(req);
				BindingProperty bindingProperty = new BindingProperty(entry.getKey(), varValue);
				computedBindings.add(bindingProperty);
			}

			String moduleName = getModuleName(req);

			String strongName = getPermutationStrongName(moduleName, computedBindings);

			if (strongName != null) {
				serveManifest(req, resp, moduleName + "/" + strongName + ".manifest");
				return;
			}

			boolean matches = false;
			BindingProperty bpToRemove = null;
			//maybe this is an iphone where we can`t detect retina or not retina...
			for (BindingProperty bp : computedBindings) {
				if ("mgwt.os".equals(bp.getName())) {
					if ("iphone_undefined".equals(bp.getValue())) {
						//oh shit this is an iphone
						//so now we need to serve to manifests
						//retina...
						//non retina
						matches = true;
						bpToRemove = bp;
					}
				}
			}
			if (matches) {
				computedBindings.remove(bpToRemove);

				Set<BindingProperty> iphoneMatch = new HashSet<BindingProperty>();
				iphoneMatch.add(new BindingProperty("mgwt.os", "iphone"));
				Set<BindingProperty> retinaMatch = new HashSet<BindingProperty>();
				retinaMatch.add(new BindingProperty("mgwt.os", "retina"));

				String iPhoneStrong = getPermutationStrongName(moduleName, iphoneMatch);
				String retinaStrong = getPermutationStrongName(moduleName, retinaMatch);

				if (iPhoneStrong != null && retinaStrong != null) {
					//put them together
					String manifest = mergeManifests(moduleName, iPhoneStrong, retinaStrong);

					serveStringManifest(req, resp, manifest);
					return;
				}

			} else {
				//TODO throw...
				throw new RuntimeException();
			}

		} catch (XMLPermutationProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO
		throw new RuntimeException();
	}

	private void serveStringManifest(HttpServletRequest req, HttpServletResponse resp, String manifest) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//TODO 
		throw new RuntimeException();

	}

	private String mergeManifests(String moduleName, String iPhoneStrong, String retinaStrong) {
		File iphoneManifest = new File(context.getRealPath(moduleName + "/" + iPhoneStrong + ".manifest"));
		File retinaManifest = new File(context.getRealPath(moduleName + "/" + retinaStrong + ".manifest"));

		if (!iphoneManifest.exists()) {
			//TODO handle
			throw new RuntimeException();
		}

		if (!retinaManifest.exists()) {
			//TODO handle
			throw new RuntimeException();
		}

		StringWriter manifestWriter = new StringWriter();

		try {
			BufferedReader br = new BufferedReader(new FileReader(iphoneManifest));
			String line = null;

			while ((line = br.readLine()) != null) {

				manifestWriter.append(line);
			}
			br.close();

			br = new BufferedReader(new FileReader(iphoneManifest));
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if (first) {
					first = false;
					continue;
				}
				manifestWriter.append(line);
			}

			br.close();

			return manifestWriter.toString();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//TODO 
		throw new RuntimeException();
	}

	public String getPermutationStrongName(String moduleName, Set<BindingProperty> computedBindings) throws FileNotFoundException, XMLPermutationProviderException {

		FileInputStream fileInputStream = new FileInputStream(context.getRealPath(moduleName + "/" + PermutationMapLinker.MANIFEST_MAP_FILE_NAME));
		Map<String, List<BindingProperty>> map = permutationProvider.getBindingProperties(fileInputStream);
		for (Entry<String, List<BindingProperty>> entry : map.entrySet()) {
			List<BindingProperty> value = entry.getValue();

			if (value.containsAll(computedBindings)) {
				//found our match
				//lets take a look if we find the manifest
				String strongName = entry.getKey();
				return strongName;
			}
		}
		return null;
	}

	protected void serveManifest(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {

		if (!new File(context.getRealPath(path)).exists()) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {

			resp.setHeader("Cache-Control", "no-cache");
			resp.setHeader("Pragma", "no-cache");
			resp.setDateHeader("Expires", new Date().getTime());

			resp.setContentType("text/cache-manifest");

			InputStream is = new FileInputStream(context.getRealPath(path));
			ServletOutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		}
	}

	protected String getModuleName(HttpServletRequest req) throws ServletException {

		//request url should be something like .../modulename.manifest" within the same folder of your host page...

		Pattern pattern = Pattern.compile("/([a-zA-Z0-9]+)\\.manifest$");
		Matcher matcher = pattern.matcher(req.getRequestURI());
		if (!matcher.find()) {
			log("can not calculate module base from url: '" + req.getRequestURI() + "'");
			throw new ServletException("can not calculate module base from url: '" + req.getRequestURI() + "'");
		}

		String module = matcher.group(1);
		return module;

	}
}
