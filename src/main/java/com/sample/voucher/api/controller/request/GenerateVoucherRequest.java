package com.sample.voucher.api.controller.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateVoucherRequest {
    @NotEmpty(message = "Please provide the offer code")
    String offerCode;
    
    @NotEmpty
    String expiryDate;
}
