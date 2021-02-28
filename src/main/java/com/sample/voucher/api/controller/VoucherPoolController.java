package com.sample.voucher.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.voucher.api.controller.request.GenerateVoucherRequest;
import com.sample.voucher.api.controller.request.GetActiveVoucherRequest;
import com.sample.voucher.api.controller.request.ValidateVoucherRequest;
import com.sample.voucher.api.controller.response.CommonResponse;
import com.sample.voucher.api.controller.response.ErrorResponse;
import com.sample.voucher.api.db.model.SpecialOffer;
import com.sample.voucher.api.db.model.ValidVoucher;
import com.sample.voucher.api.service.VoucherPoolService;
import com.sample.voucher.exception.BusinessException;

@Component
@RequestMapping("/api/voucherPool")
public class VoucherPoolController {

    @Autowired
    VoucherPoolService voucherService;
    
    @RequestMapping(value = "/generateVoucher", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> generateVoucher(@Valid @RequestBody GenerateVoucherRequest request) throws Exception {
	CommonResponse response = new CommonResponse();

	try {
	    
	    Map<String, String> datas = voucherService.createVoucher(request.getOfferName(), getDatePart(request.getExpiryDate()));
	    response.setDatas(datas);
	    response.setCount(datas.size());
	} catch (Exception e) {
	    ErrorResponse error = null;
	    if (e instanceof BusinessException) {
		error = new ErrorResponse(((BusinessException) e).getCode() , e.getMessage());
		response.setError(error);
	    }
	    else
		error = new ErrorResponse("9999", e.getMessage());
	    response.setStatus(1);
	}
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/validateVoucher", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> validateVoucher(@Valid @RequestBody ValidateVoucherRequest request)  throws Exception {
	CommonResponse response = new CommonResponse();

	try {
	    SpecialOffer offer = voucherService.updateVoucher(request.getVoucherCode(), request.getEmail());
	    
	    Map<String, Object> datas = new HashMap<String, Object>();
	    datas.put("discount", offer.getOfferDiscount());
	    datas.put("offerName", offer.getOfferName());
	    
	    response.setDatas(datas);
	    
	} catch (Exception e) {
	    ErrorResponse error = null;
	    if (e instanceof BusinessException) {
		error = new ErrorResponse(((BusinessException) e).getCode() , e.getMessage());
		response.setError(error);
	    }
	    else
		error = new ErrorResponse("9999", e.getMessage());
	    response.setStatus(1);
	}

	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getActiveVoucher", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> getActiveVoucher(@Valid @RequestBody GetActiveVoucherRequest params) {
	CommonResponse response = new CommonResponse();
	
	try {
	    List<ValidVoucher> voucherList = voucherService.getActiveVoucher(params.getEmail());
	    if (!voucherList.isEmpty())
	    {
		 response.setCount(voucherList.size());
		 response.setDatas(voucherList);
	    }
	} catch (Exception e) {
	    ErrorResponse error = null;
	    if (e instanceof BusinessException) {
		error = new ErrorResponse(((BusinessException) e).getCode() , e.getMessage());
		response.setError(error);
	    }
	    else
		error = new ErrorResponse("9999", e.getMessage());
	    response.setStatus(1);
	}
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private static Date getDatePart(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(format.format(date));
    }
}
