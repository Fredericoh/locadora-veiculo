package br.edu.faculdadedelta.locacaoveiculo.modelo.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Sexo {

    MASCULINO("Masculino"), FEMININO("Feminino");

    @Getter
    private String descricaoSexo;
}
