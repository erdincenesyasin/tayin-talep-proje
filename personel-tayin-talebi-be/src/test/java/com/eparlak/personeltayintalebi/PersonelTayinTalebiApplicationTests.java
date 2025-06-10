package com.eparlak.personeltayintalebi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eparlak.personeltayintalebi.dto.TayinTalebiRequestDTO;
import com.eparlak.personeltayintalebi.entity.AdliyeEntity;
import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.entity.TalepTurleriEntity;
import com.eparlak.personeltayintalebi.entity.TercihEntity;
import com.eparlak.personeltayintalebi.repository.PersonelRepository;
import com.eparlak.personeltayintalebi.security.PersonelUserDetails;
import com.eparlak.personeltayintalebi.service.AdliyeService;
import com.eparlak.personeltayintalebi.service.AdminTercihIslemleriService;
import com.eparlak.personeltayintalebi.service.PersonelService;
import com.eparlak.personeltayintalebi.service.TalepTurleriService;
import com.eparlak.personeltayintalebi.service.TayinTalebiService;
import com.github.javafaker.Faker;
import java.util.Random;

//* pasife aldım.
class PersonelTayinTalebiApplicationTests {

    @Autowired
    private AdminTercihIslemleriService adminTercihIslemleriService;

    @Autowired
    private PersonelRepository personelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonelService personelService;

    @Autowired
    private AdliyeService adliyeService;

    @Autowired
    private TayinTalebiService tayinTalebiService;

    @Autowired
    private TalepTurleriService talepTurleriService;

    Random random = new Random();

