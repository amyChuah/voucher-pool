package com.sample.voucher.api.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    int custId;
    String custName;
    String custEmail;
    
}
