package com.checkout.api.services.reporting.response;

import java.util.Date;
import com.checkout.api.services.customer.response.Customer;

public class Transaction {
    public String id;
    public String originId;
    public Date date;
    public String status;
    public String type;
    public String amount;
    public String scheme;
    public String responseCode;
    public String currency;
    public Boolean liveMode;
    public String businessName;
    public String channelName;
    public String trackId;
    public Customer customer;
}
