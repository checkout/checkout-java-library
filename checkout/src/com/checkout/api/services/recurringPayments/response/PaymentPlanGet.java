package com.checkout.api.services.recurringPayments.response;

import com.checkout.api.services.recurringPayments.request.BasePaymentPlan;

public class PaymentPlanGet extends BasePaymentPlan {
	
	public PaymentPlanGet() {
		super();
	}
	
	public String planId;
	public int status;
}
