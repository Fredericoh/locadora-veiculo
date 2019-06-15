package br.edu.faculdadedelta.locacaoveiculo.service;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Modelo;
import br.edu.faculdadedelta.locacaoveiculo.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public Modelo incluir(Modelo modelo){

        modelo.setIdModelo (null);

        return modeloRepository.save (modelo);
    }

    public Modelo alterar(Modelo modelo){

        pesquisarPorId (modelo.getIdModelo ());

        return modeloRepository.save (modelo);
    }

    public void excluir(Long id){

        modeloRepository.deleteById (id);
    }

    public List<Modelo> listar(){

        return modeloRepository.findAll ();
    }

    public Modelo pesquisarPorId(Long id){

        return modeloRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0));
    }
}
