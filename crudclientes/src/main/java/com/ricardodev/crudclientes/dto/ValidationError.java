package com.ricardodev.crudclientes.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//Esta classe serve para ter uma lista de FieldMessage com todos os nomes dos campos e mensagens de cada erro e de cada campo
public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
        this.errors = errors;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    //Aqui em baixo será um metodo para adicionar mensagens a essa lista
    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName,message));

    }
}
