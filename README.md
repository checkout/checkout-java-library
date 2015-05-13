### Requirements

Java 1.7 and later

### How to use the library

Add the latest **checkout-java-v{version number}.jar** file to your class path. . The latest JAR file for Checkout library resides in the **releases\latest\** folder of the project.

### Example

Import the **APIClient.java** in your code as below:   
```
import com.checkout.APIClient;
```

You will be required to set the secret key when initialising a new **APIClient** instance. You will also have option for other configurations defined in **AppSettings.java** file. There are many constructors available for configuration:

```html
APIClient(String secretKey,Environment env, boolean debugMode,int connectTimeout,int readTimeout)
APIClient(String secretKey,Environment env,boolean debugMode)
APIClient(String secretKey,Environment env)
APIClient(String secretKey,boolean debugMode) 
APIClient(String secretKey)
```

If **DebugMode** is set to true, the program will trace the request and response result to a log file called 'Log.log' at the root of the application.

By default both **connectTimeout** and **readTimeout** set to 60 seconds. You got option to change them as needed.

**Create payment token**

```
public class Example {

	public static void main(String[] args) {
			
		// Create payload
	    PaymentTokenCreate tokenPayload= new PaymentTokenCreate();

	    tokenPayload.value="100";
	    tokenPayload.currency="GBP";
	    tokenPayload.autoCapture="N";
	    tokenPayload.customerIp="88.216.3.135";
	    tokenPayload.description="test";

	    tokenPayload.metadata = new HashMap<String,String>();
	    tokenPayload.metadata.put("key1", "test value");

	    tokenPayload.products = new ArrayList<Product>();
	    Product product1 =new Product();
	    product1.description= "A4 office paper";
	    product1.name="a4 white copy paper";
	    product1.quantity=1;
	    product1.shippingCost=10;
	    product1.sku= "ABC123";
	    product1.trackingUrl="http://www.tracker.com";
	    tokenPayload.products.add(product1);

	    tokenPayload.shippingDetails = new Address();
	    tokenPayload.shippingDetails.addressLine1 = "1 Glading Fields";
	    tokenPayload.shippingDetails.postcode = "N16 2BR";
	    tokenPayload.shippingDetails.country = "GB";
	    tokenPayload.shippingDetails.city = "London";

	    tokenPayload.shippingDetails.phone = new Phone();
	    tokenPayload.shippingDetails.phone.countryCode="44"; //Phone country code
	    tokenPayload.shippingDetails.phone.number = "203 583 44 55";

	    try {

	        // Create APIClient instance with your secret key
	        APIClient ckoAPIClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50",Environment.LIVE);

	        // Submit your request and receive an apiResponse
	        Response<PaymentToken> apiResponse = ckoAPIClient.tokenService.createPaymentToken(tokenPayload);

	        if(!apiResponse.hasError){

	            // Access the response object retrieved from the api
	            PaymentToken paymentToken = apiResponse.model; 

	        }else{
	            // Api has returned an error object. You can access the details in the error property of the apiResponse.
	            // apiResponse.error
	        }

	    } catch (Exception e) {

	    }

	}
}
```

**Verify charge by payment token**

```
public class Example {

	public static void main(String[] args) {
		
		// Create payload
	    String paymentToken = "pay_tok_7a66140a-ffc9-48a7-80c3-6e1b70e8076d";

	     try {

	        // Create APIClient instance with your secret key
	        APIClient ckoAPIClient= new APIClient("sk_093F4C8D-E608-4B8D-9B39-8C2491345864",Environment.LIVE);

	        // Submit your request and receive an apiResponse
	        Response<Charge> apiResponse = ckoAPIClient.chargeService.verifyCharge(paymentToken);

	        if(!apiResponse.hasError){

	            // Access the response object retrieved from the api
	            Charge charge = apiResponse.model; 

	        }else{
	            // Api has returned an error object. You can access the details in the error property of the apiResponse.
	            // apiResponse.error
	        }

	    } catch (Exception e) {

	    }
	}
}
```

### Logging

For logging **Apache Commons Logging** has been used. If you enable logging all the http request and responses will be logged in file called 'Log.log' at the root of the application.   

logger.info("id :"+ chargeId);`

### Unit Tests

All the unit test written with JUnit (v4) and resides in the test package.
