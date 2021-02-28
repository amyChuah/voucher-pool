package com.sample.voucher.api.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidVoucher {
    String voucherCd;
    String offerName;
    double offerDiscount;
    String expiryDate;
}
