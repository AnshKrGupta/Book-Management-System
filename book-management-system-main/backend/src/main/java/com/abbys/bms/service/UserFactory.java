package com.abbys.bms.service;

import com.abbys.bms.data.user.dto.CreateCeoRequest;
import com.abbys.bms.data.user.dto.CreateCusManagerRequest;
import com.abbys.bms.data.user.dto.CreateUserRequest;
import com.abbys.bms.data.user.entity.*;

public class UserFactory {
    public static User createUser(CreateUserRequest request) {
        if(request instanceof CreateCeoRequest cr) {
            return Ceo.builder()
                    .name(cr.getName())
                    .role(fromStringToEnum(Role.class,cr.getRole()))
                    .email(cr.getEmail())
                    .ceoNic(cr.getCeoNic())
                    .ceoAddress(cr.getCeoAddress())
                    .password(cr.getPassword())
                    .contactNumber(cr.getContactNumber())
                    .bankNumber(cr.getBankNumber())
                    .bankName(cr.getBankName())
                    .build();

        } else if(request instanceof CreateCusManagerRequest cmr) {
            return CustomerManager.builder()
                    .name(cmr.getName())
                    .role(fromStringToEnum(Role.class,cmr.getRole()))
                    .email(cmr.getEmail())
                    .password(cmr.getPassword())
                    .contactNumber(cmr.getContactNumber())
                    .bankNumber(cmr.getBankNumber())
                    .bankName(cmr.getBankName())
                    .customerManagerCode(cmr.getCustomerManagerCode())
                    .onDuty(cmr.isOnDuty())
                    .workingHours(cmr.getWorkingHours())
                    .shift(fromStringToEnum(Shift.class,cmr.getShift()))
                    .build();
        }

        throw new IllegalArgumentException("Invalid Create Dto Type");
    }

    public static <T extends Enum<T>> T fromStringToEnum(Class<T> enumClass, String value) {
        if(value == null) {
            throw new IllegalArgumentException("Enum value cannot be null");
        }

        try{
            return Enum.valueOf(enumClass,value.toUpperCase());
        } catch(IllegalArgumentException ex) {
            throw new IllegalArgumentException("No enum constant " + enumClass.getCanonicalName() + " for value: " + value);
        }
    }
}
