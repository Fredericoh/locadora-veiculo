package br.edu.faculdadedelta.locacaoveiculo.service;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Motorista;
import br.edu.faculdadedelta.locacaoveiculo.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public Motorista incluir(Motorista motorista){

        motorista.setIdMotorista (null);

        return motoristaRepository.save (motorista);
    }

    public Motorista alterar(Motorista motorista){

        pesquisarPorId (motorista.getIdMotorista ());

        return motoristaRepository.save (motorista);
    }

    public void excluir(Long id){

        motoristaRepository.deleteById (id);
    }

    public List<Motorista> listar(){

        return motoristaRepository.findAll ();
    }

    public Motorista pesquisarPorId(Long id){

        return motoristaRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0));
    }
}
