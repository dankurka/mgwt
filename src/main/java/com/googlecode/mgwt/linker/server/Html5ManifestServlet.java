package com.googlecode.mgwt.linker.server;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Html5ManifestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540671294104865306L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String moduleName = getModuleName(req);

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
