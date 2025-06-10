package com.eparlak.personeltayintalebi.dto;

import java.time.LocalDate;

import com.eparlak.personeltayintalebi.entity.TalepTurleriEntity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminIslemleriRequestDTO {



    @Pattern(regexp = "^[a-zA-Z0-9ğüşıöçĞÜŞİÖÇ\\s.,\\-():/]+$", message = "Açıklama geçersiz karakterler içeriyor.")
    private String tercihDonemiAciklamaMetniBasligi;
   
   
    @Pattern(regexp = "^[a-zA-Z0-9ğüşıöçĞÜŞİÖÇ\\s.,\\-():/]+$", message = "Açıklama geçersiz karakterler içeriyor.")
    private String duyuruMetniLinki;
   
   
   
    @Min(value = 1, message = "Tercih max sayısı en az 1 olmalıdır")
    @Max(value = 50, message = "Tercih max sayısı en fazla 50 olmalıdır")
    private Long tercihMaxSayisi;
   
   
    @Min(value = 1, message = "Tercih min sayısı en az 1 olmalıdır")
    @Max(value = 10, message = "Tercih min sayısı en fazla 10 olmalıdır")
    private Long tercihMinSayisi;

    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ_\\s]+$", message = "Talep türü sadece ve _ karakteri harf içerebilir")
    @NotBlank(message = "Talep türü boş olamaz")
    private TalepTurleriEntity talepTuru;

    @Min(value = -1, message = "Talep hak sayısı en az -1 olmalıdır")//-1 SINIRSIZ DEMEK
    @Max(value = 100, message = "Talep hak sayısı en fazla 100 olmalıdır")
    private Long talepHakSayisi;

    @Future(message = "Başlangıç tarihi geçmiş olamaz")
    private LocalDate basvuruBaslangicTarihi;
   
    @Future(message = "Bitiş tarihi geçmiş olamaz")   
    private LocalDate basvuruBitisTarihi;
   
    @Future(message = "Sonuç açıklama tarihi geçmiş olamaz")
    private LocalDate sonucAciklamaTarihi;

public boolean tarihKontrol(){// basvuru<bitis<sonuc olacak şekilde olmalı.
    if(basvuruBaslangicTarihi.isAfter(basvuruBitisTarihi)){
        return false;
    }
    if(basvuruBitisTarihi.isAfter(sonucAciklamaTarihi)){
        return false;
    }

    long gunFarki= DAYS.between(basvuruBaslangicTarihi, basvuruBitisTarihi);
    if(gunFarki<7){
        return false;
    }
    long gunFarki2= DAYS.between(basvuruBitisTarihi, sonucAciklamaTarihi);
    if(gunFarki2<1){
        return false;
    }
    return true;
}

}
