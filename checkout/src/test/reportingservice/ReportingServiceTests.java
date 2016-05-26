package test.reportingservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.api.services.reporting.response.Chargeback;
import com.checkout.api.services.reporting.response.ChargebackList;
import com.checkout.api.services.reporting.response.Transaction;
import com.checkout.api.services.reporting.response.TransactionList;
import com.checkout.api.services.reporting.request.ChargebackField;
import com.checkout.api.services.reporting.request.ChargebackFilter;
import com.checkout.api.services.reporting.request.ChargebackQuery;
import com.checkout.api.services.reporting.request.ChargebackSortColumn;
import com.checkout.api.services.reporting.request.TransactionFilter;
import com.checkout.api.services.reporting.request.TransactionField;
import com.checkout.api.services.reporting.request.TransactionQuery;
import com.checkout.api.services.reporting.request.TransactionSortColumn;
import com.checkout.api.services.shared.Action;
import com.checkout.api.services.shared.Operator;
import com.checkout.api.services.shared.Response;
import com.checkout.api.services.shared.SortOrder;
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
	public void queryTransaction_ShouldAllowFilteringBySearchString_test() throws JsonSyntaxException, IOException {
		String search = "test";
		TransactionQuery request = TestHelper.getQueryTransactionModel(search, null, null, null, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		assertEquals(true, recordsContainText(response.model.data, search));
	}
	
	@Test
	public void queryTransaction_ShouldAllowFilteringBySearchString_Captured() throws JsonSyntaxException, IOException {
		String search = "Captured";
		TransactionQuery request = TestHelper.getQueryTransactionModel(search, null, null, null, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		assertEquals(true, recordsContainText(response.model.data, search));
	}
	
	@Test
	public void queryTransaction_ShouldAllowFilteringBySearchString_TRK12345() throws JsonSyntaxException, IOException {
		String search = "TRK12345";
		TransactionQuery request = TestHelper.getQueryTransactionModel(search, null, null, null, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		assertEquals(true, recordsContainText(response.model.data, search));
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Id() throws JsonSyntaxException, IOException, IllegalAccessException, NoSuchFieldException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Id, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "id", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_OriginId() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.OriginId, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "originId", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Email() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Email, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		String value = "";
		String previousValue = "";
		for(int i = 0; i < response.model.data.size(); i++) {
			previousValue = value;
			value = response.model.data.get(i).customer.email;
			
			assertEquals(true, value.compareTo(previousValue) >= 0);
		}
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Name() throws JsonSyntaxException, IOException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Name, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		String value = "";
		String previousValue = "";
		for(int i = 0; i < response.model.data.size(); i++) {
			previousValue = value;
			value = response.model.data.get(i).customer.name;
			
			assertEquals(true, value.compareTo(previousValue) >= 0);
		}
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Status() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Status, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "status", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Type() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Type, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "type", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Amount() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Amount, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertAmountSortOrder(response.model.data, "amount", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Scheme() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Scheme, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "scheme", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_ResponseCode() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.ResponseCode, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "responseCode", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_Currency() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Currency, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "currency", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_LiveMode() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.LiveMode, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "liveMode", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_BusinessName() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.BusinessName, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "businessName", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_ChannelName() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.ChannelName, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "channelName", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSorting_TrackId() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.TrackId, null, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertStringSortOrder(response.model.data, "trackId", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSortingOrder_Asc() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Date, SortOrder.Asc, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertDateSortOrder(response.model.data, "date", true);
	}
	
	@Test
	public void queryTransaction_ShouldAllowSortingOrder_Desc() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, TransactionSortColumn.Date, SortOrder.Desc, null, null, null);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		assertDateSortOrder(response.model.data, "date", false);
	}
	
	@Test
	public void queryTransaction_ShouldAllowFilteringWithAction_Exclude() throws JsonSyntaxException, IOException {
		TransactionFilter filter = new TransactionFilter();
		filter.action = Action.Exclude;
		filter.field = TransactionField.Email;
		filter.operator = Operator.Contains;
		filter.value = "test";
		
		List<TransactionFilter> filterList = new ArrayList<TransactionFilter>();
		filterList.add(filter);
		
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, null, null, null, null, filterList);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		for(int i = 0; i < response.model.data.size(); i++) {
			String email = response.model.data.get(i).customer.email;
			assertEquals(false, email.contains(filter.value));
		}
	}
	
	@Test
	public void queryTransaction_ShouldAllowFilteringWithAction_Include() throws JsonSyntaxException, IOException {
		TransactionFilter filter = new TransactionFilter();
		filter.action = Action.Include;
		filter.field = TransactionField.Email;
		filter.operator = Operator.Contains;
		filter.value = "test";
		
		List<TransactionFilter> filterList = new ArrayList<TransactionFilter>();
		filterList.add(filter);
		
		TransactionQuery request = TestHelper.getQueryTransactionModel("", null, null, null, null, null, null, filterList);
		Response<TransactionList> response = ckoClient.reportingService.queryTransaction(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		assertEquals(true, response.model.totalRecords > 0);
		
		for(int i = 0; i < response.model.data.size(); i++) {
			String email = response.model.data.get(i).customer.email;
			assertEquals(true, email.contains(filter.value));
		}
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
	
	@Test
	public void queryChargeback_ShouldAllowFilteringBySearchString_test() throws JsonSyntaxException, IOException {
		String search = "test";
		ChargebackQuery request = TestHelper.getQueryChargebackModel(search, null, null, null, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertEquals(true, recordsContainTextChargeback(response.model.data, search));
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowFilteringBySearchString_ADJM() throws JsonSyntaxException, IOException {
		String search = "ADJM";
		ChargebackQuery request = TestHelper.getQueryChargebackModel(search, null, null, null, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertEquals(true, recordsContainTextChargeback(response.model.data, search));
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Id() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Id, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertStringSortOrder(response.model.data, "id", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_ChargeId() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.ChargeId, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertStringSortOrder(response.model.data, "chargeId", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_TrackId() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.TrackId, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertStringSortOrder(response.model.data, "trackId", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Email() throws JsonSyntaxException, IOException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Email, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			String value = null;
			String previousValue = null;
			for(int i = 0; i < response.model.data.size(); i++) {
				previousValue = value;
				
				if(response.model.data.get(i).customer != null) {
					value = response.model.data.get(i).customer.email.toLowerCase();
				}
				
				if (previousValue != null && value != null) {
					assertEquals(true, value.compareTo(previousValue) >= 0);
				}
			}
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Name() throws JsonSyntaxException, IOException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Name, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			String value = "";
			String previousValue = "";
			for(int i = 0; i < response.model.data.size(); i++) {
				previousValue = value;
				
				if(response.model.data.get(i).customer != null) {
					value = response.model.data.get(i).customer.name.toLowerCase();
				}
				
				if (previousValue != null && value != null) {
					assertEquals(true, value.compareTo(previousValue) >= 0);
				}
			}
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Indicator() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Indicator, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertStringSortOrder(response.model.data, "indicator", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Amount() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Amount, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertAmountSortOrder(response.model.data, "amount", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Currency() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Currency, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertStringSortOrder(response.model.data, "currency", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSorting_Scheme() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.Scheme, null, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertStringSortOrder(response.model.data, "scheme", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSortingOrder_Asc() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.IssueDate, SortOrder.Asc, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertDateSortOrder(response.model.data, "issueDate", true);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowSortingOrder_Desc() throws JsonSyntaxException, IOException, NoSuchFieldException, IllegalAccessException {
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, ChargebackSortColumn.IssueDate, SortOrder.Desc, null, null, null);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			assertDateSortOrder(response.model.data, "issueDate", false);
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowFilteringWithAction_Exclude() throws JsonSyntaxException, IOException {
		ChargebackFilter filter = new ChargebackFilter();
		filter.action = Action.Exclude;
		filter.field = ChargebackField.Email;
		filter.operator = Operator.Contains;
		filter.value = "test";
		
		List<ChargebackFilter> filterList = new ArrayList<ChargebackFilter>();
		filterList.add(filter);
		
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, null, null, null, null, filterList);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		// No chargebacks on Sandbox
		if (response.model.totalRecords > 0) {
			for(int i = 0; i < response.model.data.size(); i++) {
				String email = response.model.data.get(i).customer.email;
				assertEquals(false, email.contains(filter.value));
			}
		}
	}
	
	@Test
	public void queryChargeback_ShouldAllowFilteringWithAction_Include() throws JsonSyntaxException, IOException {
		ChargebackFilter filter = new ChargebackFilter();
		filter.action = Action.Include;
		filter.field = ChargebackField.Email;
		filter.operator = Operator.Contains;
		filter.value = "test";
		
		List<ChargebackFilter> filterList = new ArrayList<ChargebackFilter>();
		filterList.add(filter);
		
		ChargebackQuery request = TestHelper.getQueryChargebackModel("", null, null, null, null, null, null, filterList);
		Response<ChargebackList> response = ckoClient.reportingService.queryChargeback(request);
		
		assertNotNull(response);
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		if (response.model.totalRecords > 0) {
			for(int i = 0; i < response.model.data.size(); i++) {
				String email = response.model.data.get(i).customer.email;
				assertEquals(true, email.contains(filter.value));
			}
		}
	}
	
	private <T> void assertStringSortOrder(List<T> records, String fieldName, Boolean asc) throws NoSuchFieldException, IllegalAccessException {
		String value = "";
		String previousValue = "";
		Object obj;
		for(int i = 0; i < records.size(); i++) {
			previousValue = value;
			Class<?> reflecClass = records.get(i).getClass();
			Field field = reflecClass.getDeclaredField(fieldName);
			
			obj = field.get(records.get(i));
			if (obj != null) {
				value = obj.toString().toLowerCase();
			}
			
			if (previousValue != null && value != null) {
				assertEquals(asc, value.compareTo(previousValue) >= 0);
			}
		}
	}
	
	private <T> void assertAmountSortOrder(List<T> records, String fieldName, Boolean asc) throws NoSuchFieldException, IllegalAccessException {
		Float value = 0.0F;
		Float previousValue = 0.0F;
		Object obj;
		for(int i = 0; i < records.size(); i++) {
			previousValue = value;
			Class<?> reflecClass = records.get(i).getClass();
			Field field = reflecClass.getDeclaredField(fieldName);
			
			obj = field.get(records.get(i));
			if (obj != null) {
				value = Float.parseFloat(obj.toString());
			}
			
			if (previousValue != null && value != null) {
				assertEquals(asc, value.compareTo(previousValue) >= 0);
			}
		}
	}
	
	private <T> void assertDateSortOrder(List<T> records, String fieldName, Boolean asc) throws NoSuchFieldException, IllegalAccessException {
		Date value = null;
		Date previousValue = null;
		for(int i = 0; i < records.size(); i++) {
			previousValue = value;
			Class<?> reflecClass = records.get(i).getClass();
			Field field = reflecClass.getDeclaredField(fieldName);
			value = (Date)field.get(records.get(i));
			
			if (previousValue != null && value != null) {
				assertEquals(asc, value.after(previousValue) || value.equals(previousValue));
			}
		}
	}
	
	private Boolean recordsContainText(List<Transaction> records, String search)
	{
		Boolean found = false;
		for(int i=0; i < records.size(); i++) {
			Transaction tran = records.get(i);
			if (tran.id != null && tran.id.contains(search)) {
				found = true;
			}
			
			if (tran.originId != null && tran.originId.contains(search)) {
				found = true;
			}
			
			if (tran.trackId != null && tran.trackId.contains(search)) {
				found = true;
			}
			
			if (tran.status != null && tran.status.contains(search)) {
				found = true;
			}
			
			if (tran.customer != null && tran.customer.email != null && tran.customer.email.contains(search)) {
				found = true;
			}
			
			if (found) {
				break;
			}
		}
		
		return found;
	}
	
	private Boolean recordsContainTextChargeback(List<Chargeback> records, String search)
	{
		Boolean found = false;
		for(int i=0; i < records.size(); i++) {
			Chargeback charge = records.get(i);
			if (charge.id != null && charge.id.contains(search)) {
				found = true;
			}
			
			if (charge.chargeId != null && charge.chargeId.contains(search)) {
				found = true;
			}
			
			if (charge.trackId != null && charge.trackId.contains(search)) {
				found = true;
			}
			
			if (charge.indicator != null && charge.indicator.contains(search)) {
				found = true;
			}
			
			if (charge.customer != null && charge.customer.email != null && charge.customer.email.contains(search)) {
				found = true;
			}
			
			if (charge.customer != null && charge.customer.name != null && charge.customer.name.contains(search)) {
				found = true;
			}
			
			if (charge.indicator != null && charge.indicator != null && charge.indicator.contains(search)) {
				found = true;
			}
			
			if (charge.cardNumber != null && charge.cardNumber != null && charge.cardNumber.contains(search)) {
				found = true;
			}
			
			if (found) {
				break;
			}
		}
		
		return found;
	}
}
