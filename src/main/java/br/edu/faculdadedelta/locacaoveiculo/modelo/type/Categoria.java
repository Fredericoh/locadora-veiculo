package br.edu.faculdadedelta.locacaoveiculo.modelo.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Categoria {

    HATCH("Hatch"), SEDAN("Sedan"), UTILITARIO("Utilitário"), ESPORTIVO("Esportivo");

    @Getter
    private String descricaoCategoria;

}
