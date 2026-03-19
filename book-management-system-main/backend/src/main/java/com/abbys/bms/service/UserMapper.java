package com.abbys.bms.service;

import com.abbys.bms.data.user.dto.CeoResponse;
import com.abbys.bms.data.user.dto.CustomerManagerResponse;
import com.abbys.bms.data.user.dto.UserResponse;
import com.abbys.bms.data.user.entity.Ceo;
import com.abbys.bms.data.user.entity.CustomerManager;
import com.abbys.bms.data.user.entity.User;

public class UserMapper {
    public static UserResponse toDto(User user) {
        if (user instanceof Ceo c) {
            return CeoResponse.builder()
                    .id(c.getUserId())
                    .name(c.getName())
                    .email(c.getEmail())
                    .role(c.getRole().name())
                    .ceoNic(c.getCeoNic())
                    .ceoAddress(c.getCeoAddress())
                    .build();
        }
        if (user instanceof CustomerManager cm) {
            return CustomerManagerResponse.builder()
                    .id(cm.getUserId())
                    .name(cm.getName())
                    .email(cm.getEmail())
                    .role(cm.getRole().name())
                    .customerManagerCode(cm.getCustomerManagerCode())
                    .shift(cm.getShift().name())
                    .onDuty(cm.isOnDuty())
                    .workingHours(cm.getWorkingHours())
                    .build();
        }

        throw new IllegalArgumentException("Unknown user type");
    }
}
