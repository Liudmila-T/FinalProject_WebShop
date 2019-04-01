package ua.nure.timoshenko.summaryTask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Encoding filter.
 *
 * @author L.Timoshenko
 *
 */
public class EncodingFilter implements Filter {

	private String encoding;

	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

	/**
	 * Do some things before the filter will be destroyed
	 */
	public void destroy() {
		LOG.debug("Filter destruction starts");
		// do nothing
		LOG.debug("Filter destruction finished");
	}

	/**
	 * Do the filter.
	 *
	 * @param request
	 * @param response
	 *
	 * @throws IOException
	 *             , ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		LOG.debug("Filter starts");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		LOG.trace("Request uri --> " + httpRequest.getRequestURI());

		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			LOG.trace("Request encoding = null, set encoding --> " + encoding);
			request.setCharacterEncoding(encoding);
		}

		LOG.debug("Filter finished");
		chain.doFilter(request, response);
	}

	/**
	 * Initializes the filter.
	 *
	 * @param fConfig
	 *
	 * @throws ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");
		encoding = fConfig.getInitParameter("encoding");
		LOG.trace("Encoding from web.xml --> " + encoding);
		LOG.debug("Filter initialization finished");
	}

}