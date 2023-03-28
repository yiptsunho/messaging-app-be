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
@Table(name = "chat_group")
public class Group {

    @Id
    @Generated
    @NotNull(message = "group id must not be null")
    private Long id;
    @NotNull(message = "group name must not be null")
    private String name;
    private byte[] avatar;
}
