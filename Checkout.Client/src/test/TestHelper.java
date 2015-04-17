package test;

import java.util.*;

import apiServices.cards.response.Card;
import apiServices.charges.response.ProductsModel;
import apiServices.sharedModels.Address;
import apiServices.sharedModels.Phone;
import apiServices.tokens.request.PaymentTokenCreate;

public class TestHelper {
	
	public static String getRandomEmail(){
		 return UUID.randomUUID().toString()+"@test.com";
	}
	
	public static String getRandomString()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < chars.length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	
	public static Map<String,String> getRandomMetadata()
	{
		Map<String,String> metadata =new HashMap<String,String>();
		
		metadata.put("key1", getRandomString().substring(20));
		metadata.put("key2", getRandomString().substring(20));
		
		return metadata;
	}
	
	public static Address getRandomAddress() {
		Address address =new Address();
		
		address.addressLine1 = getRandomString();
		address.addressLine2 = getRandomString();
		address.postcode = getRandomString().substring(20);
		address.country = "US";
		address.city = getRandomString().substring(15);
		address.state = getRandomString();
		address.phone = getRandomPhone();
		return address;
	}
	
	public static  List<ProductsModel> getRandomProducts()
	{
		 List<ProductsModel> products = new ArrayList<ProductsModel>();
		 
		 ProductsModel product1 =new ProductsModel();
		 product1.description= getRandomString().substring(20);
		 product1.name=getRandomString().substring(20);
		 product1.quantity="1";
		 product1.shippingCost="10";
		 product1.sku= getRandomString().substring(25);
		 product1.trackingUrl="http://www.tracker.com";
				 
		 ProductsModel product2  =new ProductsModel();
		 product2.description= getRandomString().substring(20);
		 product2.name=getRandomString().substring(20);
		 product2.quantity="1";
		 product2.shippingCost="20";
		 product2.sku= getRandomString().substring(25);
		 product2.trackingUrl="http://www.tracker.com";
		 
		 products.add(product1);
		 products.add(product2);
		 
		return products;
	}

	public static Phone getRandomPhone() {

		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		Phone phone = new Phone();
		
		char[] chars = "1234567890".toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		
		phone.countryCode="44"; //Phone country code
		phone.number = sb.toString();
		
		return phone;
	}

	public static Card getCardModel(){
		Card card = new Card();
		
		card.name=getRandomString();
		card.number="4543474002249996";
		card.expiryMonth="06";
		card.expiryYear="2017";
		card.cvv="956";
		card.billingDetails = getRandomAddress();
		
		return card;
	}
	
	public static PaymentTokenCreate getPaymentTokenCreateModel(){
		PaymentTokenCreate tokenPayload=new PaymentTokenCreate();
		Random random = new Random();
		
		tokenPayload.value=random.nextInt(1000);
		tokenPayload.currency="USD";
		tokenPayload.autoCapture="N";
		tokenPayload.customerIp="88.216.3.135";
		tokenPayload.description="test";
		tokenPayload.metadata = getRandomMetadata(); 
		tokenPayload.products = getRandomProducts();
		tokenPayload.shippingDetails = getRandomAddress();
		
		return tokenPayload;
	}

}
