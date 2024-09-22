package br.com.acsimoeschalegre.FindCars.repository;

import br.com.acsimoeschalegre.FindCars.dtos.VeiculoDTO;
import br.com.acsimoeschalegre.FindCars.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IVeiculoRepositoy extends JpaRepository<Veiculo, UUID> {
    List<VeiculoDTO> findByMarcaContainingIgnoreCase(String marca);
    @Query("SELECT v FROM Veiculo v WHERE v.ano = :anoBuscado AND  v.modelo = :modelo")
    List<VeiculoDTO> veiculosPorAnoEModelo(int anoBuscado, String modelo);
    List<VeiculoDTO> findByAno(int ano);
}
