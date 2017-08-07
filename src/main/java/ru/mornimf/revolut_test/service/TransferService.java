package ru.mornimf.revolut_test.service;

import java.util.List;

import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.exceptions.TransferException;
import ru.mornimf.revolut_test.model.Account;
import ru.mornimf.revolut_test.model.Transfer;
import ru.mornimf.revolut_test.model.User;

public interface TransferService {
	Transfer transferMoney(Account accountFrom, Account accountTo, Transfer transfer) throws TransferException;
	List<Transfer> getTransferListByUser(User user) throws DataSourceException;
	List<Transfer> getTransferList() throws DataSourceException;
}
