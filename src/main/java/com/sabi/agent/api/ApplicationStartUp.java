package com.sabi.agent.api;


import com.sabi.framework.service.ExternalTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationStartUp implements ApplicationRunner {

    @Autowired
    private ExternalTokenService externalTokenService;


    @Override
    public void run (ApplicationArguments args){
        log.info("Generate new space token at : {}" , new Date());
        externalTokenService.externalTokenRequest();
    }
}
