package com.eparlak.personeltayintalebi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    @NotBlank(message = "Sicil numarası boş olamaz")
    @Size(max = 20, message = "Sicil numarası çok uzun")
    @Pattern(regexp = "^[0-9]+$", message = "Sicil numarası sadece sayı içerebilir")
    private String sicilNo;
    
    @NotBlank(message = "Şifre boş olamaz")
    @Size(max = 100, message = "Şifre çok uzun")
    private String password;
}
