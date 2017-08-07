package ru.mornimf.revolut_test.dao;

import java.util.List;

import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.Account;

public interface AccountDao {
	List<Account> getAccountListByUserId(Integer userId) throws DataSourceException;
	Account getAccountById(Integer id) throws DataSourceException;
	void updateAccount(Account account) throws DataSourceException;
}
