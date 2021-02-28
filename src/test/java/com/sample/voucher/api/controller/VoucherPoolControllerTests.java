package com.sample.voucher.api.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.voucher.api.controller.request.GenerateVoucherRequest;
import com.sample.voucher.api.controller.request.ValidateVoucherRequest;
import com.sample.voucher.api.db.model.SpecialOffer;
import com.sample.voucher.api.db.model.ValidVoucher;
import com.sample.voucher.api.service.VoucherPoolService;
import com.sample.voucher.exception.BusinessException;

@SpringBootTest
@AutoConfigureMockMvc
public class VoucherPoolControllerTests {
    @MockBean
    private VoucherPoolService vService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Post /generateVoucher success")
    public void generateVoucherCodeReturnSuccess() throws Exception {
	Map<String, String> voucherList = new HashMap<String, String>();
	voucherList.put("john.doe@mail.com", "LAgJgzkex2");

	GenerateVoucherRequest request = new GenerateVoucherRequest("Offer1", "2021-03-31");

	Mockito.when(vService.createVoucher(Mockito.any(), Mockito.any())).thenReturn(voucherList);

	mockMvc.perform(post("/api/voucherPool/generateVoucher").contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(request)))

		// Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		// Validate the returned fields
		.andExpect(jsonPath("$.status", is(0)))
		.andExpect(jsonPath("$.count", is(1)));
    }

    @Test
    @DisplayName("Post /validateVoucher success")
    public void validateVoucherReturnSuccess() throws Exception {
	ValidateVoucherRequest request = new ValidateVoucherRequest("D6Onm48YYA", "john.doe@mail.com");
	SpecialOffer response = new SpecialOffer(1, "Offer2", 5.0);

	Mockito.when(vService.updateVoucher(Mockito.anyString(), Mockito.anyString())).thenReturn(response);

	mockMvc.perform(post("/api/voucherPool/validateVoucher").contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(request)))

		// Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		// Validate the returned fields
		.andExpect(jsonPath("$.status", is(0)))
		.andExpect(jsonPath("$.count", is(0)))
		.andExpect(jsonPath("$.datas.offerName", is("Offer2")))
		.andExpect(jsonPath("$.datas.discount", is(5.0)));
    }

    @Test
    @DisplayName("Post /validateVoucher fail")
    public void validateVoucherReturnFail() throws Exception {
	ValidateVoucherRequest request = new ValidateVoucherRequest("D6Onm48YYA", "john.doe@mail.com");
	BusinessException exception = new BusinessException("V01", "Voucher already expired");

	Mockito.when(vService.updateVoucher(Mockito.anyString(), Mockito.anyString())).thenThrow(exception);

	mockMvc.perform(post("/api/voucherPool/validateVoucher").contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(request)))

		// Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		// Validate the returned fields
		.andExpect(jsonPath("$.status", is(1)))
		.andExpect(jsonPath("$.count", is(0)))
		.andExpect(jsonPath("$.error.errorCode", is("V01")))
		.andExpect(jsonPath("$.error.errorMessage", is("Voucher already expired")));
    }
    
    @Test
    @DisplayName("Post /getActiveVoucher success")
    public void getActiveVoucherReturnSuccess() throws Exception {
	Map<String, String> request = new HashMap<String, String>();
	request.put("custEmail", "john.doe@mail.com");
	
	ValidVoucher voucher = new ValidVoucher("LAgJgzkex2", "Offer1", 10.0, "2021-03-31");
	List<ValidVoucher> result = new ArrayList<ValidVoucher>();
	result.add(voucher);
	
	Mockito.when(vService.getActiveVoucher(Mockito.anyString())).thenReturn(result);
	
	mockMvc.perform(post("/api/voucherPool/getActiveVoucher").contentType(MediaType.APPLICATION_JSON)
		.content(asJsonString(request)))

		// Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))

		// Validate the returned fields
		.andExpect(jsonPath("$.status", is(0)))
		.andExpect(jsonPath("$.count", is(1)))
		.andExpect(jsonPath("$.datas.[0].offerName", is("Offer1")))
		.andExpect(jsonPath("$.datas.[0].offerDiscount", is(10.0)));
    }
    
    static String asJsonString(final Object obj) {
	try {
	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
