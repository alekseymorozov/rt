package ru.mornimf.revolut_test.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import ru.mornimf.revolut_test.dao.AccountDao;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.Account;
import ru.mornimf.revolut_test.model.User;

public class AccountDaoImpl implements AccountDao {

	private EntityManager entityManager;

	public AccountDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Account> getAccountListByUserId(Integer userId) throws DataSourceException {
		try {
			return this.entityManager.createQuery("from Account a where a.user.id=" + userId, Account.class)
					.getResultList();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

	public Account getAccountById(Integer id) throws DataSourceException {
		try {
			return this.entityManager.createQuery("from Account a where a.id="+id, Account.class).getSingleResult();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

	public void updateAccount(Account account) throws DataSourceException {
		try {
			this.entityManager.merge(account);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

}
