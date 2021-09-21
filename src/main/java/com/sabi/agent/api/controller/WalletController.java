package com.sabi.agent.api.controller;

import com.sabi.agent.api.helper.WalletHelper;
import com.sabi.agent.core.wallet_integration.request.*;
import com.sabi.agent.core.wallet_integration.response.WalletStatusResponse;
import com.sabi.agent.service.services.WalletService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Constants.APP_CONTENT +"wallet")
public class WalletController {
    private final WalletService service;

    @Autowired
    WalletHelper helper;

    public WalletController(WalletService service) {
        this.service = service;
    }

    //This endpoint activates a wallet
    @GetMapping("/activate")
    public ResponseEntity<Response> activateWallet(@RequestHeader("fingerprint") String fingerPrint){
        WalletStatusResponse response = service.activateWallet(fingerPrint);
        return helper.buildResponse(response, HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<Response> getBalance(@RequestHeader("fingerprint") String fingerPrint){
        WalletStatusResponse balance = service.getBalance(fingerPrint);
        return helper.buildResponse(balance, HttpStatus.OK);
    }

    @GetMapping("/balanceSync")
    public ResponseEntity<Response> balanceSync(@RequestHeader("fingerprint") String fingerPrint){
        WalletStatusResponse walletStatusResponse = service.balanceSync(fingerPrint);
        return helper.buildResponse(walletStatusResponse, HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<Response> createWallet(@RequestHeader("fingerprint") String fingerPrint){
        WalletStatusResponse response = service.createWallet(fingerPrint);
        return helper.buildResponse(response, HttpStatus.OK);
    }

    @PostMapping("/debit")
    public ResponseEntity<Response> debitUser(@RequestHeader("fingerprint") String fingerPrint, @RequestBody @Valid DebitUserRequest debitUserRequest){
        return helper.buildResponse(service.debitUser(fingerPrint, debitUserRequest), HttpStatus.OK);
    }

    @PostMapping("/initiateTopUp")
    public ResponseEntity<Response> initiateTopUp(@RequestHeader("fingerprint") String fingerPrint, @RequestBody @Valid InitiateTopUpRequest initiateTopUpRequest){
        return helper.buildResponse(service.initiateTopUp(fingerPrint, initiateTopUpRequest), HttpStatus.OK);
    }

    @PostMapping("/completeTopUp")
    public ResponseEntity<Response> completeTopUp(@RequestHeader("fingerprint") String fingerPrint, @RequestBody @Valid CompleteTopUpRequest completeTopUpRequest){
        return helper.buildResponse(service.completeTopUp(fingerPrint, completeTopUpRequest), HttpStatus.OK);
    }

    @PostMapping("/transactionDetails")
    public ResponseEntity<Response> walletTransactionDetails(@RequestHeader("fingerprint") String fingerPrint, @RequestBody @Valid WalletTransacitonDetailsRequest walletTransacitonDetailsRequest){
        return helper.buildResponse(service.walletTransactionDetails(fingerPrint, walletTransacitonDetailsRequest), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Response> walletStatus(@RequestHeader("fingerprint") String fingerPrint){
        return helper.buildResponse(service.walletStatus(fingerPrint), HttpStatus.OK);
    }

    @PostMapping("/transferToBank")
    public ResponseEntity<Response> walletToBankTransfer(@RequestHeader("fingerprint") String fingerPrint, @RequestBody @Valid WalletToBankTransferRequest walletToBankTransferRequest){
        return helper.buildResponse(service.walletToBankTransfer(fingerPrint, walletToBankTransferRequest), HttpStatus.OK);
    }

    @PostMapping("/transferToWallet")
    public ResponseEntity<Response> walletToWalletTransfer(@RequestHeader("fingerprint") String fingerPrint, @RequestBody @Valid WalletToBankTransferRequest walletToBankTransferRequest){
        return helper.buildResponse(service.walletToWalletTransfer(fingerPrint,walletToBankTransferRequest), HttpStatus.OK);
    }
}
