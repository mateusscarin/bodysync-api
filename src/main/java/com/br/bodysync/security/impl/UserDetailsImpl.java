package com.br.bodysync.security.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.bodysync.model.enumerated.PersonType;

@SuppressWarnings("unused")
public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String cpf;
    private final String email;
    private final String fullName;
    private final String password;
    private final Collection<? extends GrantedAuthority> permissions;

    public UserDetailsImpl(Long id, String cpf, String email, String fullName, String password, PersonType type) {
        this.id = id;
        this.cpf = cpf;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.permissions = Arrays.asList(new SimpleGrantedAuthority(type.toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.fullName;
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

}
