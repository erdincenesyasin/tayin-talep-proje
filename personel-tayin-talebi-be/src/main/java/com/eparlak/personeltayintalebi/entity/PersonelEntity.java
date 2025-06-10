package com.eparlak.personeltayintalebi.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"tayinTalebiList"})
public class PersonelEntity extends BaseEntity {

    @NotBlank(message = "Ad boş olamaz")
    @Size(max = 100, message = "Ad çok uzun")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Ad sadece harf içerebilir")
    @Column(name = "ad")
    private String ad;

    @NotBlank(message = "Soyad boş olamaz")
    @Size(max = 100, message = "Soyad çok uzun")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Ad sadece harf içerebilir")
    @Column(name = "soyad")
    private String soyad;

    @NotBlank(message = "Sicil numarası boş olamaz")
    @Size(max = 20, message = "Sicil numarası çok uzun")
    @Pattern(regexp = "^[0-9]+$", message = "Sicil numarası sadece rakam içerebilir")
    @Column(name = "sicil_no", unique = true)
    private String sicilNo;

    @Email(message = "Geçerli email adresi giriniz")
    @Size(max = 150, message = "Email çok uzun")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Geçerli bir email adresi giriniz")
    @Column(name = "email")
    private String email;

    @Size(max = 20, message = "Telefon numarası çok uzun")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece rakam içerebilir")
    @Column(name = "telefon")
    private String telefon;
    
    @Size(max = 200, message = "Adliye adı çok uzun")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Adliye adı sadece harf içerebilir")
    @Column(name = "calistigi_adliye")
    private String calistigiAdliye;

    @Size(max = 100, message = "Unvan çok uzun")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Unvan sadece harf içerebilir")
    @Column(name = "unvan")
    private String unvan;


    
    @Column(name = "ise_baslama_tarihi")
    private LocalDate iseBaslamaTarihi;

    @Column(name = "aktif_mi")
    private Boolean aktifMi;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(max = 255, message = "Şifre çok uzun")
    @Column(name = "password")
    private String password;

    @Column(name = "role") //ENUM da yapabilirdim.
    private String role;


    @OneToMany(mappedBy = "personel", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TayinTalebiEntity> tayinTalebiList;

   


    
    
}
