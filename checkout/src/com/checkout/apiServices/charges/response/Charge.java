package com.checkout.apiServices.charges.response;

import java.util.List;
import java.util.Map;

import com.checkout.apiServices.sharedModels.Address;
import com.checkout.apiServices.sharedModels.CardResponseModel;

public class Charge {
public String id;
public String chargeMode;
public String autoCapture;
public String autoCapTime;
public String liveMode;
public String created;
public int value;
public String currency;
public String email;
public String trackId;
public String customerIp;
public String transactionIndicator;
public CardResponseModel card;
public String responseMessage;
public String responseAdvancedInfo;
public String responseCode;
public String description;
public Map<String,String> metadata;
public String status;
public boolean iscascaded;
public String authCode;
public Address shippingDetails;
public List<ProductsModel> products;
public String udf1;
public String udf2;
public String udf3;
public String udf4;
public String udf5;
}
