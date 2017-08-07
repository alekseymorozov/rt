package ru.mornimf.revolut_test.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.dao.TransferDao;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.Transfer;
import ru.mornimf.revolut_test.model.User;

public class TransferDaoImpl implements TransferDao {

	public static final Logger log = LoggerFactory.getLogger(TransferDaoImpl.class);

	private EntityManager entityManager;

	public TransferDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Transfer> getTranserList() throws DataSourceException {
		try {
			return this.entityManager.createQuery("from Transfer t", Transfer.class).getResultList();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

	public void saveTransfer(Transfer transfer) throws DataSourceException {
		try {
			this.entityManager.persist(transfer);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

	public List<Transfer> getTransferListByUser(User user) throws DataSourceException {
		try {
			return this.entityManager.createQuery("from Transfer t where t.accountFrom=" + user.getId()
					+ " or t.accountTo=" + user.getId(), Transfer.class).getResultList();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

}
