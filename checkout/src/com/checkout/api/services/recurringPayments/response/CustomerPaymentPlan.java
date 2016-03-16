package com.checkout.api.services.recurringPayments.response;

import java.util.Date;

import com.checkout.api.services.recurringPayments.request.BasePaymentPlan;

public class CustomerPaymentPlan extends BasePaymentPlan {
	
	public CustomerPaymentPlan() {
		super();
	}
	
	public String planId;
	public String customerPlanId;
	public Date startDate;
    public Date previousRecurringDate;
    public Date nextRecurringDate;
    public int recurringCountLeft;
    public int totalCollectedValue;
    public int totalCollectedCount;
    public int status;
   
}
