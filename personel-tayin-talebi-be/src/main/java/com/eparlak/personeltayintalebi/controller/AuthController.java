package com.eparlak.personeltayintalebi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eparlak.personeltayintalebi.dto.LoginRequest;
import com.eparlak.personeltayintalebi.dto.LoginResponse;
import com.eparlak.personeltayintalebi.dto.PersonelTemelBilgiDTO;
import com.eparlak.personeltayintalebi.entity.PersonelEntity;
import com.eparlak.personeltayintalebi.security.PersonelUserDetails;
import com.eparlak.personeltayintalebi.service.JwtService;
import com.eparlak.personeltayintalebi.util.ConvertToPersonelEntityTemelBilgiDTO;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //aut işlemleri 

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ConvertToPersonelEntityTemelBilgiDTO convertToPersonelTemelBilgiDTO;
    

    public AuthController(AuthenticationManager authenticationManager,
    JwtService jwtService,
    ConvertToPersonelEntityTemelBilgiDTO convertToPersonelTemelBilgiDTO
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.convertToPersonelTemelBilgiDTO = convertToPersonelTemelBilgiDTO;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        
        System.out.println("SicilNo: " + loginRequest.getSicilNo());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getSicilNo(), loginRequest.getPassword()));

            System.out.println("Authentication başarılı: " + authentication.getName());

            String token = jwtService.generateToken(authentication.getName());
            System.out.println("JWT Token üretildi: " + token.substring(0, 20) + "...");

            jwtService.debugToken(token);
            String role = authentication.getAuthorities().iterator().next().getAuthority();

            LoginResponse response = new LoginResponse(
                    token,
                    "Login başarılı",
                    authentication.getName(),
                    role,
                    86400000L);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("Login hatası: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Hatalı kullanıcı bilgileri", null, null, null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // jwtBlackList oluşturulup oraya eklenip, jwt geçersiz hale getirilebilir.
        //öldürülen jwtler db de tutulabilir.
        // Proje kapsamına göre tarayıcıdan  silmekle yetindim. 
        return ResponseEntity.ok("Logout başarılı - Token geçersiz listesine alındı-- LİSTEYE ALMA  KISMI YAPILMAMIŞTIR. SADECE TEST AMACLI YAPILDI.");
    }

    @GetMapping("/profile")
    public ResponseEntity<PersonelTemelBilgiDTO> profile() {
     

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonelUserDetails userDetails = (PersonelUserDetails) authentication.getPrincipal();

            PersonelEntity personel = userDetails.getPersonel();

            System.out.println("Personel ID: " + personel.getId());
            System.out.println("Personel Adı: " + personel.getAd() + " " + personel.getSoyad());

            PersonelTemelBilgiDTO dto = convertToPersonelTemelBilgiDTO.convertToDto(personel);

            return ResponseEntity.status(HttpStatus.OK).body(dto);

        } catch (Exception e) {
            System.out.println("Profil hatası: " + e.getMessage());
            throw new RuntimeException("Profil bilgisi alınamadı");
        }
    }
}
