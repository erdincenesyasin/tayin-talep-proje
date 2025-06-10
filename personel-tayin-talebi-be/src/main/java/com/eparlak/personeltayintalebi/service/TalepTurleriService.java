package com.eparlak.personeltayintalebi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eparlak.personeltayintalebi.entity.TalepTurleriEntity;
import com.eparlak.personeltayintalebi.repository.TalepTurleriRepository;


@Service
public class TalepTurleriService {

    private final TalepTurleriRepository talepTurleriRepository;

    public TalepTurleriService(TalepTurleriRepository talepTurleriRepository) {
        this.talepTurleriRepository = talepTurleriRepository;
    }

    public List<TalepTurleriEntity> getTalepTurleri() {
        return talepTurleriRepository.findAll();
    }

    public TalepTurleriEntity getTalepTurleriByTalepTuru(Long id) {
        return talepTurleriRepository.findById(id).orElseThrow(() -> new RuntimeException("Talep türü bulunamadı"));
    }

    public TalepTurleriEntity createTalepTurleri(TalepTurleriEntity talepTurleri) {
        return talepTurleriRepository.save(talepTurleri);
    }

    public TalepTurleriEntity updateTalepTurleri(Long id, TalepTurleriEntity talepTurleri) {
        return talepTurleriRepository.save(talepTurleri);
    }

    public void deleteTalepTurleri(Long id) {
        talepTurleriRepository.deleteById(id);
    }

}
