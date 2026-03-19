package com.abbys.bms.data.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCusManagerRequest extends CreateUserRequest{
    @NotBlank(message = "Customer Manager Code cannot be empty")
    private String customerManagerCode;

    @NotBlank(message = "Working Hours cannot be empty")
    private String workingHours;

    @NotBlank(message = "Shift cannot be empty")
    private String shift;

    private boolean onDuty;
}
