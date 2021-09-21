package com.sabi.agent.api.helper;

import com.sabi.agent.core.wallet_integration.response.IWalletResponse;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletHelper {
    public ResponseEntity<Response> buildResponse(IWalletResponse response, HttpStatus httpCode){
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        return new ResponseEntity<>(resp, httpCode);
    }
}
