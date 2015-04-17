package apiServices.customers.request;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONObject;

import com.google.gson.Gson;

import controllers.ApiHttpContext;

public class BaseCustomer extends ApiHttpContext {
	
	public String name;
	public String email;
	public String phoneNumber;
	public String description;
	public String metadata;
	public String customerId;
	
	public ArrayList<String> errorList=new ArrayList<String>();
	
	public BaseCustomer getResponse(JSONObject customer) {

		Gson gson = new Gson();
		BaseCustomer c = gson.fromJson(customer.toString(),
				BaseCustomer.class);

		for (Iterator<?> iter = customer.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			System.out.println(key + ":" + customer.get(key).toString());

		}
		return c;
	}
}
