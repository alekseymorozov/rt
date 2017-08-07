package ru.mornimf.revolut_test.dao;

import java.util.List;

import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.User;

public interface UserDao {
	List<User> getUserList() throws DataSourceException;
	User getUserById(Integer id) throws DataSourceException;
	void updateUser(User user) throws DataSourceException;
}
