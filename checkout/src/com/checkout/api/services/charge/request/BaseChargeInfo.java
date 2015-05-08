package com.checkout.api.services.charge.request;

import java.util.Map;

public class BaseChargeInfo{

	public BaseChargeInfo(){
    }

	public String description;
	public String trackId;
	public String udf1;
	public String udf2;
	public String udf3;
	public String udf4;
	public String udf5;
	
	public Map<String,String> metadata;
}
