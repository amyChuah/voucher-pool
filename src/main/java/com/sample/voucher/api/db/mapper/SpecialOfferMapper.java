package com.sample.voucher.api.db.mapper;

import com.sample.voucher.api.db.model.SpecialOffer;

public interface SpecialOfferMapper {
    SpecialOffer selectById(int offerId);
    Integer selectByName(String offerName);
}
