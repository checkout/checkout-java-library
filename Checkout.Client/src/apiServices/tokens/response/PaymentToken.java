package apiServices.tokens.response;

import com.google.gson.annotations.SerializedName;

public class PaymentToken {
	@SerializedName("Id")
	public String id;
	
	@SerializedName("LiveMode")
	public boolean liveMode;
}
