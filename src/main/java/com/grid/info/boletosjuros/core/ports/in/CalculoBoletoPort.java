package com.grid.info.boletosjuros.core.ports.in;

import com.grid.info.boletosjuros.core.domain.BoletoCalculado;

import java.time.LocalDate;

public interface CalculoBoletoPort {
    BoletoCalculado executar(String codigo, LocalDate dataPagamento);
}
