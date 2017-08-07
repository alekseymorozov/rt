package ru.mornimf.revolut_test.datasource;

public class DataSourceFactory {
	private static DataSource dataSource;

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void initDataSource() {
		dataSource = new DataSourceImpl();
	}
}
