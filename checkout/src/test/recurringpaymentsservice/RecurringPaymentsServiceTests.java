package test.recurringpaymentsservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.checkout.APIClient;
import com.checkout.api.services.card.request.CardCreate;
import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.charge.request.BaseCharge;
import com.checkout.api.services.charge.request.BaseChargeInfo;
import com.checkout.api.services.charge.request.CardCharge;
import com.checkout.api.services.charge.request.CardChargeWithNewPaymentPlan;
import com.checkout.api.services.charge.request.CardChargeWithPaymentPlan;
import com.checkout.api.services.charge.request.CardIdCharge;
import com.checkout.api.services.charge.request.CardTokenCharge;
import com.checkout.api.services.charge.request.ChargeCapture;
import com.checkout.api.services.charge.request.ChargeRefund;
import com.checkout.api.services.charge.request.ChargeVoid;
import com.checkout.api.services.charge.request.DefaultCardCharge;
import com.checkout.api.services.charge.response.Capture;
import com.checkout.api.services.charge.response.Charge;
import com.checkout.api.services.charge.response.ChargeHistory;
import com.checkout.api.services.charge.response.ChargeWithCustomerPaymentPlan;
import com.checkout.api.services.charge.response.Refund;
import com.checkout.api.services.charge.response.Void;
import com.checkout.api.services.customer.response.Customer;
import com.checkout.api.services.recurringPayments.request.ChargePaymentPlan;
import com.checkout.api.services.recurringPayments.request.CustomerPaymentPlanUpdate;
import com.checkout.api.services.recurringPayments.request.ExistingPaymentPlan;
import com.checkout.api.services.recurringPayments.request.PaymentPlan;
import com.checkout.api.services.recurringPayments.request.PaymentPlanUpdate;
import com.checkout.api.services.recurringPayments.response.CustomerPaymentPlan;
import com.checkout.api.services.recurringPayments.response.PaymentPlanGet;
import com.checkout.api.services.recurringPayments.response.PaymentPlans;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.google.gson.JsonSyntaxException;

import test.TestHelper;

public class RecurringPaymentsServiceTests {
	
	APIClient ckoClient;

	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50",true);
	}
	
	@Test
	public void createPaymentPlanTest() throws JsonSyntaxException, IOException {		
		
		Response<PaymentPlans> response = ckoClient.recurringPaymentsService.createPaymentPlan(TestHelper.getPaymentPlans());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);			
		
		assertEquals(response.model.paymentPlans.length, 1);
		assertEquals(response.model.paymentPlans[0].name, "Recurring Plan 1");
		assertNotNull(response.model.totalCollectionCount);
		assertNotNull(response.model.totalCollectionValue);
	}
	
	@Test
	public void updatePaymentPlanTest() throws JsonSyntaxException, IOException {		
		Response<PaymentPlans> response = ckoClient.recurringPaymentsService.createPaymentPlan(TestHelper.getPaymentPlans());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		Response<OkResponse> updateResponse = ckoClient.recurringPaymentsService.updatePaymentPlan(TestHelper.getPaymentPlanUpdate(), response.model.paymentPlans[0].planId);
		
		assertEquals(false, updateResponse.hasError);
		assertEquals(200, updateResponse.httpStatus);			
		
		assertEquals(updateResponse.model.message, "ok");
	}
	
	@Test
	public void getPaymentPlanTest() throws JsonSyntaxException, IOException {	
		Response<PaymentPlans> response = ckoClient.recurringPaymentsService.createPaymentPlan(TestHelper.getPaymentPlans());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		Response<PaymentPlanGet> getResponse = ckoClient.recurringPaymentsService.getPaymentPlan(response.model.paymentPlans[0].planId);
		
		assertEquals(false, getResponse.hasError);
		assertEquals(200, getResponse.httpStatus);			
		
		assertEquals(getResponse.model.name, "Recurring Plan 1");
		assertEquals(getResponse.model.value, 39900);
		assertEquals(getResponse.model.status, 1);
	}
	
	@Test
	public void cancelPaymentPlanTest() throws JsonSyntaxException, IOException {
		Response<PaymentPlans> response = ckoClient.recurringPaymentsService.createPaymentPlan(TestHelper.getPaymentPlans());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		Response<OkResponse> cancelResponse = ckoClient.recurringPaymentsService.cancelPaymentPlan(response.model.paymentPlans[0].planId);
		
		assertEquals(false, cancelResponse.hasError);
		assertEquals(200, cancelResponse.httpStatus);			
		
		assertEquals(cancelResponse.model.message, "ok");
	}
	
	@Test
	public void addPaymentPlanToCardChargeTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
		Response<PaymentPlans> response = ckoClient.recurringPaymentsService.createPaymentPlan(TestHelper.getPaymentPlans());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
