package test.CustomerService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import test.CustomerHelper;
import test.TestHelper;
import apiServices.customers.CustomerService;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.request.CustomerDelete;
import apiServices.customers.request.CustomerGet;
import apiServices.customers.request.CustomerUpdate;
import apiServices.customers.response.Customer;
import apiServices.customers.response.CustomerList;
import apiServices.sharedModels.CardResponseModel;
import apiServices.sharedModels.DeleteResponse;
import apiServices.sharedModels.Response;
import apiServices.sharedModels.OkResponse;
import exception.CKOException;


public class CustomerServiceTests {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void CreateCustomerTest() throws CKOException {
		
		CustomerCreate payload= TestHelper.getCustomerCreateModel();

		Response<Customer> customerResponse = new CustomerService().createCustomer(payload);
		
		CardResponseModel cardResponse = customerResponse.Model().cards.data.get(0);
		
		assertEquals(false, customerResponse.hasError);
		assertEquals(200, customerResponse.httpStatus);
		assertTrue(customerResponse.Model().id.startsWith("cust_"));
		assertEquals(payload.email,customerResponse.Model().email);
		assertEquals(payload.name, customerResponse.Model().name);
		assertEquals(payload.description, customerResponse.Model().description);
		assertEquals(payload.phone.countryCode,customerResponse.Model().phone.countryCode);
		assertEquals(payload.phone.number,customerResponse.Model().phone.number);
		assertEquals(payload.metadata, customerResponse.Model().metadata);
		
		assertEquals(customerResponse.Model().cards.count, 1);
		assertTrue(payload.card.number.endsWith(cardResponse.last4));
		assertEquals(cardResponse.paymentMethod,"VISA");
		assertNotNull(cardResponse.fingerprint);
		assertNotNull(cardResponse.customerId);
		assertEquals(payload.card.name,cardResponse.name);
		assertEquals(payload.card.expiryMonth,cardResponse.expiryMonth);
		assertEquals(payload.card.expiryYear,cardResponse.expiryYear);
		assertEquals(payload.card.billingDetails.addressLine1, cardResponse.billingDetails.addressLine1);
		assertEquals(payload.card.billingDetails.addressLine2, cardResponse.billingDetails.addressLine2);
		assertEquals(payload.card.billingDetails.city, cardResponse.billingDetails.city);
		assertEquals(payload.card.billingDetails.country, cardResponse.billingDetails.country);
		assertEquals(payload.card.billingDetails.phone.countryCode, cardResponse.billingDetails.phone.countryCode);
		assertEquals(payload.card.billingDetails.phone.number, cardResponse.billingDetails.phone.number);
		assertEquals(payload.card.billingDetails.postcode, cardResponse.billingDetails.postcode);
		assertEquals(payload.card.billingDetails.state, cardResponse.billingDetails.state);
		
	}

	@Test
	public void UpdateCustomerTest() throws CKOException {
	    CustomerService customerService= new CustomerService();
	    
		Response<Customer> customerResponse = customerService.createCustomer(TestHelper.getCustomerCreateModel());
		
		CustomerUpdate payload=new CustomerUpdate();
		payload.customerId = customerResponse.Model().id;
		payload.name= TestHelper.getRandomString();
		payload.description= TestHelper.getRandomString();
		payload.email = TestHelper.getRandomEmail();
		payload.phone =  TestHelper.getRandomPhone();
		payload.metadata=TestHelper.getRandomMetadata();
				
				
		Response<OkResponse> updateResponse = customerService.updateCustomer(payload);

		assertEquals(false, customerResponse.hasError);
		assertEquals(200, customerResponse.httpStatus);
		assertEquals(updateResponse.Model().message,"ok");
	}

	@Test
	public void GetCustomerRequestTest() throws CKOException {
		
		CustomerService customerService= new CustomerService();
		
		CustomerCreate payload= TestHelper.getCustomerCreateModel();
		
		Response<Customer> customerCreateResponse = customerService.createCustomer(payload);
		
		Response<Customer> customerResponse = customerService.getCustomer(customerCreateResponse.Model().id);

CardResponseModel cardResponse = customerResponse.Model().cards.data.get(0);
		
assertEquals(false, customerResponse.hasError);
assertEquals(200, customerResponse.httpStatus);
		assertTrue(customerResponse.Model().id.startsWith("cust_"));
		assertEquals(payload.email,customerResponse.Model().email);
		assertEquals(payload.name, customerResponse.Model().name);
		assertEquals(payload.description, customerResponse.Model().description);
		assertEquals(payload.phone.countryCode,customerResponse.Model().phone.countryCode);
		assertEquals(payload.phone.number,customerResponse.Model().phone.number);
		assertEquals(payload.metadata, customerResponse.Model().metadata);
		
		assertEquals(customerResponse.Model().cards.count, 1);
		assertTrue(payload.card.number.endsWith(cardResponse.last4));
		assertEquals(cardResponse.paymentMethod,"VISA");
		assertNotNull(cardResponse.fingerprint);
		assertNotNull(cardResponse.customerId);
		assertEquals(payload.card.name,cardResponse.name);
		assertEquals(payload.card.expiryMonth,cardResponse.expiryMonth);
		assertEquals(payload.card.expiryYear,cardResponse.expiryYear);
		assertEquals(payload.card.billingDetails.addressLine1, cardResponse.billingDetails.addressLine1);
		assertEquals(payload.card.billingDetails.addressLine2, cardResponse.billingDetails.addressLine2);
		assertEquals(payload.card.billingDetails.city, cardResponse.billingDetails.city);
		assertEquals(payload.card.billingDetails.country, cardResponse.billingDetails.country);
		assertEquals(payload.card.billingDetails.phone.countryCode, cardResponse.billingDetails.phone.countryCode);
		assertEquals(payload.card.billingDetails.phone.number, cardResponse.billingDetails.phone.number);
		assertEquals(payload.card.billingDetails.postcode, cardResponse.billingDetails.postcode);
		assertEquals(payload.card.billingDetails.state, cardResponse.billingDetails.state);	
	}

	
	@Test
	public void GetCustomerListRequestTest() throws CKOException {
		
		CustomerService customerService= new CustomerService();
		CustomerCreate payload= TestHelper.getCustomerCreateModel();
		
		Response<Customer> customerCreateResponse = customerService.createCustomer(payload);
		
		Response<CustomerList> customerListResponse = customerService.getCustomerList();

		assertEquals(false, customerListResponse.hasError);
		assertEquals(200, customerListResponse.httpStatus);
		//assertEquals(customerListResponse.getType().count,1);
		
		
	}

	@Test
	public void DeleteCustomerRequestTest() throws CKOException {
		CustomerService customerService= new CustomerService();
		Response<Customer> customerCreateResponse = customerService.createCustomer(TestHelper.getCustomerCreateModel());
		Response<OkResponse> deleteResponse = customerService.deleteCustomer(customerCreateResponse.Model().id);

		assertEquals(false, deleteResponse.hasError);
		assertEquals(200, deleteResponse.httpStatus);
		assertEquals(deleteResponse.Model().message,"ok");
	}

}
