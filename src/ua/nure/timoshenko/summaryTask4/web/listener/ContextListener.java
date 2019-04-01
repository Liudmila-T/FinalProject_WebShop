package ua.nure.timoshenko.summaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Context listener.
 *
 * @author L.Timoshenko
 *
 */
public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	/**
	 * @param event
	 */
	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		// do nothing
		log("Servlet context destruction finished");
	}

	/**
	 * Initializes the servlet context.
	 *
	 * @param event
	 */
	public void contextInitialized(ServletContextEvent event) {
		log("Servlet context initialization starts");

		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
		initI18N(servletContext);

		log("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 *
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext
					.getRealPath("WEB-INF/log4j.properties"));
		} catch (Exception ex) {
			LOG.error("Cannot configure Log4j", ex);
		}
		log("Log4J initialization finished");
		LOG.debug("Log4j has been initialized");
	}

	/**
	 * Initializes i18n subsystem.
	 */
	private void initI18N(ServletContext servletContext) {
		LOG.debug("I18N subsystem initialization started");

		String localesValue = servletContext.getInitParameter("locales");
		if (localesValue == null || localesValue.isEmpty()) {
			LOG.warn("'locales' init parameter is empty, the default encoding will be used");
		} else {
			List<String> locales = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(localesValue);
			while (st.hasMoreTokens()) {
				String localeName = st.nextToken();
				locales.add(localeName);
			}

			LOG.debug("Application attribute set: locales --> " + locales);
			servletContext.setAttribute("locales", locales);
		}

		LOG.debug("I18N subsystem initialization finished");
	}

	/**
	 * Initializes ContainerCommand.
	 	 */
	private void initCommandContainer() {

		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.timoshenko.summaryTask4.web.command.ContainerCommand");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException(
					"Cannot initialize Command Container");
		}
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}