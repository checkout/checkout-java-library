package com.checkout.api.services.recurringPayments;

import java.io.IOException;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.recurringPayments.request.CustomerPaymentPlanUpdate;
import com.checkout.api.services.recurringPayments.request.PaymentPlanCreate;
import com.checkout.api.services.recurringPayments.request.PaymentPlanUpdate;
import com.checkout.api.services.recurringPayments.response.CustomerPaymentPlan;
import com.checkout.api.services.recurringPayments.response.PaymentPlanGet;
import com.checkout.api.services.recurringPayments.response.PaymentPlans;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.google.gson.JsonSyntaxException;

public class RecurringPaymentsService extends BaseService {
	
	public Response<PaymentPlans> createPaymentPlan(PaymentPlanCreate payload) throws JsonSyntaxException,IOException {
		return httpClient.postRequest(ApiUrls.RECURRING_PAYMENTS,AppSettings.secretKey, gson.toJson(payload),PaymentPlans.class);
	}
	
	public Response<OkResponse> updatePaymentPlan(PaymentPlanUpdate payload, String planId) throws JsonSyntaxException,IOException {
		String url=String.format(ApiUrls.RECURRING_PAYMENTS_UPDATE, planId);
		
		return httpClient.putRequest(url,AppSettings.secretKey, gson.toJson(payload),OkResponse.class);
	}
	
	public Response<OkResponse> cancelPaymentPlan(String planId) throws JsonSyntaxException,IOException {
		String url=String.format(ApiUrls.RECURRING_PAYMENTS_UPDATE, planId);
		
		return httpClient.deleteRequest(url,AppSettings.secretKey,OkResponse.class);
	}
	
	public Response<PaymentPlanGet> getPaymentPlan(String planId) throws JsonSyntaxException,IOException {
		String url=String.format(ApiUrls.RECURRING_PAYMENTS_UPDATE, planId);
		
		return httpClient.getRequest(url,AppSettings.secretKey,PaymentPlanGet.class);
	}
	
	public Response<OkResponse> updateCustomerPaymentPlan(CustomerPaymentPlanUpdate payload, String customerPlanId) throws IOException,JsonSyntaxException {
		String url=String.format(ApiUrls.CUSTOMER_PAYMENT_PLAN_UPDATE, customerPlanId);
		return httpClient.postRequest(url, AppSettings.secretKey, gson.toJson(payload),OkResponse.class);
    }
	
	public Response<OkResponse> cancelCustomerPaymentPlan(String customerPlanId) throws IOException,JsonSyntaxException {
		String url=String.format(ApiUrls.CUSTOMER_PAYMENT_PLAN_UPDATE, customerPlanId);
		return httpClient.deleteRequest(url, AppSettings.secretKey,OkResponse.class);
    }
	
	public Response<CustomerPaymentPlan> getCustomerPaymentPlan(String customerPlanId) throws IOException,JsonSyntaxException {
		String url=String.format(ApiUrls.CUSTOMER_PAYMENT_PLAN_UPDATE, customerPlanId);
		return httpClient.getRequest(url, AppSettings.secretKey,CustomerPaymentPlan.class);
    }
}