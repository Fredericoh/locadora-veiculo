package br.edu.faculdadedelta.locacaoveiculo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocacao;

    //@NotNull(message = "O campo valor total é obrigatorio. ")
    @Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorTotal;

    @NotNull(message = "O campo data de locação é obrigatorio. ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeLocacao;

    @NotNull(message = "O campo data de devolução é obrigatorio. ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeDevolucao;

    @ManyToOne
    @JoinColumn(name = "id_carro")
    @NotNull(message = "O campo carro é obrigatorio.")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "id_motorista")
    @NotNull(message = "O campo motorista é obrigatorio. ")
    private Motorista motorista;

    public BigDecimal calcularValorTotal(){

        BigDecimal calculaValorTotal;
        long diferancaDeDias = ChronoUnit.DAYS.between(dataDeLocacao, dataDeDevolucao);

        calculaValorTotal = carro.getValorDaDiaria ().multiply (new BigDecimal (diferancaDeDias));

        setValorTotal (calculaValorTotal);

        return calculaValorTotal;
    }

}
