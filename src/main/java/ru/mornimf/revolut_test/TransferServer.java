package ru.mornimf.revolut_test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.datasource.DataSourceFactory;
import ru.mornimf.revolut_test.handler.AccountServerHandler;
import ru.mornimf.revolut_test.handler.TransferServerHandler;
import ru.mornimf.revolut_test.handler.UserServerHandler;

public class TransferServer {
    public static final Logger log = LoggerFactory.getLogger(TransferServer.class);
    private final static int DEFAUL_PORT = 8080;
	private Server jettyServer;
	
	public TransferServer() {
		this.jettyServer = new Server(DEFAUL_PORT);
	}
	
	public TransferServer (int port) {
		this.jettyServer = new Server(port);
	}
	
	public void startServer () throws Exception {
		log.info("Servlet context handler is initializing");
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		
		ServletHolder accountJerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/accounts/*");
		accountJerseyServlet.setInitParameter("jersey.config.server.provider.classnames", AccountServerHandler.class.getCanonicalName());
		log.info("Account mapping - ok");
		
	    ServletHolder userJerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/users/*");
	    userJerseyServlet.setInitParameter("jersey.config.server.provider.classnames", UserServerHandler.class.getCanonicalName());
		log.info("User mapping - ok");
		
		ServletHolder transferJerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/transfer/*");
		transferJerseyServlet.setInitParameter("jersey.config.server.provider.classnames", TransferServerHandler.class.getCanonicalName());
		log.info("Transefr mapping - ok");
		
		this.jettyServer.setHandler(servletContextHandler);
		log.info("Servlet context handler is initialized");

		log.info("Starting server...");
		this.jettyServer.start();
		log.info("Server is started succesfully!");
		log.info("------------------------------");
		/*this.jettyServer.join();*/
	}
	
	public void stopServer() throws Exception {
		this.jettyServer.stop();
		log.info("Server is stopped");
	}
}
