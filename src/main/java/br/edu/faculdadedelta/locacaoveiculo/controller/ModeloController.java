package br.edu.faculdadedelta.locacaoveiculo.controller;

import br.edu.faculdadedelta.locacaoveiculo.modelo.Fabricante;
import br.edu.faculdadedelta.locacaoveiculo.modelo.Modelo;
import br.edu.faculdadedelta.locacaoveiculo.modelo.type.Categoria;
import br.edu.faculdadedelta.locacaoveiculo.service.FabricanteService;
import br.edu.faculdadedelta.locacaoveiculo.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ModeloService modeloService;

    @Autowired
    private FabricanteService fabricanteService;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (MODELO_CADASTRO);
        modelAndView.addObject (new Modelo ());
        return modelAndView;
    }

    @ModelAttribute(name = "todosFabricantes")
    public List<Fabricante> todosFabricantes(){

        return fabricanteService.listar ();
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
            modeloService.incluir (modelo);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            modeloService.alterar (modelo);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/modelos/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (MODELO_LISTA);

        modelAndView.addObject ("modelos", modeloService.listar ());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (MODELO_CADASTRO);

        modelAndView.addObject (modeloService.pesquisarPorId (id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/modelos");

        modeloService.excluir (id);

        return modelAndView;
    }
}
