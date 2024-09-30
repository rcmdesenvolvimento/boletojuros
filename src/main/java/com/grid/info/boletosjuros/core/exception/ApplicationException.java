package com.grid.info.boletosjuros.core.exception;

import com.grid.info.boletosjuros.core.domain.enums.TipoExecao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private TipoExecao tipoExecao;
}
