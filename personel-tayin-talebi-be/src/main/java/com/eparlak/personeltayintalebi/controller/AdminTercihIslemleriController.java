package com.eparlak.personeltayintalebi.controller;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eparlak.personeltayintalebi.dto.AdminIslemleriRequestDTO;
import com.eparlak.personeltayintalebi.dto.tumbasvurular.TumBasvuralarResponseDTO;
import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;
import com.eparlak.personeltayintalebi.service.AdminTercihIslemleriService;
import com.eparlak.personeltayintalebi.service.TayinTalebiService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/tercih")
public class AdminTercihIslemleriController {

    //tüm endpointler düzenlenmedi. mevcut ihtiyaç kadarı ele alındı.

    private final AdminTercihIslemleriService adminTercihIslemleriService;
    private final TayinTalebiService tayinTalebiService;


    public AdminTercihIslemleriController(AdminTercihIslemleriService adminTercihIslemleriService, TayinTalebiService tayinTalebiService) {
        this.adminTercihIslemleriService = adminTercihIslemleriService;
        this.tayinTalebiService = tayinTalebiService;
    }

    @GetMapping
    public List<AdminTercihIslemleriEntity> getAdminTercihIslemleri() {
        return adminTercihIslemleriService.getAllAdminTercihIslemleri();
    }

    @GetMapping("/{id}")
    public AdminTercihIslemleriEntity getAdminTercihIslemleri(@PathVariable Long id) {
        return adminTercihIslemleriService.getAdminTercihIslemleriById(id);
    }
    @PostMapping
    public ResponseEntity<AdminTercihIslemleriEntity> createAdminTercihIslemleri(@Valid @RequestBody AdminIslemleriRequestDTO adminTercihIslemleri) {
        AdminTercihIslemleriEntity entity = adminTercihIslemleriService.createAdminTercihIslemleri(adminTercihIslemleri);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("/{id}")
    public AdminTercihIslemleriEntity updateAdminTercihIslemleri(@RequestBody AdminTercihIslemleriEntity adminTercihIslemleri) {
        return adminTercihIslemleriService.updateAdminTercihIslemleri(adminTercihIslemleri);
    }
    

    //delete yerine softdelete yapılabilirdi. silindi col u true yapılarak. daha detaya girmedim.
    //admin paneli tam yapılmadı
    @DeleteMapping("/{id}")
    public void deleteAdminTercihIslemleri(@PathVariable Long id) {
        adminTercihIslemleriService.deleteAdminTercihIslemleri(id);
    }

    @PatchMapping("/isActive/{id}")
    public void isActiveAdminTercihIslemleri(@PathVariable Long id) {
        adminTercihIslemleriService.isActiveAdminTercihIslemleri(id);
    }


    
    @GetMapping("/aktif-basvurular")
    public List<AdminTercihIslemleriEntity> getAktifBasvurular() {
        System.out.println("getAktifBasvurular");
        return adminTercihIslemleriService.getAktifBasvurular();
    }

    @PatchMapping ("/onayla/{id}")
    public ResponseEntity<String> onaylaAdminTercihIslemleri(@PathVariable Long id) {
        adminTercihIslemleriService.onaylaAdminTercihIslemleri(id);
        return ResponseEntity.ok("Tayin Çalışması onaylandı.");
    }

    
    @GetMapping("/basvurular-listesi/{id}")
    public Page<TumBasvuralarResponseDTO> getBasvurularListesi(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

                if(size > 100){
                    size = 100;
                }

                /// aşırı veri çekmeyi önlemek için. if koydum. Pageable olarak alınıp propertieste
              
    
        Pageable pageable = PageRequest.of(page, size);
        return tayinTalebiService.getBasvurularListesi2Page(id, pageable);
    }

   



}
