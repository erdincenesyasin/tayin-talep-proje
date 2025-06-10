package com.eparlak.personeltayintalebi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eparlak.personeltayintalebi.entity.AdliyeEntity;
import com.eparlak.personeltayintalebi.service.AdliyeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adliye")// sadece admin için istek atabilir.  adliye/tercih-formu-icin-adliye-listesi endpointine ise oturum açmış herkes istek atabilir.
public class AdliyeController {
//bu kısım test için toplu adliye ismi yüklemek için kullanıldı. kapsamlı olarak endpointler düzenlenmedi.
 
    private final AdliyeService adliyeService;  

    public AdliyeController(AdliyeService adliyeService) {
        this.adliyeService = adliyeService;
    }

    @GetMapping
    public List<AdliyeEntity> getAllAdliye() {
        return adliyeService.getAllAdliye();
        }

    @GetMapping("/{id}")
    public AdliyeEntity getAdliyeById(@PathVariable Long id) {
        return adliyeService.getAdliyeById(id);
    }

    @PostMapping
    public AdliyeEntity createAdliye(@Valid @RequestBody AdliyeEntity adliye) {
        return adliyeService.createAdliye(adliye);
    }

   
    @PostMapping("/listeyi-kaydet")
    public void postAdliyeList(@Valid @RequestBody List<AdliyeEntity> adliyeList) {
        adliyeService.postAdliyeList(adliyeList);
    }



    

    @GetMapping("/tercih-formu-icin-adliye-listesi") //oturum açmış herkes istek atabilir. 
    public List<AdliyeEntity> getTercihFormuAllAdliye() {
        return adliyeService.getAllAdliye();
        }


}
