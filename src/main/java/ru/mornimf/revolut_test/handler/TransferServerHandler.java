package ru.mornimf.revolut_test.handler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mornimf.revolut_test.datasource.DataSourceFactory;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.exceptions.HandlerException;
import ru.mornimf.revolut_test.exceptions.TransferException;
import ru.mornimf.revolut_test.model.Account;
import ru.mornimf.revolut_test.model.Transfer;
import ru.mornimf.revolut_test.model.User;
import ru.mornimf.revolut_test.service.TransferService;
import ru.mornimf.revolut_test.service.impl.TransferServiceImpl;


@Path("/")
public class TransferServerHandler {
	public static final Logger log = LoggerFactory.getLogger(TransferServerHandler.class);
	
	private TransferService transferService;
	
	public TransferServerHandler () {
		this.transferService = new TransferServiceImpl(DataSourceFactory.getDataSource());
	}
	@POST
	@Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Transfer transferMoney(@FormParam("accountFrom") Integer accountFrom, @FormParam("accountTo") Integer accountTo, @FormParam("amount") BigDecimal amount) throws HandlerException {
		//Check AccountFrom
		try {
		if(accountFrom==null || accountTo==null) {
			throw new HandlerException("Empty account Ids");
		}
		Account accountFromObj = DataSourceFactory.getDataSource().getAccountDao().getAccountById(accountFrom);
		Account accountToObj = DataSourceFactory.getDataSource().getAccountDao().getAccountById(accountTo);
		if(accountFromObj==null || accountToObj == null) {
			throw new HandlerException("One or both accounts don't extist");
		}
		Transfer transfer = new Transfer();
		transfer.setAccountFrom(accountFromObj.getId());
		transfer.setAccountTo(accountToObj.getId());
		transfer.setAmount(amount);
		transfer.setDt(new Date());
		
		return this.transferService.transferMoney(accountFromObj, accountToObj, transfer);
		
		} catch (DataSourceException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Couldn't transfer money", e);
		} catch (TransferException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Couldn't transfer money", e);
		}
	}
	
	@GET
	@Path("/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transfer> getTransferListByUser(@PathParam("userId") Integer userId) throws HandlerException {
		User user;
		try {
			user = DataSourceFactory.getDataSource().getUserDao().getUserById(userId);
			return this.transferService.getTransferListByUser(user);
		} catch (DataSourceException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Couldn't get transfer list for user", e);
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transfer> getTransferList() throws HandlerException {
		try {
			return this.transferService.getTransferList();
		} catch (DataSourceException e) {
			log.error(e.getMessage(), e);
			throw new HandlerException("Couldn't get all transfers", e);
		}
	}
	
}
