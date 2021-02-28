package com.sample.voucher.api.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOffer {
    int offerId;
    String offerName;
    double offerDiscount;
}
