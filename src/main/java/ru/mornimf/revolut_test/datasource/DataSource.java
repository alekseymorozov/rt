package ru.mornimf.revolut_test.datasource;

import ru.mornimf.revolut_test.dao.AccountDao;
import ru.mornimf.revolut_test.dao.TransferDao;
import ru.mornimf.revolut_test.dao.UserDao;

public interface DataSource {
	UserDao getUserDao();
	AccountDao getAccountDao();
	TransferDao getTransferDao();
	void startTransaction();
	void commitTransaction();
	void rollbackTransaction();
}
