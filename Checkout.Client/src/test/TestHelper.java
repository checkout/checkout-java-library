package test;

import java.util.*;

import apiServices.cards.response.BillingDetails;
import apiServices.cards.response.Card;
import apiServices.charges.request.CardCharge;
import apiServices.charges.response.ProductsModel;
import apiServices.customers.request.CustomerCreate;
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
		 product1.shippingcost="10";
		 product1.sku= getRandomString().substring(25);
		 product1.trackingurl="http://www.tracker.com";
				 
		 ProductsModel product2  =new ProductsModel();
		 product2.description= getRandomString().substring(20);
		 product2.name=getRandomString().substring(20);
		 product2.quantity="1";
		 product2.shippingcost="20";
		 product2.sku= getRandomString().substring(25);
		 product2.trackingurl="http://www.tracker.com";
		 
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

	public static CustomerCreate getCustomerCreateModel(){
	
		CustomerCreate customer = new CustomerCreate();
		
		customer.email = getRandomEmail();
		customer.name = getRandomString();
		customer.phone = getRandomPhone();		
		customer.description = getRandomString();
		customer.metadata = getRandomMetadata(); 
		customer.card=getCardModel();
		
		return customer;
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

	public static CardCharge getCardChargeModel() {
		CardCharge chargePayload =new CardCharge();
		chargePayload.email = getRandomEmail();
		chargePayload.currency="usd";
		chargePayload.value=100;
		chargePayload.autoCapture="N";
		chargePayload.trackId= "TRK12345";
		chargePayload.customerIp="82.23.168.254";
		chargePayload.description= getRandomString().substring(20);
		chargePayload.card =getCardModel();
		chargePayload.metadata = getRandomMetadata();
		chargePayload.shippingDetails = getRandomAddress();
		chargePayload.products = getRandomProducts();
		chargePayload.Udf1=getRandomString().substring(20);
		chargePayload.Udf2=getRandomString().substring(20);
		chargePayload.Udf3=getRandomString().substring(20);
		chargePayload.Udf4=getRandomString().substring(20);
		chargePayload.Udf5=getRandomString().substring(20);
		
		return  chargePayload;
	}

	
}
