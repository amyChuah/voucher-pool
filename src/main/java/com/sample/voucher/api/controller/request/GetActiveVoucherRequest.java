package com.sample.voucher.api.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetActiveVoucherRequest {
    @NotEmpty(message = "Email is required")
    @Email(message = "Not a valid email format")
    String email;
}
