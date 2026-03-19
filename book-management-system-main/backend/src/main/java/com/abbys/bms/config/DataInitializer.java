package com.abbys.bms.config;

import com.abbys.bms.data.user.entity.Ceo;
import com.abbys.bms.data.user.entity.Role;
import com.abbys.bms.data.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(!userRepo.existsByEmail("ceo@bms.com")) {
            Ceo ceo = Ceo.builder()
                    .name("System ROLE_CEO")
                    .ceoNic("200568977845")
                    .ceoAddress("Colombo 05")
                    .email("ceo@bms.com")
                    .bankName("BOC")
                    .bankNumber("478598687")
                    .contactNumber("071458966")
                    .password(passwordEncoder.encode("ceo@123"))
                    .role(Role.ROLE_CEO)
                    .build();
            userRepo.save(ceo);

        }
    }
}
