package com.grid.info.boletosjuros.core.domain;

import com.grid.info.boletosjuros.core.domain.enums.TipoBoleto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BoletoCalculado {

    private String codigo;
    private BigDecimal valorOriginal;
    private BigDecimal valor;
    private BigDecimal juros;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private TipoBoleto tipo;
}
