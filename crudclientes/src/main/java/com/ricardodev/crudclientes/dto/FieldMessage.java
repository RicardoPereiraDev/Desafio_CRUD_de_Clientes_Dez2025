package com.ricardodev.crudclientes.dto;

//Esta classe serve para representar mensagens como por exemplo o nome do campo "name" e a mensagem na frente dele no caso de tratamento personalizado das respostas da validação;
public class FieldMessage {

    private  String name;
    private String message;

    public FieldMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
