package com.checkout.api.services.charge.request;

import java.util.List;

import com.checkout.api.services.shared.Product;

public class ChargeRefund extends BaseChargeInfo {
	public ChargeRefund(){
		super();
	}

	public String value;
	public List<Product> products;
}
