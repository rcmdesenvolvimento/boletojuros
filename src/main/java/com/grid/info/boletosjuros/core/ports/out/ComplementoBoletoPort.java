package com.grid.info.boletosjuros.core.ports.out;

import com.grid.info.boletosjuros.core.domain.Boleto;
import com.grid.info.boletosjuros.core.domain.BoletoCalculado;

import java.time.LocalDate;

public interface ComplementoBoletoPort {
    Boleto executar(String codigo);
}
