package test;


import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import controllers.CheckoutClient;
import apiServices.cards.response.Card;
import apiServices.customers.request.CustomerCreate;
import apiServices.customers.response.Customer;
import apiServices.sharedModels.Response;

public class CustomerHelper {
	static CheckoutClient ckoClient=new CheckoutClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50", "pk_1ADBEB2D-2BEA-4F82-8ABC-EDE3A1201C8D");;
	@Test
	public static Response<Customer> createCustomerRequestTest() throws JsonSyntaxException, IOException{
		CustomerCreate customerPayload = new CustomerCreate();
		customerPayload.email=createRandomEmail();
		customerPayload.name=generateName();
		customerPayload.phoneNumber="42213121226";			
		
		Card card=new Card();
		customerPayload.card=card;
		card.name="zwp";
		card.number="5313581000123430";
		card.expiryMonth="06";
		card.expiryYear="2017";
		card.cvv="257";
				
		return ckoClient.CustomerService.createCustomer(customerPayload);
	
	}
	
	
	public static String createRandomEmail(){
		 int ran;
		 ran = 100 + (int)(Math.random() * ((10000 - 100) + 1));
		 return ran+"@checkout.com";
	}
	
	public static String generateName()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	
}
