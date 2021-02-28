package com.sample.voucher.api.service.impl;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.voucher.api.db.model.Customer;
import com.sample.voucher.api.db.model.SpecialOffer;
import com.sample.voucher.api.db.model.ValidVoucher;
import com.sample.voucher.api.db.model.VoucherCode;
import com.sample.voucher.api.db.repository.DatabaseRepository;
import com.sample.voucher.api.service.VoucherPoolService;
import com.sample.voucher.constant.VoucherStatusEnum;
import com.sample.voucher.exception.BusinessException;

@Component
public class VoucherPoolServiceImpl implements VoucherPoolService {
    @Autowired
    DatabaseRepository dbRepo;
    
    @Override
    public Map<String, String> createVoucher(String offerName, Date expiryDate) throws Exception {
	int offerId;
	
	try {
	    offerId = dbRepo.getOfferId(offerName);
	} catch (Exception e) {
	    throw new BusinessException("O01", "Invalid Offer");
	}
	
	List<Customer> custList = dbRepo.getCustomerList(offerId);
	
	Map<String, String> result = new HashMap<String, String>();
	
	String voucherCode = null;
	for (Customer customer : custList) {
	    voucherCode = RandomStringUtils.random(10, 0, 0, true, true, null, new SecureRandom());
	    dbRepo.createVoucher(voucherCode, customer.getCustId(), offerId, expiryDate);
	    result.put(customer.getCustEmail(), voucherCode);
	}
	
	return result;
    }

    @Override
    public SpecialOffer updateVoucher(String voucherCode, String custEmail) throws Exception {
	VoucherCode voucher = dbRepo.getVoucher(voucherCode, custEmail);
	
	if (!VoucherStatusEnum.isActive(voucher.getUsageStatus()) || !voucher.getExpiryDate().after(new Date()))
	    throw new BusinessException("V01", "Voucher already expired");
	
	SpecialOffer offer = dbRepo.getOffer(voucher.getOfferId());
	
	int voucherExpired = dbRepo.updateVoucher(voucher.getVoucherId(), VoucherStatusEnum.OFFLINE);
	if (voucherExpired != 1)
	    throw new BusinessException("V01", "Error updating voucher status");
	
	return offer;
    }
    
    public List<ValidVoucher> getActiveVoucher(String custEmail) throws Exception {
	List<ValidVoucher> voucherList = dbRepo.getValidVoucher(custEmail);
	
	return voucherList;
    }
    
    
    public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
	    System.out.println(RandomStringUtils.random(10, 0, 0, true, true, null, new SecureRandom()));
	}
	
    }
}
