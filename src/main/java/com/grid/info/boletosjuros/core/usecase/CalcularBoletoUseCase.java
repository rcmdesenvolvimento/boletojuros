package com.grid.info.boletosjuros.core.usecase;

import com.grid.info.boletosjuros.core.domain.Boleto;
import com.grid.info.boletosjuros.core.domain.BoletoCalculado;
import com.grid.info.boletosjuros.core.domain.enums.TipoBoleto;
import com.grid.info.boletosjuros.core.domain.enums.TipoExecao;
import com.grid.info.boletosjuros.core.exception.ApplicationException;
import com.grid.info.boletosjuros.core.ports.in.CalculoBoletoPort;
import com.grid.info.boletosjuros.core.ports.out.ComplementoBoletoPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CalcularBoletoUseCase implements CalculoBoletoPort {

    private final ComplementoBoletoPort complementoBoletoPort;

    public CalcularBoletoUseCase(ComplementoBoletoPort complementoBoletoPort) {
        this.complementoBoletoPort = complementoBoletoPort;
    }

    @Override
    public BoletoCalculado executar(String codigo, LocalDate dataPagamento) {
        var boleto = this.complementoBoletoPort.executar(codigo);

        // TODO -- validar boleto
        validar(boleto);

        // TODO -- calcular boleto

        
        // TODO -- salvar boleto


        return null;
    }

    private void validar(Boleto boleto) {

        if (boleto == null) {
            throw new ApplicationException(TipoExecao.BOLETO_INVALIDO);
        }

        if (boleto.getTipo() != TipoBoleto.XPTO) {
            throw new ApplicationException(TipoExecao.TIPO_BOLETO_INVALIDO);
        }

        if (boleto.getDataVencimento().isAfter(LocalDate.now())) {
            throw new ApplicationException(TipoExecao.BOLETO_NAO_VENCIDO);
        }
    }
}
