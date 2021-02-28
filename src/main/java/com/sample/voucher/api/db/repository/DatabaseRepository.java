package com.sample.voucher.api.db.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.voucher.api.db.mapper.CustomerMapper;
import com.sample.voucher.api.db.mapper.SpecialOfferMapper;
import com.sample.voucher.api.db.mapper.VoucherCodeMapper;
import com.sample.voucher.api.db.model.Customer;
import com.sample.voucher.api.db.model.SpecialOffer;
import com.sample.voucher.api.db.model.ValidVoucher;
import com.sample.voucher.api.db.model.VoucherCode;
import com.sample.voucher.constant.VoucherStatusEnum;

@Repository
public class DatabaseRepository {
    @Autowired
    CustomerMapper custMapper;

    @Autowired
    SpecialOfferMapper offerMapper;

    @Autowired
    VoucherCodeMapper voucherMapper;

    public int createVoucher(String voucherCd, int custId, int offerId, Date expiryDate) throws Exception {
	VoucherCode voucher = new VoucherCode();
	voucher.setVoucherCd(voucherCd);
	voucher.setCustId(custId);
	voucher.setOfferId(offerId);
	voucher.setExpiryDate(expiryDate);

	int result = voucherMapper.insertNewVoucher(voucher);

	return result;
    }

    public int updateVoucher(int voucherId, VoucherStatusEnum status) {
	int result = voucherMapper.updateById(voucherId, status.getValue(), new Date());
	return result;
    }

    public VoucherCode getVoucher(String voucherCd, String custEmail) {
	VoucherCode result = voucherMapper.selectByCodeEmail(voucherCd, custEmail);
	return result;
    }

    public List<ValidVoucher> getValidVoucher(String custEmail) {
	List<ValidVoucher> result = voucherMapper.selectActiveVoucher(custEmail, VoucherStatusEnum.ONLINE.getValue());
	return result;
    }

    public int getOfferId(String offerName) {
	int result = offerMapper.selectByName(offerName);
	return result;
    }
    
    public SpecialOffer getOffer(int offerId)
    {
	SpecialOffer result = offerMapper.selectById(offerId);
	return result;
    }

    public Customer getCustomer(String custEmail) {
	Customer result = custMapper.selectByEmail(custEmail);
	return result;
    }

    public List<Customer> getCustomerList(int offerId) {
	List<Customer> result = custMapper.selectCustIdByOfferId(offerId);
	return result;
    }
}
