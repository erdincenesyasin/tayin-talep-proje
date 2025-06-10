package com.eparlak.personeltayintalebi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eparlak.personeltayintalebi.entity.AdliyeEntity;
import com.eparlak.personeltayintalebi.repository.AdliyeRepository;

@Service
public class AdliyeService {

    //ihtiyaç olanlar düzenlendi.

    @Autowired
    private AdliyeRepository adliyeRepository;

    public List<AdliyeEntity> getAllAdliye() {
        return adliyeRepository.findAll();
    }

    public AdliyeEntity getAdliyeById(Long id) {
        return adliyeRepository.findById(id).orElse(null);
    }

    public AdliyeEntity createAdliye(AdliyeEntity adliye) {
        return adliyeRepository.save(adliye);
    }

    public AdliyeEntity updateAdliye(AdliyeEntity adliye) {
        return adliyeRepository.save(adliye);
    }

    public void deleteAdliye(Long id) {
        adliyeRepository.deleteById(id);
    }

    public void postAdliyeList(List<AdliyeEntity> adliyeList) {
        adliyeRepository.saveAll(adliyeList);
    }

}
