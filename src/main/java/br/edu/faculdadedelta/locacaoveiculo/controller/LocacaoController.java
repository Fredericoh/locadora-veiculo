package br.edu.faculdadedelta.locacaoveiculo.controller;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Carro;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Locacao;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Motorista;
import br.edu.faculdadedelta.locacaoveiculo.service.CarroService;
import br.edu.faculdadedelta.locacaoveiculo.service.LocacaoService;
import br.edu.faculdadedelta.locacaoveiculo.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    private static final String LOCACAO_CADASTRO = "locacaoCadastro";
    private static final String LOCACAO_LISTA =  "locacaoLista";

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private CarroService carroService;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_CADASTRO);
        modelAndView.addObject (new Locacao ());
        return modelAndView;
    }

    @ModelAttribute(name = "todosMotoristas")
    public List<Motorista> todosMotoristas(){

        return motoristaService.listar ();
    }

    @ModelAttribute(name = "todosCarros")
    public List<Carro> todosCarros(){

        return carroService.listar ();
    }

    @PostMapping
    public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (LOCACAO_CADASTRO);
        }

        LocalDate dataAtual = LocalDate.now();

        if (locacao.getDataDeLocacao ().isBefore (dataAtual)){

            redirectAttributes.addFlashAttribute ("mensagem", "A data de locação deve ser maior que a data atual.");

        }else {

            if (locacao.getIdLocacao () == null){
                locacao.calcularValorTotal ();
                locacaoService.incluir (locacao);

                redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
            } else {
                locacao.calcularValorTotal ();
                locacaoService.alterar (locacao);
                redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
            }

        }



        return new ModelAndView ("redirect:/locacoes/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_LISTA);

        modelAndView.addObject ("locacoes", locacaoService.listar ());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_CADASTRO);

        modelAndView.addObject (locacaoService.pesquisarPorId (id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");

        locacaoService.excluir (id);

        return modelAndView;
    }
}
