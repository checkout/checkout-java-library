package com.checkout.api.services.reporting.request;

import java.util.List;
import com.checkout.api.services.shared.BasePagination;
import com.checkout.api.services.shared.SortOrder;

public class TransactionQuery extends BasePagination {
	public String search;
	public List<TransactionFilter> filters;
	public TransactionSortColumn sortColumn;
	public SortOrder sortOrder;
}
