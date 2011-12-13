package com.googlecode.mgwt.linker.server;

import java.io.FileInputStream;
import java.io.IOException;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.mgwt.linker.linker.PermutationMapLinker;
import com.googlecode.mgwt.linker.linker.XMLPermutationProvider;
import com.googlecode.mgwt.linker.linker.XMLPermutationProviderException;

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

			FileInputStream fileInputStream = new FileInputStream(context.getRealPath(moduleName + "/" + PermutationMapLinker.MANIFEST_MAP));
			Map<String, List<BindingProperty>> map = permutationProvider.getBindingProperties(fileInputStream);
			for (Entry<String, List<BindingProperty>> entry : map.entrySet()) {
				List<BindingProperty> value = entry.getValue();

				if (value.containsAll(computedBindings)) {
					//found our match
				}
			}

		} catch (XMLPermutationProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
