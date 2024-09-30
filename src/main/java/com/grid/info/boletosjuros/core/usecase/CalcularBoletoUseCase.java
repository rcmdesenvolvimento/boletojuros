package com.grid.info.boletosjuros.core.usecase;

import com.grid.info.boletosjuros.core.domain.Boleto;
import com.grid.info.boletosjuros.core.domain.BoletoCalculado;
import com.grid.info.boletosjuros.core.domain.enums.TipoBoleto;
import com.grid.info.boletosjuros.core.domain.enums.TipoExecao;
import com.grid.info.boletosjuros.core.exception.ApplicationException;
import com.grid.info.boletosjuros.core.ports.in.CalculoBoletoPort;
import com.grid.info.boletosjuros.core.ports.out.ComplementoBoletoPort;
import com.grid.info.boletosjuros.core.ports.out.SalvarCalculoBoletoPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalcularBoletoUseCase implements CalculoBoletoPort {

    private static final BigDecimal JUROS_DIARIO = BigDecimal.valueOf(0.033);

    private final ComplementoBoletoPort complementoBoletoPort;
    private final SalvarCalculoBoletoPort salvarCalculoBoletoPort;

    public CalcularBoletoUseCase(ComplementoBoletoPort complementoBoletoPort, SalvarCalculoBoletoPort salvarCalculoBoletoPort) {
        this.complementoBoletoPort = complementoBoletoPort;
        this.salvarCalculoBoletoPort = salvarCalculoBoletoPort;
    }

    public CalcularBoletoUseCase(ComplementoBoletoPort complementoBoletoPort) {
        this.complementoBoletoPort = complementoBoletoPort;
    }

    @Override
    public BoletoCalculado executar(String codigo, LocalDate dataPagamento) {
        var boleto = this.complementoBoletoPort.executar(codigo);

        // TODO -- validar boleto
        validar(boleto);

        // TODO -- calcular juros boleto
        var diasVencidos = getDiasVencidos(boleto.getDataVencimento(), dataPagamento);
        var valorJurosDia = this.JUROS_DIARIO.multiply(boleto.getValor()).divide(BigDecimal.valueOf(100));
        var juros = valorJurosDia.multiply(BigDecimal.valueOf(diasVencidos)).setScale(2, RoundingMode.HALF_EVEN);
        var boletoCalculado = BoletoCalculado.builder()
                .codigo(boleto.getCodigo())
                .dataPagamento(dataPagamento)
                .juros(juros)
                .dataVencimento(boleto.getDataVencimento())
                .valorOriginal(boleto.getValor())
                .valor(boleto.getValor().add(juros))
                .tipo(boleto.getTipo())
                .build();

        // TODO -- salvar boleto na base
        this.salvarCalculoBoletoPort.executar(boletoCalculado);

        return boletoCalculado;
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

    private Long getDiasVencidos(LocalDate dataVencimento, LocalDate dataPagamento) {
        return ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
    }
}
