package test.customerservice;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import test.TestHelper;

import com.checkout.APIClient;
import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.customer.request.CustomerCreate;
import com.checkout.api.services.customer.request.CustomerListGet;
import com.checkout.api.services.customer.response.Customer;
import com.checkout.api.services.customer.response.CustomerList;
import com.checkout.api.services.shared.OkResponse;
import com.checkout.api.services.shared.Response;
import com.google.gson.JsonSyntaxException;


public class CustomerServiceTests {

	APIClient ckoClient;
	
	@Before
	public void setUp() throws Exception {
		ckoClient = new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50",true);
	}

	@Test
	public void CreateCustomerTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
		
		CustomerCreate customerCreatePayload= TestHelper.getCustomerCreateModel();
		Response<Customer> customerResponse =  ckoClient.customerService.createCustomer(customerCreatePayload);
			
		assertEquals(false, customerResponse.hasError);
		assertEquals(200, customerResponse.httpStatus);
		ValidateCustomerResponse(customerCreatePayload, customerResponse.model);
		
	}

	
	@Test
	public void UpdateCustomerTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {   
		
		Response<Customer> customerResponse = ckoClient.customerService.createCustomer(TestHelper.getCustomerCreateModel());		
		Response<OkResponse> updateResponse = ckoClient.customerService.updateCustomer(customerResponse.model.id, TestHelper.getCustomerUpdateModel());

		assertEquals(false, updateResponse.hasError);
		assertEquals(200, updateResponse.httpStatus);
		assertEquals(updateResponse.model.message,"ok");
	}

	@Test
	public void GetCustomerRequestTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
				
		CustomerCreate customerCreatePayload= TestHelper.getCustomerCreateModel();
		Response<Customer> customerCreateResponse = ckoClient.customerService.createCustomer(customerCreatePayload);
		
		Response<Customer> customerResponse = ckoClient.customerService.getCustomer(customerCreateResponse.model.id);
				
		assertEquals(false, customerResponse.hasError);
		assertEquals(200, customerResponse.httpStatus);
		
		ValidateCustomerResponse(customerCreatePayload, customerResponse.model);	
	}

	@Test
	public void GetCustomerListRequestTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date()); 
     	cal.add(Calendar.SECOND, -20);
     	
		CustomerListGet customerListRequest =new CustomerListGet();
		customerListRequest.count = 2;
		customerListRequest.offset = 1;
		customerListRequest.fromDate = cal.getTime(); 
		    
		CustomerCreate customerCreatePayload1= TestHelper.getCustomerCreateModel();
		CustomerCreate customerCreatePayload2= TestHelper.getCustomerCreateModel();
		
		Response<Customer> customerCreateResponse1 = ckoClient.customerService.createCustomer(customerCreatePayload1);
		Response<Customer> customerCreateResponse2 = ckoClient.customerService.createCustomer(customerCreatePayload2);
	    
		customerListRequest.toDate = new Date();
		
		Response<CustomerList> customerListResponse = ckoClient.customerService.getCustomerList(customerListRequest);

		assertEquals(false, customerListResponse.hasError);
		assertEquals(200, customerListResponse.httpStatus);
		assertEquals(2,customerListResponse.model.count);
		
		assertEquals(customerListResponse.model.data.get(0).id,customerCreateResponse2.model.id);
		ValidateCustomerResponse(customerCreatePayload2, customerListResponse.model.data.get(0));	
		
		assertEquals(customerListResponse.model.data.get(1).id,customerCreateResponse1.model.id);
		ValidateCustomerResponse(customerCreatePayload1, customerListResponse.model.data.get(1));	
		
	}
	
	@Test
	public void DeleteCustomerRequestTest() throws JsonSyntaxException, IOException, InstantiationException, IllegalAccessException {
	
		Response<Customer> customerCreateResponse = ckoClient.customerService.createCustomer(TestHelper.getCustomerCreateModel());
		Response<OkResponse> deleteResponse = ckoClient.customerService.deleteCustomer(customerCreateResponse.model.id);

		assertEquals(false, deleteResponse.hasError);
		assertEquals(200, deleteResponse.httpStatus);
		assertEquals(deleteResponse.model.message,"ok");
	}
	
	
	private void ValidateCustomerResponse(CustomerCreate payload,Customer customerResponse) {
		Card cardResponse = customerResponse.cards.data.get(0);
		
		assertTrue(customerResponse.id.startsWith("cust_"));
		assertEquals(payload.email,customerResponse.email);
		assertEquals(payload.name, customerResponse.name);
		assertEquals(payload.description, customerResponse.description);
		assertEquals(payload.phone.countryCode,customerResponse.phone.countryCode);
		assertEquals(payload.phone.number,customerResponse.phone.number);
		assertEquals(payload.metadata, customerResponse.metadata);
		assertNotNull(customerResponse.defaultCard);
		
		assertEquals(customerResponse.cards.count, 1);
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

}
