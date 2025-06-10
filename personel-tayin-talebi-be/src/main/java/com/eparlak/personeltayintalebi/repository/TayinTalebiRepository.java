package com.eparlak.personeltayintalebi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eparlak.personeltayintalebi.entity.TayinTalebiEntity;

@Repository
public interface TayinTalebiRepository extends JpaRepository<TayinTalebiEntity, Long> {

    boolean existsByPersonelIdAndAdminTercihIslemleriId(Long personelId, Long adminTercihIslemleriId);
    List<TayinTalebiEntity> findAllByPersonelId(Long personelId);
    
    //PROJECTION ile db den DTO için bize lazım olan kısımları alınabilirdi ama. Projectionla ilgili bilgim sınırlı.
///n+1 sorunu oluşmaması için EntityGrap kullanıldı.
    @EntityGraph(attributePaths = {"tercihList", "tercihList.adliye", "personel"})
    Page<TayinTalebiEntity> findAllByAdminTercihIslemleriId(Long adminTercihIslemleriId, Pageable pageable);
       
    Long countByPersonelIdAndAdminTercihIslemleriId(Long personelId, Long adminTercihIslemleriId);


}
