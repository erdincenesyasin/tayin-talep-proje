package com.eparlak.personeltayintalebi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eparlak.personeltayintalebi.dto.AdminIslemleriRequestDTO;
import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.repository.AdminTercihIslemleriRepository;
import com.eparlak.personeltayintalebi.repository.PersonelRepository;


@Component
public class TestVerileriniYukleyenClass implements CommandLineRunner {

    private final PersonelRepository personelRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminTercihIslemleriRepository adminTercihIslemleriRepository;

    public TestVerileriniYukleyenClass(PersonelRepository personelRepository, PasswordEncoder passwordEncoder, AdminTercihIslemleriRepository adminTercihIslemleriRepository) {
        this.personelRepository = personelRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminTercihIslemleriRepository = adminTercihIslemleriRepository;
    }

    @Override
    public void run(String... args) throws Exception {
///program açılışınca tet kullanıcıları için düşünmüştüm. 
///
//*** veritabanı oluştur */


      
      /*  if(personelRepository.count() <5){
           
            PersonelEntity personel = new PersonelEntity();
            personel.setAd("Enes");
            personel.setSoyad("Taha");
            personel.setSicilNo("11111");
            personel.setEmail("test@test.com");
            personel.setTelefon("05551111111");
            personel.setCalistigiAdliye("Konya");
            personel.setUnvan("GENEL MÜDÜR");
            personel.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
            personel.setAktifMi(true);
            personel.setRole("GNLMDR");
            personel.setPassword(passwordEncoder.encode("1234")); 
            personelRepository.save(personel);

            PersonelEntity personel2 = new PersonelEntity();
            personel2.setAd("Yasin");
            personel2.setSoyad("Kerem");
            personel2.setSicilNo("22222");
            personel2.setEmail("test@test.com");
            personel2.setTelefon("05551111111");
            personel2.setCalistigiAdliye("Konya");
            personel2.setUnvan("GENEL MÜDÜR YARDIMCISI");
            personel2.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
            personel2.setAktifMi(true);
            personel2.setRole("GNLMDRYRD");
            personel2.setPassword(passwordEncoder.encode("1234")); 
            personelRepository.save(personel2);

            PersonelEntity personel3 = new PersonelEntity();
            personel3.setAd("Yasin");
            personel3.setSoyad("Kerem");
            personel3.setSicilNo("33333");
            personel3.setEmail("test@test.com");
            personel3.setTelefon("05551111111");
            personel3.setCalistigiAdliye("Konya");
            personel3.setUnvan("PGM TAŞRA ATAMA ŞUBE MEMURU");
            personel3.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
            personel3.setAktifMi(true);
            personel3.setRole("SBMEMUR");
            personel3.setPassword(passwordEncoder.encode("1234")); 
            personelRepository.save(personel3);


            PersonelEntity personel4 = new PersonelEntity();
            personel4.setAd("Yasin");
            personel4.setSoyad("Kerem");
            personel4.setSicilNo("44444");
            personel4.setEmail("test@test.com");
            personel4.setTelefon("05551111111");
            personel4.setCalistigiAdliye("Konya");
            personel4.setUnvan("Adliye Yazı İşleri Müdürü");
            personel4.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
            personel4.setAktifMi(true);
            personel4.setRole("USER"); //BAKANLIK DIŞI PERSONEL
            personel4.setPassword(passwordEncoder.encode("1234")); 
            personelRepository.save(personel4);

            PersonelEntity personel5 = new PersonelEntity();
            personel5.setAd("Yasin");
            personel5.setSoyad("Kerem");
            personel5.setSicilNo("55555");
            personel5.setEmail("test@test.com");
            personel5.setTelefon("05551111111");
            personel5.setCalistigiAdliye("Konya");
            personel5.setUnvan("TEST ADMIN TAM YETKİLİ");
            personel5.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
            personel5.setAktifMi(true);
            personel5.setRole("TESTADMIN"); //HERŞEYE YETKİLİ, TEST İÇİN
            personel5.setPassword(passwordEncoder.encode("1234")); 
            personelRepository.save(personel5);




            AdminTercihIslemleriEntity adminTercihIslemleri = new AdminTercihIslemleriEntity();
            adminTercihIslemleri.setBasvuruBaslangicTarihi(LocalDate.parse("2025-01-01"));
            adminTercihIslemleri.setBasvuruBitisTarihi(LocalDate.parse("2050-07-07"));
            adminTercihIslemleri.setSonucAciklamaTarihi(LocalDate.parse("2050-07-07"));
            adminTercihIslemleri.setTercihMaxSayisi(10L);
            adminTercihIslemleri.setTercihMinSayisi(1L);
            adminTercihIslemleri.setTercihDonemiAciklamaMetniBasligi("MAZERET-CALISMADISI");
            adminTercihIslemleri.setDuyuruMetniLinki("");
            adminTercihIslemleri.setIsActive(true);
            adminTercihIslemleri.setIsOnaylandi(true);
            adminTercihIslemleriRepository.save(adminTercihIslemleri);



        }*/
    }


    }