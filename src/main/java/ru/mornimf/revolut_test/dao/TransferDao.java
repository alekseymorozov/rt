package ru.mornimf.revolut_test.dao;

import java.util.List;

import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.Transfer;
import ru.mornimf.revolut_test.model.User;

public interface TransferDao {
	List<Transfer> getTranserList() throws DataSourceException;
	void saveTransfer(Transfer transfer) throws DataSourceException;
	List<Transfer> getTransferListByUser(User user) throws DataSourceException;
}
