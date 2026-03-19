package com.abbys.bms.data.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCeoRequest extends CreateUserRequest{
    @NotBlank(message = "NIC cannot be empty")
    private String ceoNic;

    @NotBlank(message = "Address cannot be empty")
    private String ceoAddress;
}
