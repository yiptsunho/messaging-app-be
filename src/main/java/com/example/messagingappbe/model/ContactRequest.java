package com.example.messagingappbe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contact_request")
public class ContactRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull(message = "user must not be null")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    @NotNull(message = "contact must not be null")
    private User contact;

    @NotNull(message = "status must not be null")
    private ContactRequestStatus status;
}
