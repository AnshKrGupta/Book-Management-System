package com.abbys.bms.data.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class CeoResponse extends UserResponse {
    private String ceoNic;
    private String ceoAddress;
}
