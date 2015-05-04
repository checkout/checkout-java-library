package com.checkout.api.services.charge.response;

import java.util.List;

import com.checkout.api.services.charge.request.BaseChargeInfo;
import com.checkout.api.services.shared.Product;

public class Void extends BaseChargeInfo {
public String id;
public String originalId;
public String liveMode;
public String created;
public int value;
public String currency;
public String responseMessage;
public String responseAdvancedInfo;
public String responseCode;
public String status;
public List<Product> products;
}
