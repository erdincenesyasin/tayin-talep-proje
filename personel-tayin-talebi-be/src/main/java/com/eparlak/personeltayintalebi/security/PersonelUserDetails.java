package com.eparlak.personeltayintalebi.security;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eparlak.personeltayintalebi.entity.PersonelEntity;

public class PersonelUserDetails implements UserDetails {
    
    private final PersonelEntity personel;
    
    public PersonelUserDetails(PersonelEntity personel) {
        this.personel = personel;
    }
    
    public Long getId() {
        return personel.getId();
    }
    
    @Override
    public String getUsername() {
        return personel.getSicilNo();
    }
    
    @Override
    public String getPassword() {
        return personel.getPassword();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = personel.getRole();
       
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
    
    @Override
    public boolean isAccountNonExpired() { return true; }
    
    @Override
    public boolean isAccountNonLocked() { return true; }
    
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    
    @Override
    public boolean isEnabled() { 
        return personel.getAktifMi() != null ? personel.getAktifMi() : true; 
    }

    public PersonelEntity getPersonel() {
        return personel;
    }
}