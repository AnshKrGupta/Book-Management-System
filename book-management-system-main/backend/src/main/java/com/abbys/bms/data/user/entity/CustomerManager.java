package com.abbys.bms.data.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_customer_manager")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerManager extends User{
    @Column(unique = true,nullable = false)
    private String customerManagerCode;

    private String workingHours;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    private boolean onDuty;
}
