package com.sabi.agent.api.controller;

import com.sabi.agent.api.helper.WalletHelper;
import com.sabi.agent.core.wallet_integration.WalletSignUpDto;
import com.sabi.agent.core.wallet_integration.request.CompleteTopUpRequest;
import com.sabi.agent.core.wallet_integration.request.InitiateTopUpRequest;
import com.sabi.agent.core.wallet_integration.request.WalletBvnRequest;
import com.sabi.agent.core.wallet_integration.request.WalletSignUpRequest;
import com.sabi.agent.core.wallet_integration.response.ResponseMetaData;
import com.sabi.agent.service.services.WalletService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT + "wallet")
public class WalletController {
    private final WalletService service;

    @Autowired
    WalletHelper helper;

    public WalletController(WalletService service) {
        this.service = service;
    }


    @GetMapping("/balance")
    public ResponseEntity<Response> getBalance(@RequestParam("userId") String userId,
                                               @RequestHeader("fingerprint") String fingerPrint) {
        ResponseMetaData balance = service.getBalance(userId, fingerPrint);
        return helper.buildResponse(balance, HttpStatus.OK);
    }

    @GetMapping("/wallets")
    public ResponseEntity<Response> balanceSync(@RequestHeader(value = "fingerprint") String fingerPrint,
                                                @RequestParam("pageSize") String pageSize,
                                                @RequestParam("page") String page) {
        ResponseMetaData walletStatusResponse = service.getAllWallets(fingerPrint, pageSize, page);
        return helper.buildResponse(walletStatusResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createWallet(@RequestBody WalletSignUpRequest signUpRequest,
                                                 @RequestHeader("fingerprint") String fingerPrint) throws NoSuchAlgorithmException {
        var walletSignUpDto = new ModelMapper().map(signUpRequest, WalletSignUpDto.class);
        ResponseMetaData response = service.createWallet(walletSignUpDto, fingerPrint);
        return helper.buildResponse(response, HttpStatus.OK);
    }

    @PostMapping("/initiateTopUp")
    public ResponseEntity<Response> initiateTopUp(@RequestHeader("fingerprint") String fingerPrint,
                                                  @RequestParam("userId") String userId, @RequestBody @Valid InitiateTopUpRequest initiateTopUpRequest) {
        return helper.buildResponse(service.initiateTopUp(userId, fingerPrint, initiateTopUpRequest), HttpStatus.OK);
    }

    @PostMapping("/completeTopUp")
    public ResponseEntity<Response> completeTopup(@RequestHeader("fingerprint") String fingerPrint,
                                                  @Valid @RequestBody CompleteTopUpRequest request) {
        return helper.buildResponse(service.completeTopUp(request, fingerPrint), HttpStatus.OK);
    }

    @GetMapping("/walletDetails")
    public ResponseEntity<Response> getUserWalletDetails(@RequestHeader("fingerprint") String fingerPrint,
                                                         @RequestParam("userId") String userId) {
        return helper.buildResponse(service.getUserWalletDetails(fingerPrint, userId), HttpStatus.OK);
    }

    @PostMapping("/bvn")
    public ResponseEntity<Response> checkBvn(@RequestHeader("fingerprint") String fingerPrint, @Valid @RequestBody WalletBvnRequest request) {
        return helper.buildResponse(service.checkBvn(request, fingerPrint), HttpStatus.OK);
    }

    @GetMapping("/transactionHistory")
    public ResponseEntity<Response> transactionHistory(@RequestHeader("fingerPrint") String fingerPrint,
                                                       @RequestParam(value = "endDate", required = false) String endDate,
                                                       @RequestParam(value = "name", required = false) String name,
                                                       @RequestParam("page") String page,
                                                       @RequestParam("pageSize") String pageSize,
                                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                       @RequestParam(value = "startDate", required = false) String startDate,
                                                       @RequestParam(value = "transactionClass", required = false) String transactionClass,
                                                       @RequestParam(value = "transactionStatus", required = false) String transactionStatus,
                                                       @RequestParam(value = "transactionType", required = false) String transactionType,
                                                       @RequestParam("userId") String userId,
                                                       @RequestParam(value = "walletTransactionsSearchTerm", required = false) String walletTransactionsSearchTerm,
                                                       @RequestParam(value = "walletTransactionsSortCriteria", required = false) String walletTransactionsSortCriteria
    ) {
        return helper.buildResponse(service.walletHistory(fingerPrint, endDate, name, page, pageSize, phoneNumber, startDate, transactionClass, transactionStatus, transactionType, userId,
                walletTransactionsSearchTerm, walletTransactionsSortCriteria), HttpStatus.OK);
    }

}
