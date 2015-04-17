package apiServices.customers;

import java.io.IOException;

import utilities.HttpMethods;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import apiServices.sharedModels.OkResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpClient;
import controllers.ApiUrls;
import controllers.AppSettings;
import exception.CKOException;

public class CustomerService{

	public Response<Customer> createCustomer(CustomerCreate customerPayload) throws CKOException {

		Response<Customer> customerResponse = null;
		Gson gson = new Gson();
		
		ApiHttpClient httpRequest = new ApiHttpClient();

		try {

			String payloadJson = gson.toJson(customerPayload);
			httpRequest.createConnection(ApiUrls.CustomersApiUri,AppSettings.secretKey, HttpMethods.Post, payloadJson);
			customerResponse = httpRequest.getResponse(Customer.class);
			
		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return customerResponse;
	}

	public Response<OkResponse> updateCustomer(CustomerUpdate customerPayload) throws CKOException {
		Response<OkResponse> updateResponse = null;
		Gson gson = new Gson();
		ApiHttpClient httpRequest = new ApiHttpClient();
		
		 String CustomerApiUri = ApiUrls.CustomersApiUri +"/"+ customerPayload.customerId;
		
		try {
			
			String payloadJson = gson.toJson(customerPayload);
			httpRequest.createConnection(CustomerApiUri, AppSettings.secretKey, HttpMethods.Put,payloadJson);
			updateResponse = httpRequest.getResponse(OkResponse.class);

		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);

		} catch (JsonSyntaxException e) {
			throw new CKOException(e.getMessage(), "JsonSyntaxException", e);
		}
		return updateResponse;

	}

	public Response<Customer> getCustomer(String customerId) throws CKOException {
		Response<Customer> customerResponse = null;
		ApiHttpClient httpRequest = new ApiHttpClient();
		
		String CustomerApiUri = ApiUrls.CustomersApiUri +"/"+customerId;
		
		try {
			
			httpRequest.createGetConnection(CustomerApiUri, AppSettings.secretKey, HttpMethods.Get);
			customerResponse = httpRequest.getResponse(Customer.class);

		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);
		} catch (CKOException e) {

			throw new CKOException(e.getMessage(), "CKOException", e);
		}
		return customerResponse;
	}

	public Response<CustomerList> getCustomerList()throws CKOException {
		Response<CustomerList> customerListResponse = null;
		ApiHttpClient httpRequest = new ApiHttpClient();

		///String CustomerApiUri = ApiUrls.CustomersApiUri +"/"+customerId;
		
		try {
			
			httpRequest.createGetConnection(ApiUrls.CustomersApiUri,AppSettings.secretKey,  HttpMethods.Get);
			customerListResponse = httpRequest.getResponse(CustomerList.class);

		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);
		} catch (CKOException e) {

			throw new CKOException(e.getMessage(), "CKOException", e);
		}
		return customerListResponse;
	}

	public Response<OkResponse> deleteCustomer(String customerId) throws CKOException {
		Response<OkResponse> deleteResponse = null;
		ApiHttpClient httpRequest = new ApiHttpClient();
		
		String CustomerApiUri = ApiUrls.CustomersApiUri +"/"+customerId;
		
		try {
			
			httpRequest.createGetConnection(CustomerApiUri, AppSettings.secretKey, HttpMethods.Delete);
			deleteResponse = httpRequest.getResponse(OkResponse.class);

		} catch (IOException e) {
			throw new CKOException(e.getMessage(), "IOException", e);
		} catch (CKOException e) {

			throw new CKOException(e.getMessage(), "CKOException", e);
		}
		return deleteResponse;

	}
}
