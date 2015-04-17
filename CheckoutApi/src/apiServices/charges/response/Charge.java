package apiServices.charges.response;

import java.util.List;
import java.util.Map;

import apiServices.sharedModels.CardResponseModel;


public class Charge {
public String object;
public String id;
public String livemode;
public String created;
public int value;
public String currency;
public String email;
public boolean paid;
public boolean refunded;
public boolean deferred;
public boolean expired;
public boolean captured;
public CardResponseModel card;
public List<Refund> refunds;
public Map<String,String> localpayent;
public String responseMessage;
public String responseAdvancedInfo;
public String responseCode;
public String refundedValue;
public String description;
public String metadata;
public String balanceTransaction;
public String status;
public boolean isCascaded;
public String authCode;
public ShippingDetails shippingDetails;
public String autoCapture;
public String autoCapTime;
public List<ProductsModel> products;

}
