package com.abbys.bms.data.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Min(6)
    @Max(12)
    private String password;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Contact Number cannot be empty")
    private String contactNumber;

    @NotBlank(message = "Bank Number cannot be empty")
    private String bankNumber;

    @NotBlank(message = "Bank Name cannot be empty")
    private String bankName;

    @NotBlank(message = "Role cannot be empty")
    private String role;
}
