package br.edu.faculdadedelta.locacaoveiculo.controller;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Fabricante;
import br.edu.faculdadedelta.locacaoveiculo.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteController {

    private static final String FABRICANTE_CADASTRO = "fabricanteCadastro";
    private static final String FABRICANTE_LISTA =  "fabricanteLista";

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (FABRICANTE_CADASTRO);
        modelAndView.addObject (new Fabricante ());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Fabricante fabricante, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (FABRICANTE_CADASTRO);
        }

        if (fabricante.getIdFabricante () == null){
            fabricanteRepository.save (fabricante);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            fabricanteRepository.save (fabricante);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/fabricantes/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (FABRICANTE_LISTA);

        modelAndView.addObject ("fabricantes", fabricanteRepository.findAll ());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (FABRICANTE_CADASTRO);

        modelAndView.addObject (fabricanteRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/fabricantes");

        fabricanteRepository.deleteById (id);

        return modelAndView;
    }
}
