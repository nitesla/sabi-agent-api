package com.sabi.agent.api.runner;

import com.sabi.framework.models.User;
import com.sabi.framework.repositories.UserRepository;
import com.sabi.framework.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdminUserRunner implements ApplicationRunner {


    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(userRepo.count() == 0){

            User user = new User();
            user.setFirstName("adminUser");
            user.setLastName("adminUser2");
            user.setPassword(passwordEncoder.encode("1111111"));
            user.setPhone("08136529363");
            user.setEmail("admin@sabi.com");
            user.setUsername("08136529363");
            user.setLoginAttempts(0l);
            user.setUserCategory(Constants.ADMIN_USER);
            user.setIsActive(true);
            user.setPasswordChangedOn(LocalDateTime.now());
            user.setCreatedBy(0L);
            user.setCreatedDate(LocalDateTime.now());
            user.setUpdatedDate(LocalDateTime.now());
            userRepo.save(user);
        }
    }





}
