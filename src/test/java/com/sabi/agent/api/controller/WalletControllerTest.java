package com.sabi.agent.api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabi.agent.api.helper.WalletHelper;
import com.sabi.agent.core.wallet_integration.request.WalletToBankTransferRequest;
import com.sabi.agent.core.wallet_integration.request.WalletTransacitonDetailsRequest;
import com.sabi.agent.core.wallet_integration.response.WalletResponse;
import com.sabi.agent.core.wallet_integration.response.WalletStatusResponse;
import com.sabi.agent.core.wallet_integration.response.WalletTransactionDetailsResponse;
import com.sabi.agent.service.services.WalletService;
import com.sabi.framework.dto.responseDto.Response;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {WalletController.class})
@RunWith(SpringRunner.class)
public class WalletControllerTest {
    @Autowired
    private WalletController walletController;

    @MockBean
    private WalletHelper walletHelper;

    @MockBean
    private WalletService walletService;

    @Test
    public void testWalletTransactionDetails() throws Exception {
        when(this.walletService.walletTransactionDetails((String) any(), (WalletTransacitonDetailsRequest) any()))
                .thenReturn(new WalletTransactionDetailsResponse());
        when(this.walletHelper.buildResponse((com.sabi.agent.core.wallet_integration.response.IWalletResponse) any(),
                (HttpStatus) any())).thenReturn(new ResponseEntity<Response>(HttpStatus.CONTINUE));

        WalletTransacitonDetailsRequest walletTransacitonDetailsRequest = new WalletTransacitonDetailsRequest();
        walletTransacitonDetailsRequest.setTransactionClass("Transaction Class");
        walletTransacitonDetailsRequest.setEndDate("2020-03-01");
        walletTransacitonDetailsRequest.setWalletTransactionsSortCriteria("Wallet Transactions Sort Criteria");
        walletTransacitonDetailsRequest.setWalletTransactionsSearchTerm("Wallet Transactions Search Term");
        walletTransacitonDetailsRequest.setTransactionReference("Transaction Reference");
        walletTransacitonDetailsRequest.setName("Name");
        walletTransacitonDetailsRequest.setPage(1L);
        walletTransacitonDetailsRequest.setPhoneNumber("4105551212");
        walletTransacitonDetailsRequest.setPageSize(3L);
        walletTransacitonDetailsRequest.setTransactionType("Transaction Type");
        walletTransacitonDetailsRequest.setStartDate("2020-03-01");
        String content = (new ObjectMapper()).writeValueAsString(walletTransacitonDetailsRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/wallet/transactionDetails")
                .header("fingerprint", "b6:03:0e:39:97:9e:d0:e7:24:ce:a3:77:3e:01:42:09")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.walletController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testWalletStatus() throws Exception {
        when(this.walletService.walletStatus((String) any())).thenReturn(new WalletStatusResponse());
        when(this.walletHelper.buildResponse((com.sabi.agent.core.wallet_integration.response.IWalletResponse) any(),
                (HttpStatus) any())).thenReturn(new ResponseEntity<Response>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/wallet/status")
                .header("fingerprint", "b6:03:0e:39:97:9e:d0:e7:24:ce:a3:77:3e:01:42:09");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.walletController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testWalletToBankTransfer() throws Exception {
        when(this.walletService.walletToBankTransfer((String) any(), any()))
                .thenReturn(new WalletResponse());
        when(this.walletHelper.buildResponse(any(),
                any())).thenReturn(new ResponseEntity<Response>(HttpStatus.CONTINUE));

        WalletToBankTransferRequest walletToBankTransferRequest = new WalletToBankTransferRequest();
        walletToBankTransferRequest.setReceiverPhoneNumber("4105551212");
        walletToBankTransferRequest.setTransactionCharge(BigDecimal.valueOf(1L));
        walletToBankTransferRequest.setAccountNumber("42");
        walletToBankTransferRequest.setBankCode("Bank Code");
        walletToBankTransferRequest.setAmount(BigDecimal.valueOf(1L));
        walletToBankTransferRequest.setNarration("Narration");
        walletToBankTransferRequest.setTransactionTypeEnum("Debit_trans");
        walletToBankTransferRequest.setAccountName("Dr Jane Doe");
        String content = (new ObjectMapper()).writeValueAsString(walletToBankTransferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/wallet/transferToBank")
                .header("fingerprint", "b6:03:0e:39:97:9e:d0:e7:24:ce:a3:77:3e:01:42:09")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.walletController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

