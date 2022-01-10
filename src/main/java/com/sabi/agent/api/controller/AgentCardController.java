package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.requestDto.CardTokenizationRequest;
import com.sabi.agent.core.models.agentModel.AgentCard;
import com.sabi.agent.service.services.AgentCardService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.helpers.ResponseHelper;
import com.sabi.framework.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT +"agentcard")
public class AgentCardController {

    private final AgentCardService agentCardService;
    private final ResponseHelper responseHelper;

    public AgentCardController(AgentCardService agentCardService, ResponseHelper responseHelper) {
        this.agentCardService = agentCardService;
        this.responseHelper = responseHelper;
    }

    @PostMapping("/tokeniseCard")
    public ResponseEntity<Response> tokeniseCard(@RequestBody @Valid CardTokenizationRequest tokenisationRequest){
//        TokenisationResponse tokenise = paymentService.tokenise(tokenisationRequest);
//        agentService.tokenise(tokenise, tokenisationRequest.getAgentId());
        return responseHelper.buildResponse(agentCardService.saveCard(tokenisationRequest), HttpStatus.OK, "Request Successful");

    }

    @PutMapping("/default")
    public ResponseEntity<Response> chooseDefault(@RequestParam(value = "cardId") Long cardId,
                                                  @RequestParam(value = "agentId") Long agentId){
        return responseHelper.buildResponse(agentCardService.chooseDefaultCard(agentId, cardId), HttpStatus.OK, "Request Successful");
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCards(@RequestParam(value = "agentId") Long agentId){
        return responseHelper.buildResponse(agentCardService.getCards(agentId), HttpStatus.OK, "Request Successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCard(@PathVariable Long cardId){
        return responseHelper.buildResponse(agentCardService.getCard(cardId), HttpStatus.OK, "Request Successful");
    }
}
