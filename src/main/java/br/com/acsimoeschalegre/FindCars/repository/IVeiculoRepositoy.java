package br.com.acsimoeschalegre.FindCars.repository;

import br.com.acsimoeschalegre.FindCars.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IVeiculoRepositoy extends JpaRepository<Veiculo, UUID> {
}
