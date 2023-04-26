package com.example.messagingappbe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "user name must not be null")
    private String name;
    private String phoneNumber;

    @NotNull(message = "user emailAddress must not be null")
    private String emailAddress;

    @NotNull(message = "user password must not be null")
    private String password;
    private byte[] avatar;

    @NotNull(message = "verified must not be null")
    private Boolean verified;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<Group> groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() { // initialize verified as false
        super();
        this.verified = false;
    }
}
