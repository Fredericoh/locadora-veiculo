package br.edu.faculdadedelta.locacaoveiculo.repository;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
