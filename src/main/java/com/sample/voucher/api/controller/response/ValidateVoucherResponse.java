package com.sample.voucher.api.controller.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ValidateVoucherResponse extends CommonResponse{
    double discount;
    String offerName;
}
