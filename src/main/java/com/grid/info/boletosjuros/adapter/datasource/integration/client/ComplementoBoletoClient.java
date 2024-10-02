package com.grid.info.boletosjuros.adapter.datasource.integration.client;

import com.grid.info.boletosjuros.adapter.datasource.integration.dto.BoletoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "complemento", url = "${api.boleto}")
public interface ComplementoBoletoClient {

    @GetMapping("/{codigo}")
    BoletoDto getBoleto(@PathVariable(value = "codigo") String codigo);
}
