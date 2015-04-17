package test.ErrorResponse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import apiServices.customers.CustomerService;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import exception.CKOException;
import exception.ValidationFailException;

public class RequiredPropertyError_CustomersAPITest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void CreateCustomerRequestTest_RequiredPropertyError()
			throws CKOException {
		CustomerCreate customerPayload = new CustomerCreate();

		boolean hasPropertyError = false;
		CustomerService api = new CustomerService();

		try {

			api.createCustomer(customerPayload);

		} catch (ValidationFailException e) {
		
			hasPropertyError = true;
		}catch(CKOException e)
		{
			hasPropertyError = true;
		}

		assertEquals(true, hasPropertyError);
		assertEquals(true, customerPayload.errorList.contains("email is required"));

	}

	@Test
	public void UpdateCustomerRequestTest_requiredPropertyError()
			throws CKOException {
		boolean hasError = false;
		CustomerUpdate customerPayload = new CustomerUpdate();
		CustomerService api = new CustomerService();

		try {

			api.updateCustomer(customerPayload);

		} catch (ValidationFailException e) {
		
			hasError = true;
		}
		assertEquals(true, hasError);
		assertEquals(true,
				customerPayload.errorList.contains("customer id is required"));
	}

	

	@Test
	public void GetCustomerRequestTest_requiredPropertyError() throws CKOException {
		CustomerGet customerPayload = new CustomerGet();
		boolean hasError = false;
		CustomerService api = new CustomerService();	
		try {
			api.getCustomer(customerPayload);
		} catch (ValidationFailException e) {
			hasError = true;
		}
		assertEquals(true, hasError);
		assertEquals(true, customerPayload.errorList.contains("customer id is required"));
	}

	@Test
	public void DeleteCustomerRequestTest_requiredPropertyError() throws CKOException {
		CustomerDelete customerPayload = new CustomerDelete();
		
		boolean hasError = false;
		CustomerService api = new CustomerService();
		try {
			api.deleteCustomer(customerPayload);

		} catch (ValidationFailException e) {
			hasError = true;
		}

		assertEquals(true, hasError);
		assertEquals(true,customerPayload.errorList.contains("customer id is required"));
	}

	
	

}
