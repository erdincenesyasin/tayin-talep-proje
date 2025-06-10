package com.eparlak.personeltayintalebi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class TalepTurleriEntity extends BaseEntity {
  
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ_\\s]+$", message = "Talep türü sadece harf içerebilir")
    private String talepTuru; //GENEL_ISTEK_CALISMA_KAPSAMINDA,GENEL_ISTEK_CALISMA_DISI,MAZERET_SAGLIK,MAZERET_CAN,MAZERET_AILE
    
    
    
}