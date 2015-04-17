package apiServices.charges.request;

import java.util.Map;
import apiServices.charges.response.ProductsModel;
import apiServices.charges.response.ShippingDetails;

public class BaseCharge{

	public String customerId;
	public String email;
	public String currency;
	public int value;
	public String description;
	public String autoCapture;
	public String autoCaptime;	
	public ProductsModel products;
	public ShippingDetails shippingDetails;
	public Map<String,String> metadata;
}
