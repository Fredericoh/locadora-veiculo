package br.edu.faculdadedelta.locacaoveiculo.repository;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
