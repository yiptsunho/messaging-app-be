package com.example.messagingappbe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "register_verification_token")
public class RegisterVerificationToken {

    private static final Integer EXPIRATION = 60 * 24; // the verification token will expire in 1 day

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    private Boolean active;
    private Date calculateExpiryDate(Integer expiryTimeInMinute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinute);
        return new Date(cal.getTime().getTime());
    }
    public RegisterVerificationToken(User user) {
        this.user = user;
        expiryDate = calculateExpiryDate(EXPIRATION);
        token = UUID.randomUUID().toString();
        active = true;
    }
}
