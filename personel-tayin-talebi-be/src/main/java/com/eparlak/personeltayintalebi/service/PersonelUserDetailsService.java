package com.eparlak.personeltayintalebi.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.repository.PersonelRepository;
import com.eparlak.personeltayintalebi.security.PersonelUserDetails;


@Service
public class PersonelUserDetailsService implements UserDetailsService{

private final PersonelRepository personelRepository;


public PersonelUserDetailsService(PersonelRepository personelRepository){
    this.personelRepository = personelRepository;
    
}




    @Override
    public UserDetails loadUserByUsername(String sicilNoString  ) throws UsernameNotFoundException {

        

        try {
             PersonelEntity personel = personelRepository.findBySicilNo(sicilNoString)
                .orElseThrow(() -> new UsernameNotFoundException("LoadUserByUsername metodu: Kullanıcı bulunamadı"));
            
           

            PersonelUserDetails personelUserDetails = new PersonelUserDetails(personel);

                
            return personelUserDetails;
            
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    
}