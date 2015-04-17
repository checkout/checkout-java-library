package apiServices.charges.request;

import java.util.List;
import java.util.Map;

import apiServices.charges.response.ProductsModel;
import apiServices.sharedModels.Address;

public class BaseCharge{

	public static final String Yes = "y";
	
	public BaseCharge(){
		  autoCapture = Yes;
		  transactionIndicator= "1"; //Default is REGULAR
		  chargeMode = "1";			//Default mode is no 3D
	}

	public String currency;
	public int value;
	public String chargeMode;
	public String trackId;
	public String transactionIndicator;
	public String customerIp;
	public String description;
	public String autoCapture;
	public String autoCapTime;
	public String customerId;
	public String email;

	public List<ProductsModel> products;
	public Address shippingDetails;
	public Map<String,String> metadata;
	 public String Udf1;
	 public String Udf2;
	 public String Udf3;
	 public String Udf4;
	 public String Udf5;
}
