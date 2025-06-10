package com.eparlak.personeltayintalebi.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.eparlak.personeltayintalebi.entity.TercihEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TayinTalebiTemelBilgiDTO {
    private Long id;
    private String talepTuru;
    private String aciklama;
    private LocalDateTime createdAt;
    private PersonelTemelBilgiDTO personel;
    private List<TercihEntity> tercihList;
}
