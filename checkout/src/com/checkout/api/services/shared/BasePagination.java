package com.checkout.api.services.shared;

import java.util.Date;

public class BasePagination
{
    public int count;
    public int offset;
    public Date fromDate;
    public Date toDate;
    public int pageSize;
    public String pageNumber;
}
