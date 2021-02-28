package com.sample.voucher.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@MapperScan("com.sample.voucher.api.db.mapper")
public class VoucherPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoucherPoolApplication.class, args);
    }

}
	