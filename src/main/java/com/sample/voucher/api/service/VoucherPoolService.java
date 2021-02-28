package com.sample.voucher.api.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sample.voucher.api.db.model.SpecialOffer;
import com.sample.voucher.api.db.model.ValidVoucher;

public interface VoucherPoolService {
    Map<String, String> createVoucher(String offerCode, Date expiryDate) throws Exception;
    SpecialOffer updateVoucher(String voucherCode, String custEmail) throws Exception;
    List<ValidVoucher> getActiveVoucher(String custEmail) throws Exception;
}
