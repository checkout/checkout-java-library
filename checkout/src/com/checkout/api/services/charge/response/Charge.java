package com.checkout.api.services.charge.response;

import com.checkout.api.services.card.response.Card;
import com.checkout.api.services.charge.request.BaseCharge;
import com.checkout.api.services.shared.Address;

public class Charge extends BaseCharge {
public String id;
public String liveMode;
public String created;
public int chargeMode;
public String customerId;
public String transactionIndicator;
public Card card;
public String responseMessage;
public String responseAdvancedInfo;
public String responseCode;
public String status;
public boolean isCascaded;
public String authCode;
public Address shippingDetails;
}
