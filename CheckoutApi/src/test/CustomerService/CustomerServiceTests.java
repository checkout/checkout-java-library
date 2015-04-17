package test.CustomerService;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonSyntaxException;
import controllers.CheckoutClient;
import test.CustomerHelper;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;

public class CustomerServiceTests {

	public static String cust_id="";
    public  CheckoutClient ckoClient;
	
    @Before
	public void setUp() throws Exception {
		ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50","pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");
	}

	@Test
	public void createCustomerRequestTest() throws JsonSyntaxException, IOException {
			
		Response<Customer> customerResponse = null;
		CustomerCreate customerPayload = new CustomerCreate();

		customerPayload.email=CustomerHelper.createRandomEmail();
		customerPayload.name=CustomerHelper.generateName();
		customerPayload.phoneNumber="42213121226";	
				
		customerResponse = ckoClient.CustomerService.createCustomer(customerPayload);
		
		cust_id=customerResponse.getType().id;
		
		assertEquals(false, customerResponse.getHasError());
		assertEquals(200, customerResponse.getHttpStatus());
		assertEquals(customerPayload.email,
				customerResponse.getType().email);
		assertEquals(customerPayload.name, customerResponse.getType().name);
		assertEquals(customerPayload.phoneNumber,
				customerResponse.getType().phoneNumber);
		
	}

	@Test
	public void updateCustomerRequestTest() throws JsonSyntaxException, IOException  {
		CustomerUpdate customerPayload = new CustomerUpdate();
		customerPayload.customerId=cust_id;
		customerPayload.name=CustomerHelper.createRandomEmail();
		customerPayload.email=CustomerHelper.createRandomEmail();
		customerPayload.phoneNumber="07592331690";
		
		Response<Customer> customerResponse = ckoClient.CustomerService.updateCustomer(customerPayload);

		assertEquals(false, customerResponse.getHasError());
		assertEquals(200, customerResponse.getHttpStatus());
		assertEquals(customerPayload.email,
				customerResponse.getType().email);
		assertEquals(customerPayload.name, customerResponse.getType().name);
		assertEquals(customerPayload.phoneNumber,
				customerResponse.getType().phoneNumber);		

	}

	@Test
	public void getCustomerRequestTest() throws JsonSyntaxException, IOException {

		CustomerGet customerPayload = new CustomerGet();
		customerPayload.customerId=cust_id;

		Response<Customer> customerResponse = null;

		customerResponse = ckoClient.CustomerService.getCustomer(customerPayload);

		assertEquals(false, customerResponse.getHasError());		
	}

	
	@Test
	public void getCustomerListRequestTest() throws JsonSyntaxException, IOException {

		Response<CustomerList> customerResponse = null;
		
		customerResponse = ckoClient.CustomerService.getCustomerList();

		assertEquals(false, customerResponse.getHasError());
		assertEquals("list", customerResponse.getType().object);
		
		
	}

	@Test
	public void deleteCustomerRequestTest() throws JsonSyntaxException, IOException {
		Response<Customer> customerResponse = CustomerHelper.createCustomerRequestTest();
		CustomerDelete customerPayload = new CustomerDelete();
		customerPayload.customerId=customerResponse.getType().id;
		Response<DeleteResponse> deleteResponse = null;
		
		deleteResponse = ckoClient.CustomerService.deleteCustomer(customerPayload);

		assertEquals(false, deleteResponse.getHasError());
		assertEquals(true, deleteResponse.getType().deleted);
		

	}

}
