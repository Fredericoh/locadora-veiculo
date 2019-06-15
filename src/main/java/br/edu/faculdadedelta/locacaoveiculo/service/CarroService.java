package br.edu.faculdadedelta.locacaoveiculo.service;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Carro;
import br.edu.faculdadedelta.locacaoveiculo.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Carro incluir(Carro carro){

        carro.setIdCarro (null);

        return carroRepository.save (carro);
    }

    public Carro alterar(Carro carro){

        pesquisarPorId (carro.getIdCarro ());

        return carroRepository.save (carro);
    }

    public void excluir(Long id){

        carroRepository.deleteById (id);
    }

    public List<Carro> listar(){

        return carroRepository.findAll ();
    }

    public Carro pesquisarPorId(Long id){

        return carroRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0));
    }
}
