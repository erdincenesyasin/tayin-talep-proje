package com.eparlak.personeltayintalebi.dto.tumbasvurular;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonelDTO {
    Long id;
    String ad;
    String soyad;
    String unvan;
    String sicilNo;
    String calistigiAdliye;
   
}
