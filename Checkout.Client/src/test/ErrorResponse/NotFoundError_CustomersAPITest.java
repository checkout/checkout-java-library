package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.customers.CustomerService;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class NotFoundError_CustomersAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void UpdateCustomerRequestTest_notFoundError() throws CKOException {
		CustomerUpdate customerPayload = new CustomerUpdate();
		customerPayload.customerId="cust_e09aada7-76f9-419a-884a-2ad7e5265271";

		CustomerService api = new CustomerService();

		Response<Customer> customerResponse = api.updateCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true, customerResponse.error.message.contains("Customer not found"));
	}
	
	@Test
	public void DeleteCustomerRequestTest_notFoundError() throws CKOException {
		CustomerDelete customerPayload = new CustomerDelete();
		customerPayload.customerId="cust_e09aada7-76f9-419a-884a-2ad7e5265271";

		CustomerService api = new CustomerService();
		Response<DeleteResponse> customerResponse = null;

		customerResponse = api.deleteCustomer(customerPayload);

		assertEquals(true, customerResponse.getHasError());
		assertEquals(true, customerResponse.error.message.contains("Customer not found"));

	}

}
