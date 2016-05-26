package com.checkout.api.services.reporting.response;

import java.util.Date;

import com.checkout.api.services.customer.response.Customer;

public class Chargeback {
    public String id;
    public String chargeId;
    public String scheme;
    public String amount;
    public String value;
    public String currency;
    public String trackId;
    public Date issueDate;
    public String cardNumber;
    public String indicator;
    public String reasonCode;
    public String arn;
    public Customer customer;
}
