package br.edu.faculdadedelta.locacaoveiculo.modelo;

import br.edu.faculdadedelta.locacaoveiculo.modelo.type.Categoria;
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
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModelo;

    @NotBlank(message = "O campo descrição do modelo é obrigatorio. ")
    private String descricaoModelo;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    @NotNull(message = "O campo fabricante é obrigatorio. ")
    private Fabricante fabricante;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo categoria é obrigatorio. ")
    private Categoria categoria;

}
