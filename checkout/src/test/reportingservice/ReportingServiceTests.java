package test.reportingservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.api.services.reporting.response.ChargebackList;
import com.checkout.api.services.reporting.response.TransactionList;
import com.checkout.api.services.reporting.request.ChargebackQuery;
import com.checkout.api.services.reporting.request.TransactionQuery;
import com.checkout.api.services.shared.Response;
import com.google.gson.JsonSyntaxException;

import test.TestHelper;

public class ReportingServiceTests {

	APIClient ckoClient;
	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient(TestHelper.secretKey,true);
	}
	
	@Test
	public void queryTransaction_PageSizeShouldBeWithinLimits_Null()  throws JsonSyntaxException, IOException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, null, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
	}
	
	@Test
	public void queryTransaction_PageSizeShouldBeWithinLimits_Bad()  throws JsonSyntaxException, IOException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, null, null, 9, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(true, response.hasError);
		assertEquals(400, response.httpStatus);
	}
	
	@Test
	public void queryTransaction_PageSizeShouldBeWithinLimits_OK() throws JsonSyntaxException, IOException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, null, null, 15, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
	}
	
	@Test
	public void queryTransaction_ShouldAllowPagination_Null() throws JsonSyntaxException, IOException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, null, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(1, response.model.pageNumber);
	}
	
	
	@Test
	public void queryChargebacks_PageSizeShouldBeWithinLimits_Null()  throws JsonSyntaxException, IOException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, null, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
	}
	
	@Test
	public void queryChargebacks_PageSizeShouldBeWithinLimits_OK() throws JsonSyntaxException, IOException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, null, null, 15, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
	}
}
