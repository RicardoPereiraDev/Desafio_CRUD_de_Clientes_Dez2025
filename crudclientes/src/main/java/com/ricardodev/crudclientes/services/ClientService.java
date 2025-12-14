package com.ricardodev.crudclientes.services;

import com.ricardodev.crudclientes.dto.ClientDTO;
import com.ricardodev.crudclientes.entities.Client;
import com.ricardodev.crudclientes.repositories.ClientRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //devolve DTO
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);

    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> resultClients = clientRepository.findAll(pageable);
        //Vou ter que converter a minha lista de Clientes para uma lista de ClientDTO e para isso uso o lambda
        ////Para cada registo da minha lista original eu vou chamar o meu new ClientDTO recebendo x
        return resultClients.map(x->new ClientDTO(x));
        }


    }

