package com.eparlak.personeltayintalebi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eparlak.personeltayintalebi.dto.TayinTalebiRequestDTO;
import com.eparlak.personeltayintalebi.dto.TayinTalebiTemelBilgiDTO;
import com.eparlak.personeltayintalebi.entity.TayinTalebiEntity;
import com.eparlak.personeltayintalebi.entity.TercihEntity;
import com.eparlak.personeltayintalebi.service.TayinTalebiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tayin-talebi/me")
public class TayinTalebiController {

    //tüm endpointler düzenlenmedi. ihtiyaç olanlar düzenlendi.

    private final TayinTalebiService tayinTalebiService;

    public TayinTalebiController(TayinTalebiService tayinTalebiService) {

        this.tayinTalebiService = tayinTalebiService;
    }

    @PostMapping
    public ResponseEntity<TayinTalebiTemelBilgiDTO> createTayinTalebi(
            @Valid @RequestBody TayinTalebiRequestDTO tayinTalebi) {
        TayinTalebiTemelBilgiDTO sonuc = tayinTalebiService.createTayinTalebi(tayinTalebi);
        return ResponseEntity.status(HttpStatus.CREATED).body(sonuc);
    }

    @GetMapping
    public List<TayinTalebiTemelBilgiDTO> getAllTayinTalebi() {
        return tayinTalebiService.getAllTayinTalebi();
    }

    @GetMapping("/{id}")
    public TayinTalebiTemelBilgiDTO getTayinTalebiById(@PathVariable Long id) {
        return tayinTalebiService.getTayinTalebiById(id);
    }

    @PutMapping("/guncelle")
    public TayinTalebiTemelBilgiDTO updateTayinTalebi(@Valid @RequestBody TayinTalebiEntity tayinTalebi) {
        return tayinTalebiService.updateTayinTalebi(tayinTalebi);
    }

    @GetMapping("/{id}/tercih-listesi")
    public List<TercihEntity> tercihListesi(@PathVariable Long id) {
        return tayinTalebiService.tercihListesi(id);
    }

    @GetMapping("/tercihim-var-mi/{id}")
    public ResponseEntity<Boolean> tercihimVarMi(@PathVariable Long id) {
        Boolean sonuc = tayinTalebiService.tercihimVarMi(id);
        if (sonuc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sonuc);
    }

}
