package com.eparlak.personeltayintalebi.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminTercihIslemleriEntity extends BaseEntity {
   

    @Pattern(regexp = "^[a-zA-Z0-9ğüşıöçĞÜŞİÖÇ\\s.,\\-():/]+$", message = "Açıklama geçersiz karakterler içeriyor.")
    private String tercihDonemiAciklamaMetniBasligi;//2025 yılı  PGM KADROLU PERSONELİN İSTEĞE BAĞLI NAKİL ÇALIŞMASI, vs.
    @Pattern(regexp = "^[a-zA-Z0-9ğüşıöçĞÜŞİÖÇ\\s.,\\-():/]+$", message = "Açıklama geçersiz karakterler içeriyor.")
    private String duyuruMetniLinki;
    private Long tercihMaxSayisi;
    private Long tercihMinSayisi;
    private Long talepHakSayisi;// çalışmalarda 1 hak; mazeret vs. sınırsız hak verilir. sınırsız = -1
        private LocalDate basvuruBaslangicTarihi;
    private LocalDate basvuruBitisTarihi;
    private LocalDate sonucAciklamaTarihi;
    @Nullable  //ilk kayıtta false/NULL olacak. bunun onayı ikinci bir işlemle yapılması daha mantıklı
    private Boolean isActive;//tercihte bulunmanın aktif edilmesi
    @Nullable //ilk kayıtta false/NULL olacak. bunun onayı, isactive true ise gnlmdr vs. onaylayacak.
    private Boolean isOnaylandi;//gnlmdr vs. onayıyla yürürlüğe girecek.

    @ManyToOne 
        private TalepTurleriEntity talepTurleri;




    @OneToMany(mappedBy = "adminTercihIslemleri", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TayinTalebiEntity> tayinTalebiList;
    
    
    
    
}
