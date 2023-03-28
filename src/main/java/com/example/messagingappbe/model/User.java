package com.example.messagingappbe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class User {

    @Id
    @NotNull(message = "user id must not be null")
    @Generated
    private Long id;
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "user emailAddress must not be null")
    private String emailAddress;
    @NotNull(message = "user password must not be null")
    private String password;
    private byte[] avatar;
}
