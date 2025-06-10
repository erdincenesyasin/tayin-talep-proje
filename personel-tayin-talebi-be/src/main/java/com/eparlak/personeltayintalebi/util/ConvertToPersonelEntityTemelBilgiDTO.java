package com.eparlak.personeltayintalebi.util;

import org.springframework.stereotype.Component;

import com.eparlak.personeltayintalebi.dto.PersonelTemelBilgiDTO;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;

@Component
public class ConvertToPersonelEntityTemelBilgiDTO {
    

public PersonelTemelBilgiDTO convertToDto(PersonelEntity personel) {
    PersonelTemelBilgiDTO dto = new PersonelTemelBilgiDTO();
    dto.setId(personel.getId());
    dto.setAd(personel.getAd());
    dto.setSoyad(personel.getSoyad());
    dto.setSicilNo(personel.getSicilNo());   
    dto.setCalistigiAdliye(personel.getCalistigiAdliye());
    dto.setUnvan(personel.getUnvan());
    dto.setRole(personel.getRole());
   
    return dto;
}
    
}