//		CardChargeWithPaymentPlan cc2 = TestHelper.getCardChargeModel();
		CardChargeWithPaymentPlan cc = TestHelper.getCardChargeWithPaymentPlan();
		ExistingPaymentPlan epp = new ExistingPaymentPlan();
		epp.planId = response.model.paymentPlans[0].planId;
		epp.startDate = new Date(2016,04,22);
		cc.paymentPlans.add(epp);
		
		Response<ChargeWithCustomerPaymentPlan> planResponse = ckoClient.chargeService.addPaymentPlanToCardCharge(cc);
		
		assertEquals(false, planResponse.hasError);
		assertEquals(200, planResponse.httpStatus);
		assertEquals(planResponse.model.customerPaymentPlans[0].planId, response.model.paymentPlans[0].planId);
	}
	
	@Test
	public void createPaymentPlanWithCardChargeTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {

		CardChargeWithNewPaymentPlan cc = TestHelper.getCardChargeWithNewPaymentPlan();
		
		Response<ChargeWithCustomerPaymentPlan> planResponse = ckoClient.chargeService.createPaymentPlanWithCardCharge(cc);
		
		assertEquals(false, planResponse.hasError);
		assertEquals(200, planResponse.httpStatus);
		
	}
	
	@Test
	public void updateCustomerPaymentPlanTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		Response<ChargeWithCustomerPaymentPlan> response = ckoClient.chargeService.createPaymentPlanWithCardCharge(TestHelper.getCardChargeWithNewPaymentPlan());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		Response<OkResponse> updateResponse = ckoClient.chargeService.updateCustomerPaymentPlan(TestHelper.getCustomerPaymentPlanUpdate(), response.model.customerPaymentPlans[0].customerPlanId);
		
		assertEquals(false, updateResponse.hasError);
		assertEquals(200, updateResponse.httpStatus);			
		
		assertEquals(updateResponse.model.message, "ok");
	}
	
	@Test
	public void cancelCustomerPaymentPlanTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {		
		Response<ChargeWithCustomerPaymentPlan> response = ckoClient.chargeService.createPaymentPlanWithCardCharge(TestHelper.getCardChargeWithNewPaymentPlan());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		Response<OkResponse> updateResponse = ckoClient.chargeService.cancelCustomerPaymentPlan(response.model.customerPaymentPlans[0].customerPlanId);
		
		assertEquals(false, updateResponse.hasError);
		assertEquals(200, updateResponse.httpStatus);			
		
		assertEquals(updateResponse.model.message, "ok");
	}
	
	@Test
	public void getCustomerPaymentPlanTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {	
		Response<ChargeWithCustomerPaymentPlan> response = ckoClient.chargeService.createPaymentPlanWithCardCharge(TestHelper.getCardChargeWithNewPaymentPlan());
		
		assertEquals(false, response.hasError);
		assertEquals(200, response.httpStatus);
		
		Response<PaymentPlanGet> getResponse = ckoClient.recurringPaymentsService.getPaymentPlan(response.model.customerPaymentPlans[0].planId);
		
		assertEquals(false, getResponse.hasError);
		assertEquals(200, getResponse.httpStatus);			
		
		assertEquals(getResponse.model.name, "Recurring Plan 1");
		assertEquals(getResponse.model.value, 39900);
		assertEquals(getResponse.model.status, 1);
	}
	
//	
//	public Response<CustomerPaymentPlan> getCustomerPaymentPlan(String customerPlanId) throws IOException,JsonSyntaxException {
//		String url=String.format(ApiUrls.CUSTOMER_PAYMENT_PLAN_UPDATE, customerPlanId);
//		return httpClient.getRequest(url, AppSettings.secretKey,CustomerPaymentPlan.class);
//    }
}