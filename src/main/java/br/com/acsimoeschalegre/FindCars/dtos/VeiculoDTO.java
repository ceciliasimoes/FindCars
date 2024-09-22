package br.com.acsimoeschalegre.FindCars.dtos;

import br.com.acsimoeschalegre.FindCars.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoDTO(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") int ano,
        @JsonAlias("Combustivel") String combustivel
) {
    public VeiculoDTO(Veiculo v){
        this(v.getValor(),v.getMarca(),v.getModelo(), v.getAno(), v.getCombustivel());
    }
}
