package ru.mornimf.revolut_test.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ru.mornimf.revolut_test.dao.AccountDao;
import ru.mornimf.revolut_test.dao.TransferDao;
import ru.mornimf.revolut_test.dao.UserDao;
import ru.mornimf.revolut_test.dao.impl.AccountDaoImpl;
import ru.mornimf.revolut_test.dao.impl.TransferDaoImpl;
import ru.mornimf.revolut_test.dao.impl.UserDaoImpl;

public class DataSourceImpl implements DataSource {

	private UserDao userDao;
	private AccountDao accountDao;
	private TransferDao transferDao;

	private EntityManager em;

	public DataSourceImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnf-pu-test");
		em = emf.createEntityManager();

		this.userDao = new UserDaoImpl(em);
		this.accountDao = new AccountDaoImpl(em);
		this.transferDao = new TransferDaoImpl(em);
	}

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public AccountDao getAccountDao() {
		return this.accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public TransferDao getTransferDao() {
		return transferDao;
	}

	public void setTransferDao(TransferDao transferDao) {
		this.transferDao = transferDao;
	}

	public void startTransaction() {
		em.getTransaction().begin();
	}

	public void commitTransaction() {
		em.getTransaction().commit();
	}

	public void rollbackTransaction() {
		em.getTransaction().rollback();
	}

}