package com.sample.voucher.api.controller.request;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateVoucherRequest {
    @NotEmpty(message = "Please provide the offer code")
    String offerName;
    
    @NotNull
    @FutureOrPresent(message = "Please provide valid expiry date")
    Date expiryDate;
}
