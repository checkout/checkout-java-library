package com.checkout.api.services.customer;


import java.io.IOException;

import com.checkout.api.services.BaseService;
import com.checkout.api.services.customer.request.CustomerCreate;
import com.checkout.api.services.customer.request.CustomerListGet;
import com.checkout.api.services.customer.request.CustomerUpdate;
import com.checkout.api.services.customer.response.Customer;
import com.checkout.api.services.customer.response.CustomerList;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.checkout.helpers.ApiUrls;
import com.checkout.helpers.AppSettings;
import com.checkout.helpers.DateTimeHelper;
import com.checkout.helpers.UrlHelper;
import com.google.gson.JsonSyntaxException;

public class CustomerService extends BaseService{
	
	public CustomerService(){
		super();
	}
	
	public Response<Customer> createCustomer(CustomerCreate payload)  throws IOException,JsonSyntaxException{		

		return httpClient.postRequest(ApiUrls.CUSTOMERS, AppSettings.secretKey, gson.toJson(payload),Customer.class);
	}

	public Response<OkResponse> updateCustomer(String customerId, CustomerUpdate payload) throws IOException,JsonSyntaxException{
		String apiUrl=String.format(ApiUrls.CUSTOMER, customerId);
		
		return httpClient.putRequest(apiUrl, AppSettings.secretKey, gson.toJson(payload),OkResponse.class);
	}

	public Response<Customer> getCustomer(String customerId)  throws IOException,JsonSyntaxException {
		String apiUrl=String.format(ApiUrls.CUSTOMER, customerId);
		
		return httpClient.getRequest(apiUrl, AppSettings.secretKey, Customer.class);
	}

	public Response<CustomerList> getCustomerList(CustomerListGet payload) throws IOException,JsonSyntaxException {
		String apiUrl=ApiUrls.CUSTOMERS;

	        if (payload.count > 0)
	        {
	            apiUrl = UrlHelper.addParameterToUrl(apiUrl, "count", Integer.toString(payload.count));
	        }

	        if (payload.offset >0)
	        {
	            apiUrl = UrlHelper.addParameterToUrl(apiUrl, "offset", Integer.toString(payload.offset));
	        }

	        if (payload.fromDate!=null)
	        {
	            apiUrl = UrlHelper.addParameterToUrl(apiUrl, "fromDate", DateTimeHelper.FormatAsUtc(payload.fromDate));
	        }

	        if (payload.toDate !=null)
	        {
	            apiUrl = UrlHelper.addParameterToUrl(apiUrl, "toDate", DateTimeHelper.FormatAsUtc(payload.toDate));
	        }

	return	httpClient.getRequest(apiUrl,AppSettings.secretKey, CustomerList.class);
		
	}

	public Response<OkResponse> deleteCustomer(String customerId) throws IOException,JsonSyntaxException {
		String apiUrl=String.format(ApiUrls.CUSTOMER, customerId);
		
		return httpClient.deleteRequest(apiUrl, AppSettings.secretKey,OkResponse.class );
	}
}
