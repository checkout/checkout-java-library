package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.customers.CustomerService;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class ValidationError_CustomersAPITest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void UpdateCustomerRequestTest() throws CKOException {
		CustomerUpdate customerPayload = new CustomerUpdate();
		customerPayload.name="grace";
		customerPayload.email="zwp1031@sina.com";
		customerPayload.phoneNumber="07592331690";
		customerPayload.customerId="cust_1b84sc22e-b548-47dc-91d5-e8d1f4a84ae7";

		CustomerService api = new CustomerService();
		Response<Customer> customerResponse = api
				.updateCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true,customerResponse.error.errors.contains("Invalid customer id"));

	}

	@Test
	public void GetCustomerRequestTest() throws CKOException {

		CustomerGet customerPayload = new CustomerGet();
		customerPayload.customerId="cust_7fad3d2ce-8575-4c34-b427-d3ef90917994";

		CustomerService api = new CustomerService();
		Response<Customer> customerResponse = null;

		customerResponse = api.getCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true,customerResponse.error.errors.contains("Invalid customer id"));

	}

}
