package ru.mornimf.revolut_test.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import ru.mornimf.revolut_test.dao.UserDao;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.User;

public class UserDaoImpl implements UserDao {
	private EntityManager entityManager;

	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<User> getUserList() throws DataSourceException {
		try {
			return this.entityManager.createQuery("from User u", User.class).getResultList();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

	public User getUserById(Integer id) throws DataSourceException {
		try {
			return this.entityManager.createQuery("from User u where u.id = " + id, User.class).getSingleResult();
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

	public void updateUser(User user) throws DataSourceException {
		try {
			this.entityManager.merge(user);
		} catch (Exception e) {
			throw new DataSourceException(e.getMessage(), e);
		}
	}

}
