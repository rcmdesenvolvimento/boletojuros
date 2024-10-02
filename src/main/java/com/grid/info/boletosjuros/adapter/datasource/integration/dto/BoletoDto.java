package com.grid.info.boletosjuros.adapter.datasource.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grid.info.boletosjuros.core.domain.enums.TipoBoleto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BoletoDto {

    private String codigo;

    @JsonProperty("data_vencimento")
    private LocalDate dataVencimento;

    private BigDecimal valor;
    private TipoBoleto tipo;
}
