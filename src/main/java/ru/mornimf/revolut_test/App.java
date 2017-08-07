package ru.mornimf.revolut_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.datasource.DataSourceFactory;

public class App {
	public static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		log.info("DB connection is ininitializing");
		DataSourceFactory.initDataSource();
		log.info("DB connection is initialized successfully");

		Integer port = null;
		try {
			port = args.length > 0 ? Integer.valueOf(args[0]) : null;
		} catch (NumberFormatException e) {
			log.error("Wrong port parameter", e);
			System.exit(-1);
		}

		TransferServer transferServer = port == null ? new TransferServer() : new TransferServer(port);

		try {
			transferServer.startServer();
		} catch (Exception e) {
			log.error("Couldn't start server", e);
			System.exit(-2);
		}
	}
}
