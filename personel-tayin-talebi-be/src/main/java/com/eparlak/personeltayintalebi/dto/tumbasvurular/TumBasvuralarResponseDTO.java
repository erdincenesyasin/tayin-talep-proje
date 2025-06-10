package com.eparlak.personeltayintalebi.dto.tumbasvurular;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TumBasvuralarResponseDTO {
    TayinTalebiDTO tayinTalebi;
    PersonelDTO personel;
    List<TercihDTO> tercihListesi;
}

