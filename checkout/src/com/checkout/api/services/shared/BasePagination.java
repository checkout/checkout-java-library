package com.checkout.api.services.shared;

import java.util.Date;

public class BasePagination
{
    public Integer count;
    public Integer offset;
    public Date fromDate;
    public Date toDate;
    public Integer pageSize;
    public String pageNumber;
}
