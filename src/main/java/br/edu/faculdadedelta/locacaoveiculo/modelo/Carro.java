package br.edu.faculdadedelta.locacaoveiculo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;

    @NotBlank(message = "O campo placa é obrigatorio. ")
    private String placa;

    @NotBlank(message = "O campo chassi é obrigatorio. ")
    private String chassi;

    @NotNull(message = "O campo valor da diaria é obrigatorio. ")
    @Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorDaDiaria;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    @NotNull(message = "O campo modelo é obrigatorio. ")
    private Modelo modelo;

}
