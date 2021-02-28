package com.sample.voucher.api.db.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sample.voucher.api.db.model.ValidVoucher;
import com.sample.voucher.api.db.model.VoucherCode;

public interface VoucherCodeMapper {
    VoucherCode selectByCodeEmail(String voucherCd, String custEmail);
    List<ValidVoucher> selectActiveVoucher(String custEmail, int usageStatus);
    int updateById(@Param("voucherId") int voucherId, @Param("usageStatus") int usageStatus, @Param("usageDate") Date usageDate);
    int insertNewVoucher(VoucherCode voucher);
}
