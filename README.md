# checkout-java-library

Client library for CheckoutCom.

# Overview

The library is using the Java API of Checkout.com.

# Development

To build it:
  ./gradlew build

Or of course run the tests in IntelliJ as usual.

# Release

Use:
  ./gradlew clean release
to build and upload a new version.
This uploads the JAR to artifactory and updates the version number.

Then upgrade the version number where it's used, like in main app BuildConfig.groovy.


# Using it from Grails

In BuildConfig:
	compile 'com.transferwise:checkout-java-library:1.X'

# Example

Import the APIClient.java in your code as below:

import com.checkout.APIClient;
You will be required to set the secret key when initialising a new APIClient instance. You will also have option for other configurations defined in AppSettings.java file. There are many constructors available for configuration:

APIClient(String secretKey,Environment env, boolean debugMode,int connectTimeout,int readTimeout)
APIClient(String secretKey,Environment env,boolean debugMode)
APIClient(String secretKey,Environment env)
APIClient(String secretKey,boolean debugMode)
APIClient(String secretKey)
If DebugMode is set to true, the program will trace the request and response result to a log file called 'Log.log' at the root of the application.

By default both connectTimeout and readTimeout set to 60 seconds. You got option to change them as needed.

