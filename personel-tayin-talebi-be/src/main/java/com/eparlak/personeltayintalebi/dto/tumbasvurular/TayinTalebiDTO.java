package com.eparlak.personeltayintalebi.dto.tumbasvurular;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  
public class TayinTalebiDTO {
    Long id;
    // String aciklama; // 500 karaktere açıklama yazılıyor. Yük biner.  Gekerirse çekilsin ??
    LocalDateTime createdAt; //başvuru tarihi için lazım.
    
    

}
