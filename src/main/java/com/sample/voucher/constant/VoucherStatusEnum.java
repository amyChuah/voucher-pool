package com.sample.voucher.constant;

public enum VoucherStatusEnum {
    ONLINE(0), 
    OFFLINE(1);
    
    private int value;
    
    public int getValue() {
 	return value;
     }

     public void setValue(int value) {
 	this.value = value;
     }

     VoucherStatusEnum(int value) {
 	this.value = value;
     }
     
     public static boolean isActive(int value)
     {
	 return value == ONLINE.value;
     }
}
