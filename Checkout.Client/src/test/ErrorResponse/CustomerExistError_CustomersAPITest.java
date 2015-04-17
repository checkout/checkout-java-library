package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import test.CustomerHelper;
import apiServices.customers.CustomerService;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class CustomerExistError_CustomersAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void CreateCustomerRequestTest_ExistError() throws CKOException {
		Response<Customer> customerResponse = null;
		CustomerCreate customerPayload = new CustomerCreate();

		customerPayload.email=CustomerHelper.createRandomEmail();
		customerPayload.name=CustomerHelper.generateName();
		customerPayload.phoneNumber="242223112226";

		CustomerService api = new CustomerService();

		customerResponse = api.createCustomer(customerPayload);
		assertEquals(true, customerResponse.getHasError());
		assertEquals(false, customerResponse.getHttpStatus() == 200);
		assertEquals(
				true,
				customerResponse.error.message
						.contains("A customer with a combination of email address and phone number already exists"));

	}

}
