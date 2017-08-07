package ru.mornimf.revolut_test.handler;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.datasource.DataSourceFactory;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.exceptions.HandlerException;
import ru.mornimf.revolut_test.model.Account;

@Path("/")
public class AccountServerHandler {
	public static final Logger log = LoggerFactory.getLogger(AccountServerHandler.class);

	@GET
	@Path("/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccountListByUserId(@PathParam("userId") Integer userId) throws HandlerException {
		try {
			return DataSourceFactory.getDataSource().getAccountDao().getAccountListByUserId(userId);
		} catch (DataSourceException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Couldn't get account list", e);
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccountById(@PathParam("id") Integer id) throws HandlerException {
		try {
			return DataSourceFactory.getDataSource().getAccountDao().getAccountById(id);
		} catch (DataSourceException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Couldn't get account with id = " + id, e);
		}
	}
}
