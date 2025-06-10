package com.eparlak.personeltayintalebi.dto;

import com.eparlak.personeltayintalebi.entity.AdliyeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TercihResponseDTO {

    private AdliyeBilgisiDTO adliye;

    private Integer sira; // 1, 2,




}