package br.edu.faculdadedelta.locacaoveiculo.controller;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Fabricante;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Modelo;
import br.edu.faculdadedelta.locacaoveiculo.modelo.type.Categoria;
import br.edu.faculdadedelta.locacaoveiculo.repository.FabricanteRepository;
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
@RequestMapping("/modelos")
public class ModeloController {

    private static final String MODELO_CADASTRO = "modeloCadastro";
    private static final String MODELO_LISTA =  "modeloLista";

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (MODELO_CADASTRO);
        modelAndView.addObject (new Modelo ());
        return modelAndView;
    }

    @ModelAttribute(name = "todosFabricantes")
    public List<Fabricante> todosFabricantes(){

        return fabricanteRepository.findAll ();
    }

    @ModelAttribute(name = "todasCategorias")
    public Categoria[] todasCategorias(){

        return Categoria.values ();
    }

    @PostMapping
    public ModelAndView salvar(@Valid Modelo modelo, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (MODELO_CADASTRO);
        }

        if (modelo.getIdModelo () == null){
            modeloRepository.save (modelo);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            modeloRepository.save (modelo);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/modelos/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (MODELO_LISTA);

        modelAndView.addObject ("modelos", modeloRepository.findAll ());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (MODELO_CADASTRO);

        modelAndView.addObject (modeloRepository.findById (id)
                .orElseThrow (() -> new EmptyResultDataAccessException (0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/modelos");

        modeloRepository.deleteById (id);

        return modelAndView;
    }
}
