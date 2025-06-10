package com.eparlak.personeltayintalebi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;

@Repository
public interface AdminTercihIslemleriRepository extends JpaRepository<AdminTercihIslemleriEntity, Long> {
    List<AdminTercihIslemleriEntity> findByIsActiveTrueAndBasvuruBitisTarihiAfter(LocalDate date);
    
}
