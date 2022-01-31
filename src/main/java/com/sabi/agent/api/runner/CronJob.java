package com.sabi.agent.api.runner;


import com.sabi.framework.service.ExternalTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class CronJob {

    private ExternalTokenService externalTokenService;

    public CronJob(ExternalTokenService externalTokenService) {
        this.externalTokenService = externalTokenService;

    }

    // @Scheduled(cron="0 0 0 * * ?")
    @Scheduled(cron="${cronExpression}")
    public void getNewToken() {
        log.info("::::::::::::: Cron Job Started at :::::::::::: :   %s", new Date());
        externalTokenService.externalTokenRequest();
    }
}
