package com.checkout.api.services.charge.request;

import java.util.List;

import com.checkout.api.services.shared.Product;

public class ChargeVoid extends BaseChargeInfo {
	public ChargeVoid(){
		super();
	}
	public List<Product> products;
}
