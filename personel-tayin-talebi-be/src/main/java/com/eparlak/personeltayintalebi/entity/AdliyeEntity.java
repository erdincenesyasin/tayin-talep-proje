package com.eparlak.personeltayintalebi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdliyeEntity extends BaseEntity{


    @Column(nullable = false, unique = true)
    @Size(max = 100, message = "Adliye adı çok uzun")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Adliye adı sadece harf içerebilir")
    @NotBlank(message = "Adliye adı boş olamaz")    
    private String ad; // "Ankara, Konya, İstanbul..."
}