package com.checkout.api.services.recurringPayments.request;

public class BaseChargePaymentPlan {
	
	public BaseChargePaymentPlan() {
		
	}
	
	public String name;
    public String planTrackId;
    public int autoCapTime;
    public int value;
    public String cycle;
    public int recurringCount;
}
