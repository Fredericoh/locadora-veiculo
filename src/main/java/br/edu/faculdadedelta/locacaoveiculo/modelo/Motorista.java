package br.edu.faculdadedelta.locacaoveiculo.modelo;

import br.edu.faculdadedelta.locacaoveiculo.modelo.type.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMotorista;

    @NotBlank(message = "O campo nome é obrigatorio. ")
    private String nome;

    @NotBlank(message = "O campo cpf é obrigatorio. ")
    private String cpf;

    @NotBlank(message = "O campo cnh é obrigatorio. ")
    private String cnh;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo sexo é obrigatorio. ")
    private Sexo sexo;

}
