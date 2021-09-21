package com.sabi.agent.api.exceptions;

import com.sabi.agent.core.wallet_integration.response.IWalletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissingFieldException implements IWalletResponse {
    private String message;
    private Map<String, String> fields;
}
