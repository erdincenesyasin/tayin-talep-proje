package com.eparlak.personeltayintalebi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TayinTalebiPageConvertDTO {
    private LocalDateTime createdAt;
    private String aciklama;
    private String talepTuru;
    

}
