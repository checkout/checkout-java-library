### Requirements

Java 1.7 and later

### How to use the library

Add the latest **checkout-java-v{version number}.jar** file to your class path. . The latest JAR file for Checkout library resides in the **releases\latest\** folder of the project.

### Example

Import the **checkout.java** in your code as below:   
```
import checkout.Checkout;
```

You will be required to set the secret key when initialising a new **Checkout** instance. You will also have option for other configurations defined in **AppSettings.java** file. There are two constructors available for configuration:

```html
Checkout(String secretKey)
Checkout(String secretKey, boolean debugMode,int connectTimeout,int readTimeout)
```


If **DebugMode** is set to true, the program will log the response result to console.

By default both **connectTimeout** and **readTimeout** set to 60 seconds. You got option to change them as needed.
```html
package test.example;

import checkout.Checkout;
import checkout.exception.CKOException;
import apiServices.charges.response.Charge;
import apiServices.sharedModels.Response;

public class Example {

	public static void main(String[] args){

		Checkout ckoClient= new Checkout("sk_CC937715-4F68-4306-BCBE-640B249A4D50");

		String paymentToken ="pay_tok_7a66140a-ffc9-48a7-80c3-6e1b70e8076d";	// enter the payment token for the charge to be verified.

		Response<charge> chargeResponse=null;

		 try {

			 chargeResponse = ckoClient.chargeService.verifyCharge(paymentToken);

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