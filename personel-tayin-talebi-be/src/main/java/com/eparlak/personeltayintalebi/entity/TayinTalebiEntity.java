package com.eparlak.personeltayintalebi.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"tercihList", "personel"})
public class TayinTalebiEntity extends BaseEntity {

    /* bu kısmı AdminTercihIslemleriEntity ye taşıdım.
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Talep türü sadece harf içerebilir")    
    private String talepTuru; // "Aile Birliği", "Sağlık", "Eğitim" vs.
*/
    @Column(length = 500)
    @Size(max = 500, message = "Açıklama çok uzun")
    @Pattern(regexp = "^[a-zA-Z0-9ğüşıöçĞÜŞİÖÇ\\s.,\\-():/]+$", message = "Açıklama geçersiz karakterler içeriyor.")
    private String aciklama;


    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Durum sadece harf içerebilir")
    private String durum; // "BEKLEMEDE", "ONAYLANDI", "REDDEDILDI"-- sadece admin görebilir.DTO larda bu alan gösterilmeyecek.

    @OneToMany(mappedBy = "tayinTalebi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)    
    @NotNull    
    @Size(min = 1, message = "En az bir tercih gereklidir")
    
    private List<TercihEntity> tercihList;


    @ManyToOne(fetch = FetchType.LAZY)    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @NotNull
    private PersonelEntity personel;

    @ManyToOne(fetch = FetchType.LAZY)    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @NotNull
    private AdminTercihIslemleriEntity adminTercihIslemleri;

   

}
