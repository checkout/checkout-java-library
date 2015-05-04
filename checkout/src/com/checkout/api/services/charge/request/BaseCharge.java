package com.checkout.api.services.charge.request;

import java.util.List;

import com.checkout.api.services.shared.Product;

public class BaseCharge extends BaseChargeInfo{

	private static final String YES = "y";
	
	public BaseCharge(){
		  autoCapture = YES;
		  chargeMode = 1;			//Default mode is no 3D
	}

	public String value;
	public String currency;
	public int chargeMode;
	public String autoCapture;
	public int autoCapTime;
	public String customerIp;
	public String email;
	public List<Product> products;
}
