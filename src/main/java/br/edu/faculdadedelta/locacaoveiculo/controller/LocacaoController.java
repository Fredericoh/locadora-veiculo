package br.edu.faculdadedelta.locacaoveiculo.controller;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Carro;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Locacao;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Motorista;
import br.edu.faculdadedelta.locacaoveiculo.repository.CarroRepository;
import br.edu.faculdadedelta.locacaoveiculo.repository.LocacaoRepository;
import br.edu.faculdadedelta.locacaoveiculo.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    private static final String LOCACAO_CADASTRO = "locacaoCadastro";
    private static final String LOCACAO_LISTA =  "locacaoLista";

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private CarroRepository carroRepository;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_CADASTRO);
        modelAndView.addObject (new Locacao ());
        return modelAndView;
    }

    @ModelAttribute(name = "todosMotoristas")
    public List<Motorista> todosMotoristas(){

        return motoristaRepository.findAll ();
    }

    @ModelAttribute(name = "todosCarros")
    public List<Carro> todosCarros(){

        return carroRepository.findAll ();
    }

    @PostMapping
    public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (LOCACAO_CADASTRO);
        }

        if (locacao.getIdLocacao () == null){
            locacaoRepository.save (locacao);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            locacaoRepository.save (locacao);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/locacoes/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_LISTA);

        modelAndView.addObject ("locacoes", locacaoRepository.findAll ());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_CADASTRO);

        modelAndView.addObject (locacaoRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");

        locacaoRepository.deleteById (id);

        return modelAndView;
    }
}
