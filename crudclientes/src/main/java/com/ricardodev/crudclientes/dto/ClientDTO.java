package com.ricardodev.crudclientes.dto;

import com.ricardodev.crudclientes.entities.Client;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {


    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @Column(unique = true)
    private String cpf;
    private Double income;
    @PastOrPresent(message = "a data de nascimento não pode ser data futura")
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO(){
        
    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client){
        id= client.getId();
        name= client.getName();
        cpf= client.getCpf();
        income=client.getIncome();
        birthDate=client.getBirthDate();
        children=client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public Integer getChildren() {
        return children;
    }

}
