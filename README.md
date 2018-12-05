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

### Logging

For logging **Apache Commons Logging** has been used. If you enable logging all the http request and responses will be logged in file called 'Log.log' at the root of the application.   

logger.info("id :"+ chargeId);`

### Unit Tests

All the unit test written with JUnit (v4) and resides in the test package.
