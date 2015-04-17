package test;


import java.util.UUID;

import org.junit.Test;

import apiServices.cards.response.Card;
import apiServices.customers.CustomerService;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;
import exception.CKOException;

public class CustomerHelper {

	@Test
	public static Response<Customer> CreateCustomerRequestTest() throws CKOException {
		CustomerCreate customerPayload = new CustomerCreate();
		customerPayload.email= TestHelper.getRandomEmail();
		customerPayload.name= TestHelper.getRandomString();
		customerPayload.phone= TestHelper.getRandomPhone();			
		
		Card card=new Card();
		customerPayload.card=card;
		card.name="zwp";
		card.number="5313581000123430";
		card.expiryMonth="06";
		card.expiryYear="2017";
		card.cvv="257";
		
		CustomerService api = new CustomerService();
				
		return api.createCustomer(customerPayload);
	
	}
}
