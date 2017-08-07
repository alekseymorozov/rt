package ru.mornimf.revolut_test.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.datasource.DataSource;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.exceptions.TransferException;
import ru.mornimf.revolut_test.model.Account;
import ru.mornimf.revolut_test.model.Transfer;
import ru.mornimf.revolut_test.model.User;
import ru.mornimf.revolut_test.service.TransferService;

public class TransferServiceImpl implements TransferService {
	public static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

	private DataSource dataSource;

	public TransferServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public synchronized Transfer transferMoney(Account accountFrom, Account accountTo, Transfer transfer) throws TransferException {
		try {
			this.dataSource.startTransaction();
			accountFrom.setBalance(accountFrom.getBalance().subtract(transfer.getAmount()));
			accountTo.setBalance(accountTo.getBalance().add(transfer.getAmount()));
			this.dataSource.getAccountDao().updateAccount(accountFrom);
			this.dataSource.getAccountDao().updateAccount(accountTo);
			this.dataSource.getTransferDao().saveTransfer(transfer);
			this.dataSource.commitTransaction();
			return transfer;
		} catch (DataSourceException e) {
			this.dataSource.rollbackTransaction();
			throw new TransferException(e.getMessage(), e);
		}
	}

	public List<Transfer> getTransferListByUser(User user) throws DataSourceException {
			return this.dataSource.getTransferDao().getTransferListByUser(user);
	}

	public List<Transfer> getTransferList() throws DataSourceException {
		return this.dataSource.getTransferDao().getTranserList();
	}

}
