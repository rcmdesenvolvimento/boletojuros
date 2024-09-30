package com.grid.info.boletosjuros.core.ports.out;

import com.grid.info.boletosjuros.core.domain.BoletoCalculado;

public interface SalvarCalculoBoletoPort {
    void executar(BoletoCalculado boletoCalculado);
}
