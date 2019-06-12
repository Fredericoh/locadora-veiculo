package br.edu.faculdadedelta.locacaoveiculo.repository;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
