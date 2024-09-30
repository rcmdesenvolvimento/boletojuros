package com.grid.info.boletosjuros.core.domain.enums;

public enum TipoExecao {
    BOLETO_INVALIDO {
        @Override
        public String getMensagemError() {
            return "O boleto encontrado é inválido.";
        }
    },
    TIPO_BOLETO_INVALIDO {
        @Override
        public String getMensagemError() {
            return "Só poderão ser calculados os juros dos boletos XPTO.";
        }
    },
    BOLETO_NAO_VENCIDO {
        @Override
        public String getMensagemError() {
            return "O boleto infomado ainda não está vencido.";
        }
    };

    public abstract String getMensagemError();
}
