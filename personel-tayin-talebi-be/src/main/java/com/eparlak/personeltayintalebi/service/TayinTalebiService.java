package com.eparlak.personeltayintalebi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.eparlak.personeltayintalebi.dto.AdliyeBilgisiDTO;
import com.eparlak.personeltayintalebi.dto.PersonelTemelBilgiDTO;
import com.eparlak.personeltayintalebi.dto.TayinTalebiPageConvertDTO;
import com.eparlak.personeltayintalebi.dto.TayinTalebiRequestDTO;
import com.eparlak.personeltayintalebi.dto.TayinTalebiTemelBilgiDTO;
import com.eparlak.personeltayintalebi.dto.TercihResponseDTO;
import com.eparlak.personeltayintalebi.dto.TumBasvurularResponseDTO;
import com.eparlak.personeltayintalebi.dto.tumbasvurular.PersonelDTO;
import com.eparlak.personeltayintalebi.dto.tumbasvurular.TayinTalebiDTO;
import com.eparlak.personeltayintalebi.dto.tumbasvurular.TercihDTO;
import com.eparlak.personeltayintalebi.dto.tumbasvurular.TumBasvuralarResponseDTO;
import com.eparlak.personeltayintalebi.entity.AdliyeEntity;
import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.entity.TayinTalebiEntity;
import com.eparlak.personeltayintalebi.entity.TercihEntity;
import com.eparlak.personeltayintalebi.exception.CustomMukerrerTayinTalebi;
import com.eparlak.personeltayintalebi.repository.TayinTalebiRepository;
import com.eparlak.personeltayintalebi.security.PersonelUserDetails;


@Service
public class TayinTalebiService {

    private final TayinTalebiRepository tayinTalebiRepository;
    private final AdminTercihIslemleriService adminTercihIslemleriService;
    private final AdliyeService adliyeService;
    private final ModelMapper mapper;

    public TayinTalebiService(TayinTalebiRepository tayinTalebiRepository, PersonelService personelService,
            AdminTercihIslemleriService adminTercihIslemleriService, AdliyeService adliyeService, ModelMapper mapper) {
        this.tayinTalebiRepository = tayinTalebiRepository;
      
        this.adminTercihIslemleriService = adminTercihIslemleriService;
        this.adliyeService = adliyeService;
        this.mapper = mapper;
    }

    public TayinTalebiTemelBilgiDTO createTayinTalebi(TayinTalebiRequestDTO tayinTalebiDTO) {
        PersonelEntity personel = getPersonelFromSecurityContext();

        Long talepHakSayisi = adminTercihIslemleriService
                .getTalepHakSayisi(tayinTalebiDTO.getAdminTercihIslemleri().getId());
        if (talepHakSayisi > -1) {

            if (talepHakSayisi > 1) {
                Long talepSayisi = tayinTalebiRepository.countByPersonelIdAndAdminTercihIslemleriId(
                        personel.getId(),
                        tayinTalebiDTO.getAdminTercihIslemleri().getId());

                if (talepSayisi >= talepHakSayisi) {
                    throw new RuntimeException("HATA: Bu nakil çalışması için  bu personelin " + talepSayisi
                            + " adet tercihi var. Tekrar yeni tercih oluşturamaz. İzin verilen hak sayısı: "
                            + talepHakSayisi);
                }
            }
            // talep hakkı 0 olamayacağı (valid) için 0 ı kontrol etmedim.
            if (talepHakSayisi == 1) {
                Boolean tercihYapilmisMi = tayinTalebiRepository.existsByPersonelIdAndAdminTercihIslemleriId(
                        personel.getId(),
                        tayinTalebiDTO.getAdminTercihIslemleri().getId());
                if (tercihYapilmisMi) {

                    throw new CustomMukerrerTayinTalebi(
                            "HATA: Bu nakil çalışması için  bu personelin zaten tercihi var. Tekrar yeni tercih oluşturamaz.");
                }
            }

        }

        TayinTalebiEntity tayinTalebi = mapper.map(tayinTalebiDTO, TayinTalebiEntity.class);
        tayinTalebi.setPersonel(personel);

        tayinTalebi.setDurum("BEKLEMEDE");

        AdminTercihIslemleriEntity adminTercihIslemleri = adminTercihIslemleriService
                .getAdminTercihIslemleriById(tayinTalebi.getAdminTercihIslemleri().getId());
        tayinTalebi.setAdminTercihIslemleri(adminTercihIslemleri);

        for (TercihEntity tercih : tayinTalebi.getTercihList()) {
            AdliyeEntity adliye = adliyeService.getAdliyeById(tercih.getAdliye().getId());
            tercih.setAdliye(adliye);
            tercih.setTayinTalebi(tayinTalebi);
        }

        TayinTalebiEntity tayinTalebiEntity = tayinTalebiRepository.save(tayinTalebi);
        return convertToTayinTalebiTemelBilgiDTO(tayinTalebiEntity);
    }

    public List<TayinTalebiTemelBilgiDTO> getAllTayinTalebi() {
        PersonelEntity personel = getPersonelFromSecurityContext();
        List<TayinTalebiEntity> tayinTalebiList = tayinTalebiRepository.findAllByPersonelId(personel.getId());
        return convertToDTOList(tayinTalebiList);

    }

