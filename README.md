### Requirements

Java 1.7 and later

### How to use the library

Add the latest **checkout-java-v{version number}.jar** file to your class path. . The latest JAR file for Checkout library resides in the **releases\latest\** folder of the project.

### Example

Import the **APIClient.java** in your code as below:   
```
import com.checkout.APIClient;;
```

You will be required to set the secret key when initialising a new **APIClient** instance. You will also have option for other configurations defined in **AppSettings.java** file. There are two constructors available for configuration:

```html
APIClient(String secretKey)
APIClient(String secretKey, boolean debugMode,int connectTimeout,int readTimeout)
```


If **DebugMode** is set to true, the program will log the response result to console.

By default both **connectTimeout** and **readTimeout** set to 60 seconds. You got option to change them as needed.

**Create payment token**

```html

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.checkout.APIClient;
import com.checkout.apiServices.charges.response.ProductsModel;
import com.checkout.apiServices.sharedModels.Address;
import com.checkout.apiServices.sharedModels.Phone;
import com.checkout.apiServices.sharedModels.Response;
import com.checkout.apiServices.tokens.request.PaymentTokenCreate;
import com.checkout.apiServices.tokens.response.PaymentToken;
import com.checkout.exception.CKOException;

public class Example {

	public static void main(String[] args) throws IOException{

		APIClient ckoAPIClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50",true,60,60);
		
		PaymentTokenCreate tokenPayload= new PaymentTokenCreate();
		
		tokenPayload.value=100;
		tokenPayload.currency="GBP";
		tokenPayload.autoCapture="N";
		tokenPayload.customerIp="88.216.3.135";
		tokenPayload.description="test";
		
		tokenPayload.metadata = new HashMap<String,String>();
		tokenPayload.metadata.put("key1", "test value");
		
		tokenPayload.products = new ArrayList<ProductsModel>();
		 ProductsModel product1 =new ProductsModel();
		 product1.description= "A4 office paper";
		 product1.name="a4 white copy paper";
		 product1.quantity="1";
		 product1.shippingCost="10";
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
		
		Response<PaymentToken> tokenResponse = null;
		
		
		 try {
			  
			 tokenResponse = ckoAPIClient.tokenService.createPaymentToken(tokenPayload);
			 
		 } catch (CKOException e) {
	            e.printStackTrace();
		 }
		 
		if(!tokenResponse.hasError){
			String paymentTokenId = tokenResponse.model.id; //payment token id retrieved from the response model
			//...
		}else{
			// Api has returned an error. You can access the error details with the error property on the response object.
			// tokenResponse.error
		}
		
	}
}
```

**Verify charge by payment token**

```html
import java.io.IOException;

import com.checkout.APIClient;
import com.checkout.apiServices.charges.response.Charge;
import com.checkout.apiServices.sharedModels.Response;
import com.checkout.exception.CKOException;


public class Example {

	public static void main(String[] args) throws IOException{

		APIClient ckoAPIClient= new APIClient("sk_CC937715-4F68-4306-BCBE-640B249A4D50",true,60,60);
		
		String paymentToken ="pay_tok_7a66140a-ffc9-48a7-80c3-6e1b70e8076d";	// enter the payment token for the charge to be verified.
		
		Response<Charge> chargeResponse=null;
		
		 try {
			  
			 chargeResponse = ckoAPIClient.chargeService.verifyCharge(paymentToken);
			 
		 } catch (CKOException e) {
	            e.printStackTrace();
		 }
		 
		if(!chargeResponse.hasError){
			String paymentTokenId = chargeResponse.model.id; //payment token id retrieved from the response model
			//...
		}else{
			// Api has returned an error. You can access the error details with the error property on the response object.
			// chargeResponse.error
		}
		
	}
}
```

### Logging

For logging **Apache Commons Logging** has been used. If you enable logging all the http request and responses will be logged in the console.   

logger.info("id :"+ chargeId);`

### Unit Tests

All the unit test written with JUnit (v4) and resides in the test package.