    @Order(1)
    void tumAdliyeleriKaydet() {

        // 81 TANE ADİYE OLUŞTURUR...
        List<AdliyeEntity> adliyeList = adliyeService.getAllAdliye();
        if (!adliyeList.isEmpty()) {
            throw new IllegalStateException("Adliye listesi zaten var. Önceki testleri çalıştırınız.");

        }
        List<String> sehirler = Arrays.asList(
                "ADANA", "ADIYAMAN", "AFYONKARAHİSAR", "AĞRI", "AKSARAY", "AMASYA", "ANKARA", "ANTALYA", "ARDAHAN", "ARTVİN", "AYDIN", "BALIKESİR", "BARTIN", "BATMAN", "BAYBURT", "BİLECİK", "BİNGÖL", "BİTLİS", "BOLU", "BURDUR", "BURSA", "ÇANAKKALE", "ÇANKIRI", "ÇORUM", "DENİZLİ", "DİYARBAKIR", "DÜZCE", "EDİRNE", "ELAZIĞ", "ERZİNCAN", "ERZURUM", "ESKİŞEHİR", "GAZİANTEP", "GİRESUN", "GÜMÜŞHANE", "HAKKARİ", "HATAY", "IĞDIR", "ISPARTA", "İSTANBUL", "İZMİR", "KAHRAMANMARAŞ", "KARABÜK", "KARAMAN", "KARS", "KASTAMONU", "KAYSERİ", "KIRIKKALE", "KIRKLARELİ", "KIRŞEHİR", "KİLİS", "KOCAELİ", "KONYA", "KÜTAHYA", "MALATYA", "MANİSA", "MARDİN", "MERSİN", "MUĞLA", "MUŞ", "NEVŞEHİR", "NİĞDE", "ORDU", "OSMANİYE", "RİZE", "SAKARYA", "SAMSUN", "SİİRT", "SİNOP", "SİVAS", "ŞANLIURFA", "ŞIRNAK", "TEKİRDAĞ", "TOKAT", "TRABZON", "TUNCELİ", "UŞAK", "VAN", "YALOVA", "YOZGAT", "ZONGULDAK");

        for (String sehir : sehirler) {
            AdliyeEntity adliye = new AdliyeEntity();
            adliye.setAd(sehir);
            adliyeList.add(adliye);

        }
        adliyeService.postAdliyeList(adliyeList);

    }

    
    @Order(2)
    void besTaneAdminYetkiliBirTaneUserPersonelOlustur() {

        // 6 TANE PERSONEL KAYDI OLUŞTURUR
        // GNLMDR, GNLMDRYRD, SBMEMUR, USER, TESTADMIN, SBMDR
        // 11111, 22222, 33333, 44444, 55555, 66666 sicilleri sırasıyla.
        // PASSWORD 1234 HEPSİNİN
        // RASTGELE ŞEKİLDE 100 TANE PERSONEL İÇİN AYRI METOT YAZIDI..

        // test iki kez çalışırsa sicilden dolayı hata fırtaltır. sicil unique olduğu için.. fazladan exception
        // yazmadım.

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
        personel4.setRole("USER"); // BAKANLIK DIŞI PERSONEL
        personel4.setPassword(passwordEncoder.encode("1234"));
        personelRepository.save(personel4);

        PersonelEntity personel5 = new PersonelEntity();
        personel5.setAd("Yasin");
        personel5.setSoyad("Kerem");
        personel5.setSicilNo("55555");
        personel5.setEmail("test@test.com");
        personel5.setTelefon("05551111111");
        personel5.setCalistigiAdliye("Konya");
        personel5.setUnvan("TEST ADMIN ");
        personel5.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
        personel5.setAktifMi(true);
        personel5.setRole("TESTADMIN"); // HERŞEYE YETKİLİ, TEST İÇİN
        personel5.setPassword(passwordEncoder.encode("1234"));
        personelRepository.save(personel5);

        PersonelEntity personel6 = new PersonelEntity();
        personel6.setAd("Yasin");
        personel6.setSoyad("Kerem");
        personel6.setSicilNo("66666");
        personel6.setEmail("test@test.com");
        personel6.setTelefon("05551111111");
        personel6.setCalistigiAdliye("Konya");
        personel6.setUnvan("ŞUBE MÜDÜRÜ");
        personel6.setIseBaslamaTarihi(LocalDate.parse("2005-01-01"));
        personel6.setAktifMi(true);
        personel6.setRole("SBMDR"); // HERŞEYE YETKİLİ, TEST İÇİN
        personel6.setPassword(passwordEncoder.encode("1234"));
        personelRepository.save(personel6);



    }

    
    @Order(3)
    void elliTaneSahtepersonelOlustur() {
        List<AdliyeEntity> adliyeList = adliyeService.getAllAdliye();
        if (adliyeList.isEmpty()) {
            throw new IllegalStateException(
                    "Kaydedilecek personelin çalışma yerleri adliye listesinden random çekiliyor. Adliye listesi boş. Önce tumAdliyeleriKaydet testini çalıştırınız.");
        }
        Faker faker = new Faker();
        for (int i = 0; i < 51; i++) {
            PersonelEntity personel = new PersonelEntity();
            personel.setAd(faker.name().firstName().replaceAll("[^a-zA-ZğüşöçıİĞÜŞÖÇ]", ""));// HARF DIŞINDA KARAKTER
                                                                                             // ATIYOR BAZEN önlemek için
            personel.setSoyad(faker.name().lastName().replaceAll("[^a-zA-ZğüşöçıİĞÜŞÖÇ]", ""));
            personel.setSicilNo(faker.number().digits(8));

            personel.setTelefon(faker.number().digits(10));
            personel.setUnvan("ZABIT KATİBİ");
            personel.setPassword(passwordEncoder.encode("1234"));
            personel.setRole("USER");
            personel.setAktifMi(true);
            if (adliyeList.isEmpty()) {
                throw new IllegalStateException("Adliye listesi boş. Önce tumAdliyeleriKaydet testini çalıştırınız.");
            }
            personel.setCalistigiAdliye(adliyeList.get(random.nextInt(adliyeList.size())).getAd());

            personelRepository.save(personel);

        }
    }

    
    @Order(4)
    void talepTurleriOlustur() {

        TalepTurleriEntity talepTurleri = new TalepTurleriEntity();
        talepTurleri.setTalepTuru("GENEL_ISTEK_CALISMA_KAPSAMINDA");
        talepTurleriService.createTalepTurleri(talepTurleri);
        
        TalepTurleriEntity talepTurleri2 = new TalepTurleriEntity();
        talepTurleri2.setTalepTuru("GENEL_ISTEK_CALISMA_DISI");
        talepTurleriService.createTalepTurleri(talepTurleri2);
        TalepTurleriEntity talepTurleri3 = new TalepTurleriEntity();
        talepTurleri3.setTalepTuru("MAZERET_SAGLIK");
        talepTurleriService.createTalepTurleri(talepTurleri3);
        TalepTurleriEntity talepTurleri4 = new TalepTurleriEntity();
        talepTurleri4.setTalepTuru("MAZERET_CAN");
        talepTurleriService.createTalepTurleri(talepTurleri4);
        TalepTurleriEntity talepTurleri5 = new TalepTurleriEntity();
        talepTurleri5.setTalepTuru("MAZERET_AILE");
        talepTurleriService.createTalepTurleri(talepTurleri5);

    }

    
    @Order(5)
    void tayinCalismalariOlustur() {

     
        /// 7 TANE TAYİN ÇALIŞMASI OLUŞTURUR..
        /// 
        ///*
        ///*   4 numaralı testte oluşturulan talep türlerine göre tayin çalışması oluşturuluyor.
        ///*
        ///*   YUKARIDAKİLERDEN 7 TANE OLUŞTURUR.
        


        Map<Long, String> tercihDonemiAciklamaMetniBasligiList = new HashMap<>();

        tercihDonemiAciklamaMetniBasligiList.put(1L,"XXX SAYILI YASANIN GEÇİCİ 5. MADDESİNE GÖRE NAKİL ÇALIŞMASI");
        tercihDonemiAciklamaMetniBasligiList.put(1L,"2025 YILI KADROLU PERSONELİN İSTEĞE BAĞLI NAKİL ÇALIŞMASI");
        tercihDonemiAciklamaMetniBasligiList.put(1L,"DEPREM BÖLGESİ İÇİN NAKLEN ATAMA VE VEYA GEÇİCİ GÖREVLENDİRME ÇALIŞMASI");
        tercihDonemiAciklamaMetniBasligiList.put(1L,"2025 YILI SÖZLEŞMELİ PERSONELİN KARŞILIKLI YER DEĞİŞTİRME ÇALIŞMASI");
        tercihDonemiAciklamaMetniBasligiList.put(3L,"SAĞLIK MAZERETİNE DAYALI TAYİN TALEPLERİ");
        tercihDonemiAciklamaMetniBasligiList.put(4L,"CAN GÜVENLİĞİ MAZERETİNE DAYALI TAYİN TALEPLERİ");
        tercihDonemiAciklamaMetniBasligiList.put(5L,"AİLE MAZERETİNE DAYALI TAYİN TALEPLERİ");


        for (Map.Entry<Long, String> entry : tercihDonemiAciklamaMetniBasligiList.entrySet()) {

         
            AdminTercihIslemleriEntity adminTercihIslemleri = new AdminTercihIslemleriEntity();
            adminTercihIslemleri.setBasvuruBaslangicTarihi(LocalDate.now().minusDays(10));
            adminTercihIslemleri.setDuyuruMetniLinki("ÇALIŞMANIN KAPSAMI,BAŞVURU ŞARTLARI,SONUÇLAR, vs. gibi bilgiler");
            adminTercihIslemleri.setBasvuruBitisTarihi(LocalDate.now().plusDays(30));
            adminTercihIslemleri.setSonucAciklamaTarihi(LocalDate.now().plusDays(40));
            adminTercihIslemleri.setTercihDonemiAciklamaMetniBasligi(entry.getValue());
            adminTercihIslemleri.setTalepHakSayisi(entry.getKey()==1?1:-1L);/// mazeretleri sınırsız -1; diğerlerine 1 hak
            adminTercihIslemleri.setTalepTurleri(talepTurleriService.getTalepTurleriByTalepTuru(entry.getKey()));
            adminTercihIslemleri.setTercihMaxSayisi(10L);
            adminTercihIslemleri.setTercihMinSayisi(1L);
          //  adminTercihIslemleri.setIsOnaylandi(true);
            //adminTercihIslemleri.setIsActive(true);

            //DTO dan create yaparken onay ve aktif kısımları true yapılamıyor. 
            // true yapsın diye testlerde kullanılması için  serviste bir metot yazdım.
            
            adminTercihIslemleriService.testIcinAdminTercihIslemleriOlustur(adminTercihIslemleri);
        }

    }

    
    @Order(6)
    void tumPersonelTayinTalebiOlustur() {

        // TÜM PERSONELİN TAYİN İSTEDİĞİNİ DÜŞÜNÜYORUZ..

        List<AdliyeEntity> adliyeList = adliyeService.getAllAdliye();

        if (adliyeList.isEmpty()) {
            throw new IllegalStateException("Adliye listesi boş. Önce tumAdliyeleriKaydet testini çalıştırınız.");
        }

        List<AdminTercihIslemleriEntity> adminTercihIslemleriList = adminTercihIslemleriService
                .getAllAdminTercihIslemleri();

        if (adminTercihIslemleriList.isEmpty()) {
            throw new IllegalStateException(
                    "Admin tercih islemi listesi boş. Önce tayinCalismalariOlustur testini çalıştırınız.");
        }

        List<PersonelEntity> personelList = personelService.getAllRoleUserEntity();

        if (personelList.isEmpty()) {
            throw new IllegalStateException(
                    "Personel listesi boş. Önce besTaneAdminYetkiliPersonelOlustur SONRA yuzTaneSahtepersonelOlustur testini çalıştırınız.");
        }

        for (PersonelEntity personel : personelList) {
            // tüm personel tercihte bulunacak.
            // random adliye seçecek
            // tüm tayin çalışmaları için ayrı ayrı talepte bulanacak.
            // 7 çalışma için 100 personel 10'ar tane adliye seçecek...700 tane talepentity - 7000 tane adliyeentity tercihi
            // oluşması gerekiyor.

            Faker faker = new Faker();//sahte içerik oluşturmak için kullanıldı.

            PersonelUserDetails sahtePersonel = new PersonelUserDetails(personel);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(sahtePersonel, null,
                    new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(auth);

            for (AdminTercihIslemleriEntity adminTercihIslemleri : adminTercihIslemleriList) {

                TayinTalebiRequestDTO tayinTalebiRequestDTO = new TayinTalebiRequestDTO();
                tayinTalebiRequestDTO.setAdminTercihIslemleri(adminTercihIslemleri);

                tayinTalebiRequestDTO.setAciklama(faker.lorem().characters(500));
                
                // tercih listesi oluşturulyuor...
                List<TercihEntity> tercihList = new ArrayList<>();
                for (int i = 1; i < 11; i++) {
                    TercihEntity tercih = new TercihEntity();
                    // random adliye seçecek

                    AdliyeEntity randomAdliye = adliyeList.get(random.nextInt(adliyeList.size()));
                    tercih.setAdliye(randomAdliye);
                    tercih.setSira(i);
                    tercihList.add(tercih);
                }

                tayinTalebiRequestDTO.setTercihList(tercihList);

                tayinTalebiService.createTayinTalebi(tayinTalebiRequestDTO);
            }
            SecurityContextHolder.clearContext();

        }
    }

}