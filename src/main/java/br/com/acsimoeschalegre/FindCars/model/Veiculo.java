package br.com.acsimoeschalegre.FindCars.model;

import br.com.acsimoeschalegre.FindCars.dtos.VeiculoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String valor;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;

    public Veiculo(VeiculoDTO dto){
        this.valor = dto.valor();
        this.marca = dto.marca();
        this.modelo = dto.modelo();
        this.ano = dto.ano();
        this.combustivel = dto.combustivel();
    }
}



