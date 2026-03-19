package com.abbys.bms.data.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email",unique = true)
    private String email;

    private String password;

    private String name;

    private String contactNumber;

    private String bankNumber;

    private String bankName;

    @Enumerated(EnumType.STRING)
    private Role role;
}
