package com.checkout.api.services.charge.request;

import java.util.List;

import com.checkout.api.services.recurringPayments.request.ChargePaymentPlan;

public class CardChargeWithNewPaymentPlan extends CardCharge {
	public CardChargeWithNewPaymentPlan(){
		super();
	}
	
	public List<ChargePaymentPlan> paymentPlans;
}
