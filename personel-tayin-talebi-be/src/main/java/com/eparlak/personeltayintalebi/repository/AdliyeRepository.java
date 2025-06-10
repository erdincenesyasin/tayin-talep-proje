package com.eparlak.personeltayintalebi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eparlak.personeltayintalebi.entity.AdliyeEntity;

@Repository
public interface AdliyeRepository extends JpaRepository<AdliyeEntity, Long> {
    
}
