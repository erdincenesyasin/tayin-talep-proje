
package com.eparlak.personeltayintalebi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String message;
    private String sicilNo;
    private String role;
    private Long expiresIn; // 24 saat = 86400000 ms
}