package br.edu.faculdadedelta.locacaoveiculo.service;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Locacao;
import br.edu.faculdadedelta.locacaoveiculo.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    public Locacao incluir(Locacao locacao){

        locacao.setIdLocacao (null);

        return locacaoRepository.save (locacao);
    }

    public Locacao alterar(Locacao locacao){

        pesquisarPorId (locacao.getIdLocacao ());

        return locacaoRepository.save (locacao);
    }

    public void excluir(Long id){

        locacaoRepository.deleteById (id);
    }

    public List<Locacao> listar(){

        return locacaoRepository.findAll ();
    }

    public Locacao pesquisarPorId(Long id){

        return locacaoRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0));
    }
}
