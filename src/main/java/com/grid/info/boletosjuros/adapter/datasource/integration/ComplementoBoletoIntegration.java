package com.grid.info.boletosjuros.adapter.datasource.integration;

import com.grid.info.boletosjuros.adapter.datasource.integration.client.ComplementoBoletoClient;
import com.grid.info.boletosjuros.adapter.datasource.mapper.BoletoMapper;
import com.grid.info.boletosjuros.core.domain.Boleto;
import com.grid.info.boletosjuros.core.ports.out.ComplementoBoletoPort;

public class ComplementoBoletoIntegration implements ComplementoBoletoPort {

    private final ComplementoBoletoClient client;
    private final BoletoMapper mapper;

    public ComplementoBoletoIntegration(ComplementoBoletoClient client, BoletoMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public Boleto executar(String codigo) {
        var boletoDto = this.client.getBoleto(codigo);
        return mapper.toDomain(boletoDto);
    }
}
