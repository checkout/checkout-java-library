package com.checkout.api.services.reporting.request;

import java.util.List;

import com.checkout.api.services.shared.BasePagination;
import com.checkout.api.services.shared.Filter;
import com.checkout.api.services.shared.SortColumn;
import com.checkout.api.services.shared.SortOrder;

public class ChargebackQuery extends BasePagination {
	public String search;
	public List<Filter> filters;
	public SortColumn sortColumn;
	public SortOrder sortOrder;
}