    // bu serviste convert işlemini list olarakta yapan metot konulmuştur. diğer
    // servislere list convert koymadım.
    private List<TayinTalebiTemelBilgiDTO> convertToDTOList(List<TayinTalebiEntity> tayinTalebiList) {
        List<TayinTalebiTemelBilgiDTO> tayinTalebiTemelBilgiDTOList = new ArrayList<>();

        for (TayinTalebiEntity tayinTalebi : tayinTalebiList) {

            PersonelTemelBilgiDTO personelTemelBilgiDTO = mapper.map(tayinTalebi.getPersonel(),
                    PersonelTemelBilgiDTO.class);
            TayinTalebiTemelBilgiDTO tayinTalebiTemelBilgiDTO = mapper.map(tayinTalebi, TayinTalebiTemelBilgiDTO.class);
            tayinTalebiTemelBilgiDTO.setPersonel(personelTemelBilgiDTO);
            tayinTalebiTemelBilgiDTO.setTercihList(tayinTalebi.getTercihList());
            tayinTalebiTemelBilgiDTOList.add(tayinTalebiTemelBilgiDTO);
        }

        return tayinTalebiTemelBilgiDTOList;
    }

    public TayinTalebiTemelBilgiDTO getTayinTalebiById(Long id) {
        TayinTalebiEntity tayinTalebiEntity = tayinTalebiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tayin talebi bulunamadı"));
        return convertToTayinTalebiTemelBilgiDTO(tayinTalebiEntity);
    }

    public TayinTalebiTemelBilgiDTO updateTayinTalebi(TayinTalebiEntity tayinTalebi) {

        TayinTalebiEntity tayinTalebiEntity = tayinTalebiRepository.save(tayinTalebi);
        return convertToTayinTalebiTemelBilgiDTO(tayinTalebiEntity);
    }

    public void deleteTayinTalebi(Long id) {
        tayinTalebiRepository.deleteById(id);
    }

    private TayinTalebiTemelBilgiDTO convertToTayinTalebiTemelBilgiDTO(TayinTalebiEntity tayinTalebi) {

        PersonelTemelBilgiDTO personelTemelBilgiDTO = mapper.map(tayinTalebi.getPersonel(),
                PersonelTemelBilgiDTO.class);
        TayinTalebiTemelBilgiDTO tayinTalebiTemelBilgiDTO = mapper.map(tayinTalebi, TayinTalebiTemelBilgiDTO.class);
        tayinTalebiTemelBilgiDTO.setPersonel(personelTemelBilgiDTO);

        return tayinTalebiTemelBilgiDTO;
    }

    public List<TercihEntity> tercihListesi(Long id) {
        TayinTalebiEntity tayinTalebi = tayinTalebiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tayin talebi bulunamadı"));

        if (tayinTalebi.getPersonel().getId() != getPersonelFromSecurityContext().getId()) {
            throw new RuntimeException(" İstek atan kullanıcı ile tayin talebinde bulanan kişi aynı değil.");
        }

        List<TercihEntity> tercihListesi = tayinTalebi.getTercihList();
        return tercihListesi;
    }

    private PersonelEntity getPersonelFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonelUserDetails userDetails = (PersonelUserDetails) authentication.getPrincipal();

        return userDetails.getPersonel();
    }

    public Boolean tercihimVarMi(Long id) {
        PersonelEntity personel = getPersonelFromSecurityContext();

        Long talepHakSayisi = adminTercihIslemleriService
                .getTalepHakSayisi(id);
        if (talepHakSayisi > -1) {

            if (talepHakSayisi > 1) {
                Long talepSayisi = tayinTalebiRepository.countByPersonelIdAndAdminTercihIslemleriId(
                        personel.getId(),
                        id);

                if (talepSayisi >= talepHakSayisi) {//büyük olamaz, sadece eşitlikte baksakkta olur
                    return true;
                }
            }
            // talep hakkı 0 olamayacağı (valid) için 0 ı kontrol etmedim.
            if (talepHakSayisi == 1) {
                Boolean tercihYapilmisMi = tayinTalebiRepository.existsByPersonelIdAndAdminTercihIslemleriId(
                        personel.getId(),
                        id);

                if (tercihYapilmisMi) {
                    return true;
                }
            }

        }

        return false;
    }

    //// admin tarafından tüm başvuralar listelenmek istenirse controllerdeki if den gelen max sayısına göre
    /// getirecek.
    /// response DTO olarak dönüyor.
    /// TumBasvurularResponseDTO tüm DTO lardan birleştirme ile oluşturdum.

   
//pageable ile listeleme yapıldı.
//tum basvurular listesi için.
// n+1 sorunu oluşmaması ve tüm  kayıtları tek seferde çekmemesine dikkat edildi.
    public Page<TumBasvuralarResponseDTO> getBasvurularListesi2Page(Long id, Pageable pageable) {
        Page<TayinTalebiEntity> entityPage = tayinTalebiRepository.findAllByAdminTercihIslemleriId(id, pageable);
 
        return entityPage.map(entity -> {
            TumBasvuralarResponseDTO dto = new TumBasvuralarResponseDTO();
            dto.setTayinTalebi(mapper.map(entity, TayinTalebiDTO.class));
            dto.setPersonel(mapper.map(entity.getPersonel(), PersonelDTO.class));

            List<TercihDTO> tercihList = new ArrayList<>();
            for (TercihEntity tercih : entity.getTercihList()) {
                TercihDTO tercihDTO = mapper.map(tercih, TercihDTO.class);
                tercihList.add(tercihDTO);
            }
            dto.setTercihListesi(tercihList);
           

            return dto;
        });
    }

}
