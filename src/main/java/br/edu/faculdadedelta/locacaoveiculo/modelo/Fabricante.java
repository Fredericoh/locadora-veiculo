package br.edu.faculdadedelta.locacaoveiculo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFabricante;

    @NotBlank(message = "O campo nome é obrigatorio. ")
    private String nome;

    @NotBlank(message = "O campo descrição do fabricante é obrigatorio. ")
    private String descricaoFabricante;

}
