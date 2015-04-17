package apiServices.payment_provider.response;


import java.util.Map;

public class CustomFields {
public String key;
public String dataType;
public String label;
public boolean required;
public int order;
public int minLength;
public int maxLength;
public Map<String,String> errorCodes;
public Map<String,String> lookupValues;

}
