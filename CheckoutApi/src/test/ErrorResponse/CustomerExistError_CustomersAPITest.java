package test.ErrorResponse;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import test.CustomerHelper;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;


public class CustomerExistError_CustomersAPITest {
	static CheckoutClient ckoClient;
	@Before
	public void setUp() throws Exception {
		
		ckoClient=new CheckoutClient("sk_test_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_test_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");;

	}

	@Test
	public void createCustomerRequestTest_ExistError() throws JsonSyntaxException, IOException {
		Response<Customer> customerResponse = null;
		CustomerCreate customerPayload = new CustomerCreate();

		customerPayload.email=CustomerHelper.createRandomEmail();
		customerPayload.name=CustomerHelper.generateName();
		customerPayload.phoneNumber="242223112226";

		customerResponse = ckoClient.CustomerService.createCustomer(customerPayload);
		assertEquals(true, customerResponse.getHasError());
		assertEquals(false, customerResponse.getHttpStatus() == 200);
		assertEquals(
				true,
				customerResponse.error.message
						.contains("A customer with a combination of email address and phone number already exists"));

	}

}
