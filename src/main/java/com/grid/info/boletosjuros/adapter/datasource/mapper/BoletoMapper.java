package com.grid.info.boletosjuros.adapter.datasource.mapper;

import com.grid.info.boletosjuros.adapter.datasource.integration.dto.BoletoDto;
import com.grid.info.boletosjuros.core.domain.Boleto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoletoMapper {

    Boleto toDomain(BoletoDto boletoDto);

}
