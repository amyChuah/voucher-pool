package com.sample.voucher.api.db.mapper;

import java.util.List;

import com.sample.voucher.api.db.model.Customer;

public interface CustomerMapper {
    Customer selectByEmail(String custEmail);
    List<Customer> selectCustIdByOfferId(int offerId);
}
