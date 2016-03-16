package com.checkout.api.services.charge.request;

import java.util.List;

import com.checkout.api.services.recurringPayments.request.ExistingPaymentPlan;

public class CardChargeWithPaymentPlan extends CardCharge {

	public CardChargeWithPaymentPlan(){
		super();
	}
	
	public List<ExistingPaymentPlan> paymentPlans;
}
