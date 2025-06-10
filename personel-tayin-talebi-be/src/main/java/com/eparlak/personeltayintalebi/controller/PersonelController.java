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

import com.eparlak.personeltayintalebi.dto.PersonelTemelBilgiDTO;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.service.PersonelService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personel")

public class PersonelController {

    //personelcontroller testler için gerekli olduğu kadarıyla düzenlenmiştir.
    //admin personel kaydı ekranları projede bulunmamaktadır.
    
    private final PersonelService personelService;

    public PersonelController(PersonelService personelService) {
        this.personelService = personelService;
    }
    
    @GetMapping
    public List<PersonelTemelBilgiDTO> getAllPersonels() {
        return personelService.getAllPersonels();
    }

    @GetMapping("/{id}")
    public PersonelTemelBilgiDTO getPersonelById(@PathVariable Long id) {
        return personelService.getPersonelById(id);
    }

    @PostMapping
    public PersonelTemelBilgiDTO createPersonel(@Valid @RequestBody PersonelEntity personel) {
        return personelService.createPersonel(personel);
    }

    @PutMapping("/{id}")
    public PersonelTemelBilgiDTO updatePersonel(@PathVariable Long id, @Valid @RequestBody PersonelEntity personel) {
        return personelService.updatePersonel(personel);
    }

    @DeleteMapping("/{id}")
    public void deletePersonel(@PathVariable Long id) {
        personelService.deletePersonel(id);
    }


    

}
