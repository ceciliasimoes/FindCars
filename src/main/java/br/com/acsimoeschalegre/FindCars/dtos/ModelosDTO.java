package br.com.acsimoeschalegre.FindCars.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosDTO(
        List<DadosVeiculos> modelos
){
}