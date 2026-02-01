package com.smartenergy.smart_energy_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando o intervalo de datas é inválido.
 * Ex: Data de início posterior à data de fim.
 * * O @ResponseStatus garante que o Spring retorne erro 400 (Bad Request)
 * automaticamente se essa exceção for lançada.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException(String message) {
        super(message);
    }
    
}
