package apiServices.customers;

import java.io.IOException;

import apiServices.customers.request.CustomerCreate;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import controllers.ApiHttpContext;
import controllers.ApiUrls;
import controllers.AppSettings;


public class CustomerService extends ApiUrls {

	public Response<Customer> createCustomer(
			CustomerCreate customerPayload) throws IOException,JsonSyntaxException {

		Response<Customer> customerResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();

			String payloadJson = gson.toJson(customerPayload);
			httpRequest.createPostConnection(CustomersApiUri,AppSettings.secretKey, "POST", payloadJson);
			customerResponse = httpRequest.getResponse(Customer.class);
		
		return customerResponse;
	}

	public Response<Customer> updateCustomer(
			CustomerUpdate customerPayload) throws IOException,JsonSyntaxException {
		Response<Customer> customerResponse = null;
		Gson gson = new Gson();
		
		ApiHttpContext httpRequest = new ApiHttpContext();
	
			String payloadJson = gson.toJson(customerPayload);
			String Url=String.format(ApiUrls.CustomerApiUri, customerPayload.customerId);
			httpRequest.createPostConnection(Url, AppSettings.secretKey, "PUT",payloadJson);
			customerResponse = httpRequest.getResponse(Customer.class);

		return customerResponse;

	}

	public Response<Customer> getCustomer(CustomerGet customerPayload) throws IOException,JsonSyntaxException {
		Response<Customer> customerResponse = null;
		
		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.CustomerApiUri, customerPayload.customerId);
			httpRequest.createGetConnection(Url, AppSettings.secretKey, "GET");
			customerResponse = httpRequest.getResponse(Customer.class);
		
		return customerResponse;
	}

	public Response<CustomerList> getCustomerList()throws IOException,JsonSyntaxException{
		Response<CustomerList> customerResponse = null;
		ApiHttpContext httpRequest = new ApiHttpContext();
		
			httpRequest.createGetConnection(CustomersApiUri,AppSettings.secretKey, "GET");

			customerResponse = httpRequest.getResponse(CustomerList.class);
		return customerResponse;
	}

	public Response<DeleteResponse> deleteCustomer(CustomerDelete customerPayload) throws IOException,JsonSyntaxException {
		Response<DeleteResponse> customerResponse = null;
		
		ApiHttpContext httpRequest = new ApiHttpContext();
		String Url=String.format(ApiUrls.CustomerApiUri, customerPayload.customerId);
			httpRequest.createGetConnection(Url, AppSettings.secretKey, "DELETE");

			customerResponse = httpRequest.getResponse(DeleteResponse.class);

		return customerResponse;

	}
}
