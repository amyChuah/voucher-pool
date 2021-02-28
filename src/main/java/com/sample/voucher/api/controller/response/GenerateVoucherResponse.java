package com.sample.voucher.api.controller.response;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GenerateVoucherResponse extends CommonResponse {
    int count;
    Map<String, String> voucherMap;
}
