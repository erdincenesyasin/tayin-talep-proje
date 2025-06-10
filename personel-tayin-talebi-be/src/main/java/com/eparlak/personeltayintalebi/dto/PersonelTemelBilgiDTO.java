package com.eparlak.personeltayintalebi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonelTemelBilgiDTO {
    private Long id;
    private String ad;
    private String soyad;
    private String telefon;
    private String sicilNo;
    private String unvan;
    private String calistigiAdliye;
    private String role;
}
