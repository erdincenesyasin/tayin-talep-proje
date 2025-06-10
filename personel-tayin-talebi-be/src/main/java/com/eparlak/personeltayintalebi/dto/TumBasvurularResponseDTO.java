package com.eparlak.personeltayintalebi.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TumBasvurularResponseDTO {
    
    private TayinTalebiPageConvertDTO tayinTalebi;
    private PersonelTemelBilgiDTO personel;
    private List<TercihResponseDTO> tercihList;
    


}


