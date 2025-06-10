package com.eparlak.personeltayintalebi.dto;

import java.util.List;


import com.eparlak.personeltayintalebi.entity.AdminTercihIslemleriEntity;
import com.eparlak.personeltayintalebi.entity.TercihEntity;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TayinTalebiRequestDTO {

    @NotBlank(message = "Açıklama boş bırakılamaz")
    @Size(max = 500, message = "Açıklama çok uzun")
    @Pattern(regexp = "^[a-zA-Z0-9ğüşıöçĞÜŞİÖÇ\\s.,\\-():/]+$", message = "Açıklama alanı geçersiz karakterler içeriyor.")
    private String aciklama;

    @NotEmpty(message = "En az bir tercih gereklidir")
    private List<TercihEntity> tercihList;

  @NotNull(message = "Admin tercih işlemleri boş bırakılamaz")
    private AdminTercihIslemleriEntity adminTercihIslemleri;
    
}
