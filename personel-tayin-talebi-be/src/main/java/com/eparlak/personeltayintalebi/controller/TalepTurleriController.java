package com.eparlak.personeltayintalebi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eparlak.personeltayintalebi.entity.TalepTurleriEntity;
import com.eparlak.personeltayintalebi.service.TalepTurleriService;

@RestController
@RequestMapping("/talep-turleri")
public class TalepTurleriController {
    //yetkili roller talep turleri belirlenebilir.


    private final TalepTurleriService talepTurleriService;

    public TalepTurleriController(TalepTurleriService talepTurleriService) {
        this.talepTurleriService = talepTurleriService;
    }

    @GetMapping
    public List<TalepTurleriEntity> getTalepTurleri() {
        return talepTurleriService.getTalepTurleri();
    }

    @PostMapping
    public TalepTurleriEntity createTalepTurleri(@RequestBody TalepTurleriEntity talepTurleri) {
        return talepTurleriService.createTalepTurleri(talepTurleri);


    }

    @PutMapping("/{id}")
    public TalepTurleriEntity updateTalepTurleri(@PathVariable Long id, @RequestBody TalepTurleriEntity talepTurleri) {
        return talepTurleriService.updateTalepTurleri(id, talepTurleri);
    }

    @DeleteMapping("/{id}")
    public void deleteTalepTurleri(@PathVariable Long id) {
        talepTurleriService.deleteTalepTurleri(id);
    }

}
