package ru.mornimf.revolut_test.handler;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.datasource.DataSourceFactory;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.exceptions.HandlerException;
import ru.mornimf.revolut_test.model.User;

@Path("/")
public class UserServerHandler {
	public static final Logger log = LoggerFactory.getLogger(UserServerHandler.class);
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserList() throws HandlerException {
		try {
			List<User> userList = DataSourceFactory.getDataSource().getUserDao().getUserList();
			return userList;	
		} catch (DataSourceException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Coulnd't get User list", e);
		}
	}

}
