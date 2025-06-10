package com.eparlak.personeltayintalebi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eparlak.personeltayintalebi.dto.PersonelTemelBilgiDTO;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.repository.PersonelRepository;

@Service
public class PersonelService {

    private final PasswordEncoder passwordEncoder;

    private final PersonelRepository personelRepository;

    private final ModelMapper mapper;

    public PersonelService(PersonelRepository personelRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.personelRepository = personelRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper=mapper;
    }

    public List<PersonelTemelBilgiDTO> getAllPersonels() {
        
        
        List<PersonelEntity> personelEntities=personelRepository.findAll();

        List<PersonelTemelBilgiDTO> personelTemelBilgiDTOList=new ArrayList<>();
                for (PersonelEntity personelEntity : personelEntities) {
                    PersonelTemelBilgiDTO personelTemelBilgiDTO = convertoPersonelTemelBilgiDTO(personelEntity);
                    personelTemelBilgiDTOList.add(personelTemelBilgiDTO);
        }

        return personelTemelBilgiDTOList;

    }

    public PersonelTemelBilgiDTO getPersonelById(Long id) {

        PersonelEntity personelEntity = personelRepository.findById(id).orElse(null);
        return convertoPersonelTemelBilgiDTO(personelEntity);

    }

    

    public PersonelTemelBilgiDTO createPersonel(PersonelEntity personel) {
        personel.setPassword(passwordEncoder.encode(personel.getPassword()));
        PersonelEntity personelEntity = personelRepository.save(personel);
        return convertoPersonelTemelBilgiDTO(personelEntity);
    }

    public PersonelTemelBilgiDTO updatePersonel(PersonelEntity personel) {
        PersonelEntity personelEntity = personelRepository.save(personel);
        return convertoPersonelTemelBilgiDTO(personelEntity);
    }

    public void deletePersonel(Long id) {
        personelRepository.deleteById(id);
    }

    private PersonelTemelBilgiDTO convertoPersonelTemelBilgiDTO(PersonelEntity personelEntity) {
        PersonelTemelBilgiDTO personelTemelBilgiDTO = mapper.map(personelEntity, PersonelTemelBilgiDTO.class);
        return personelTemelBilgiDTO;

    }





      public PersonelEntity getPersonelByIdEntity(Long id) {
        return personelRepository.findById(id).orElse(null);
    }


    public List<PersonelEntity> getAllRoleUserEntity() {
        return personelRepository.findByRole("USER");
    }

}
