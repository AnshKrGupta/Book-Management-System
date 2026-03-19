package com.abbys.bms.data.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class CustomerManagerResponse extends UserResponse {
    private String customerManagerCode;
    private String shift;
    private boolean onDuty;
    private String workingHours;
}
