package br.edu.faculdadedelta.locacaoveiculo.controller;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Carro;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Modelo;
import br.edu.faculdadedelta.locacaoveiculo.repository.CarroRepository;
import br.edu.faculdadedelta.locacaoveiculo.repository.ModeloRepository;
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
@RequestMapping("/carros")
public class CarroController {

    private static final String CARROS_CADASTRO = "carroCadastro";
    private static final String CARROS_LISTA =  "carroLista";

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (CARROS_CADASTRO);
        modelAndView.addObject (new Carro ());
        return modelAndView;
    }

    @ModelAttribute(name = "todosModelos")
    public List<Modelo> todosModelos(){

        return modeloRepository.findAll ();
    }

    @PostMapping
    public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (CARROS_CADASTRO);
        }

        if (carro.getIdCarro () == null){
            carroRepository.save (carro);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            carroRepository.save (carro);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/carros/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (CARROS_LISTA);

        modelAndView.addObject ("carros", carroRepository.findAll ());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (CARROS_CADASTRO);

        modelAndView.addObject (carroRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/carros");

        carroRepository.deleteById (id);

        return modelAndView;
    }
}
