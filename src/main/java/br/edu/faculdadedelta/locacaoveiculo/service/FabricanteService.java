package br.edu.faculdadedelta.locacaoveiculo.service;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Fabricante;
import br.edu.faculdadedelta.locacaoveiculo.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    public Fabricante incluir(Fabricante fabricante){

        fabricante.setIdFabricante (null);

        return fabricanteRepository.save (fabricante);
    }

    public Fabricante alterar(Fabricante fabricante){

        pesquisarPorId (fabricante.getIdFabricante ());

        return fabricanteRepository.save (fabricante);
    }

    public void excluir(Long id){

        fabricanteRepository.deleteById (id);
    }

    public List<Fabricante> listar(){

        return fabricanteRepository.findAll ();
    }

    public Fabricante pesquisarPorId(Long id){

        return fabricanteRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0));
    }
}
