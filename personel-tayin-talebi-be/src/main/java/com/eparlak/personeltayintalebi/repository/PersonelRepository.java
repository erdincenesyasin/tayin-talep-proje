package com.eparlak.personeltayintalebi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eparlak.personeltayintalebi.entity.PersonelEntity;
@Repository
public interface PersonelRepository extends JpaRepository<PersonelEntity, Long> {

   Optional<PersonelEntity> findBySicilNo(String sicilNo);
   List<PersonelEntity> findByRole(String role);
}
