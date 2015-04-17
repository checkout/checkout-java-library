package controllers;

import java.util.ArrayList;

import exception.ValidationFailException;

public class PropertyValidator {
 public static String validationError="Validation failed, there are required fields need to be filled in";
 ApiHttpClient httpRequest=null;
 
 public PropertyValidator(){
	 httpRequest=new ApiHttpClient();
	 
 }
 
 public void CheckErrors(ArrayList<String> error) {
   
			if (!error.isEmpty()) {

				throw new ValidationFailException(validationError+ "\n" + error);

			}
	}
}
