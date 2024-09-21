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
    private String marca;
    private String modelo;
    private Integer ano;
    private String combustivel;

    public Veiculo(VeiculoDTO dto){
        this.marca = dto.marca();
        this.modelo = dto.modelo();
        this.ano = dto.ano();
        this.combustivel = dto.combustivel();
    }
}



