package com.checkout.api.services.charge.response;

import com.checkout.api.services.recurringPayments.response.CustomerPaymentPlan;

public class ChargeWithCustomerPaymentPlan extends Charge {
	public CustomerPaymentPlan[] customerPaymentPlans;
}
