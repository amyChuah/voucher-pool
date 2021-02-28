package com.sample.voucher.api.db.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherCode {
    int voucherId;
    String voucherCd;
    int custId;
    int offerId;
    Date expiryDate;
    int usageStatus;
    Date usageDate;
    Date creationDate;
    String offerName;
}
