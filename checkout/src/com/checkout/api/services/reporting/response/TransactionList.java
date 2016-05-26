package com.checkout.api.services.reporting.response;

import java.util.List;

public class TransactionList {
	public int totalRecords;
	public int pageSize;
	public int pageNumber;
	public List<Transaction> data;
}
