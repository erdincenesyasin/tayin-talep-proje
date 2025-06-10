package com.eparlak.personeltayintalebi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TercihEntity extends BaseEntity{
  

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @NotNull
    private TayinTalebiEntity tayinTalebi;

    @ManyToOne
    @NotNull
    private AdliyeEntity adliye;

    @Column(nullable = false)
    @Min(value = 1, message = "Sira numarası en az 1 olmalıdır")
    private Integer sira; // 1, 2, 3...
}