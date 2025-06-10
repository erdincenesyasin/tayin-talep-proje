package com.eparlak.personeltayintalebi.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;
import com.eparlak.personeltayintalebi.exception.CustomTarihSiralamasiHatali;
import com.eparlak.personeltayintalebi.repository.AdminTercihIslemleriRepository;
import com.eparlak.personeltayintalebi.dto.AdminIslemleriRequestDTO;

@Service
public class AdminTercihIslemleriService {

private final AdminTercihIslemleriRepository adminTercihIslemleriRepository;
private final ModelMapper mapper;


    public AdminTercihIslemleriService(AdminTercihIslemleriRepository adminTercihIslemleriRepository, ModelMapper mapper) {
        this.adminTercihIslemleriRepository = adminTercihIslemleriRepository;
        this.mapper = mapper;
    }

    public List<AdminTercihIslemleriEntity> getAllAdminTercihIslemleri() {
        return adminTercihIslemleriRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }       

    public AdminTercihIslemleriEntity getAdminTercihIslemleriById(Long id) {
        return adminTercihIslemleriRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin tercih islemi bulunamadi"));
    }

    public AdminTercihIslemleriEntity createAdminTercihIslemleri(AdminIslemleriRequestDTO adminTercihIslemleri) {

        if(!adminTercihIslemleri.tarihKontrol()){
            throw new CustomTarihSiralamasiHatali("Tercihlerin Başlangıç Tarihi, Başvuru Bitiş Tarihi ve Sonuç Açıklanma Tarihi uyumsuz. Eski tarihli ya da sıralaması yanlış.. ");
        }
        AdminTercihIslemleriEntity entity= mapper.map(adminTercihIslemleri, AdminTercihIslemleriEntity.class);
        return adminTercihIslemleriRepository.save(entity);
    }

    public AdminTercihIslemleriEntity updateAdminTercihIslemleri(AdminTercihIslemleriEntity adminTercihIslemleri) {
        return adminTercihIslemleriRepository.save(adminTercihIslemleri);
    }

    public void deleteAdminTercihIslemleri(Long id) {
        adminTercihIslemleriRepository.deleteById(id);
    }

public void isActiveAdminTercihIslemleri(Long id) {
    AdminTercihIslemleriEntity adminTercihIslemleri = getAdminTercihIslemleriById(id);
    if(adminTercihIslemleri.getIsOnaylandi() == false){
        throw new RuntimeException("Onaylanmamış nakil çalışması aktif edilip yayınlanamaz..");
    }
    adminTercihIslemleri.setIsActive(true);
    adminTercihIslemleriRepository.save(adminTercihIslemleri);

}

public List<AdminTercihIslemleriEntity> getAktifBasvurular() {
    //başvuru bitiş tarihi dolmamış ve aktif olan nakil çalışmalarını getirecek.
    return adminTercihIslemleriRepository.findByIsActiveTrueAndBasvuruBitisTarihiAfter(LocalDate.now());
}

public void onaylaAdminTercihIslemleri(Long id) {//GENEL MÜDÜR VE GENEL MÜDÜR YARDIMCISI ROLÜNDE
    AdminTercihIslemleriEntity adminTercihIslemleri = getAdminTercihIslemleriById(id);
    adminTercihIslemleri.setIsOnaylandi(true);
    adminTercihIslemleri.setIsActive(false);// onaydan önce aktif edilmişse(önlemi var ama garanti için) 
    //önlemler alınmıştı zaten. Ama buraya da ekledim. Aktif edilmesi ayrı bir işlem olacak ve onaydan sonra olacak mutlaka.
    adminTercihIslemleriRepository.save(adminTercihIslemleri);
}

    //test verisi girmek için oluşturuldu.
    public void testIcinAdminTercihIslemleriOlustur(AdminTercihIslemleriEntity adminTercihIslemleri){
          
        adminTercihIslemleriRepository.save(adminTercihIslemleri);
    }
    
//admin tarafından user lara verilmiş olan talep hakkından fazla talep yapılıp yapılmadığını kontrol için.
    public Long getTalepHakSayisi(Long id){
        AdminTercihIslemleriEntity adminTercihIslemleri = getAdminTercihIslemleriById(id);
        
        return adminTercihIslemleri.getTalepHakSayisi();
    }
}
