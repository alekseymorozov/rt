package ru.mornimf.revolut_test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.mornimf.revolut_test.datasource.DataSourceFactory;
import ru.mornimf.revolut_test.exceptions.DataSourceException;
import ru.mornimf.revolut_test.model.Transfer;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private static TransferServer transferServer;

	private final static String ROOT_URL = "http://localhost:8080/";

	private final static String USER_LIST_URL = "users/";
	private final static String ACCOUNT_BY_ID = "accounts/%accountId%";
	private final static String ACCOUNT_BY_USER = "accounts/user/%userId%";
	private final static String TRANSFER = "transfer/";
	private final static String TRANSFER_LIST = "transfer/";
	private final static String TRANSFER_LIST_BY_USER = "transfer/user/%userId%";

	@BeforeClass
	public static void before() throws Exception {
		DataSourceFactory.initDataSource();
		// Create Server
		transferServer = new TransferServer();
		transferServer.startServer();
	}

	@Test
	public void testUserList() throws IOException {
		HttpURLConnection http = prepareHttpConnection(USER_LIST_URL);
		assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
		String responseBody = getResponseBody(http.getInputStream());
		assertThat("Response Body", responseBody, is("[{\"firstName\":\"Gregory\",\"id\":1,\"secondName\":\"House\"},{\"firstName\":\"Eric\",\"id\":2,\"secondName\":\"Forman\"},{\"firstName\":\"Ellison\",\"id\":3,\"secondName\":\"Kemeron\"}]"));
	}

	@Test
	public void testAccountListById() throws IOException {
		HttpURLConnection http = prepareHttpConnection(ACCOUNT_BY_ID.replaceAll("%accountId%", "1"));
		assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
		String responseBody = getResponseBody(http.getInputStream());
		assertThat("Response Body", responseBody, is("{\"balance\":100000,\"id\":1}"));
	}

	@Test
	public void testAccountListByUser() throws IOException {
		HttpURLConnection http = prepareHttpConnection(ACCOUNT_BY_USER.replaceAll("%userId%", "1"));
		assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
		String responseBody = getResponseBody(http.getInputStream());
		assertThat("Response Body", responseBody, is("[{\"balance\":100000,\"id\":1},{\"balance\":500000,\"id\":2}]"));
	}	

	@Test
	public void testTransferListByUser() throws IOException {
		HttpURLConnection http = prepareHttpConnection(TRANSFER_LIST_BY_USER.replaceAll("%userId%", "1"));
		assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
		String responseBody = getResponseBody(http.getInputStream());
		assertThat("Response Body", responseBody, is("[{\"accountFrom\":1,\"accountTo\":2,\"amount\":500,\"dt\":\"2017-01-01T18:00:00.69+03:00\",\"id\":1}]"));
	}

	@Test
	public void testTransfer() throws IOException, DataSourceException {
		List<Transfer> transferListBefore = DataSourceFactory.getDataSource().getTransferDao().getTranserList();
		assertThat("Transfer List Count 1", transferListBefore.size(), is(1));
		HttpURLConnection transferHttp = prepareHttpConnection(TRANSFER, "POST");
		transferHttp.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		transferHttp.setRequestProperty( "charset", "utf-8");
		transferHttp.setDoOutput(true);
		
		Integer accountFrom = 3;
		Integer accountTo = 4;
		BigDecimal amount = new BigDecimal(500);
		
		String postData = "accountFrom=%accountFrom%&accountTo=%accountTo%&amount=%amount%";
		postData = postData.replaceAll("%accountFrom%", accountFrom.toString()).replaceAll("%accountTo%", accountTo.toString()).replaceAll("%amount%", amount.toString());
		
		OutputStream os = transferHttp.getOutputStream();
        os.write(postData.getBytes());
		assertThat("Response Code", transferHttp.getResponseCode(), is(HttpStatus.OK_200));
		
		HttpURLConnection transferListHttp = prepareHttpConnection(TRANSFER_LIST);
		assertThat("Response Code", transferListHttp.getResponseCode(), is(HttpStatus.OK_200));
		
		List<Transfer> transferListAfter = DataSourceFactory.getDataSource().getTransferDao().getTranserList();
		assertThat("Transfer List Count 2", transferListAfter.size(), is(2));
		
		Transfer transfer = transferListAfter.get(1);
		assertThat("Account From", transfer.getAccountFrom(), is(accountFrom));
		assertThat("Account To", transfer.getAccountTo(), is(accountTo));
		assertThat("Amount", transfer.getAmount(), is(amount));
	}

	@AfterClass
	public static void after() throws Exception {
		transferServer.stopServer();
	}

	private static HttpURLConnection prepareHttpConnection(String URL) throws IOException {
		return prepareHttpConnection(URL, "GET");
	}

	private static HttpURLConnection prepareHttpConnection(String URL, String method) throws IOException {
		HttpURLConnection http = (HttpURLConnection) new URL(ROOT_URL + URL).openConnection();
		http.setRequestMethod(method);
		return http;
	}
	
	private static String getResponseBody(InputStream inputStream) throws IOException {
		BufferedReader responeBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer responseBuffer = new StringBuffer();
		String inputLine;
		while ((inputLine = responeBufferedReader.readLine()) != null) {
			responseBuffer.append(inputLine);
		}
		return responseBuffer.toString();
	}
}
