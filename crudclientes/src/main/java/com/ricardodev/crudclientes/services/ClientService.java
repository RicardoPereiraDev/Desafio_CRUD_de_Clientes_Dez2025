package com.ricardodev.crudclientes.services;

import com.ricardodev.crudclientes.dto.ClientDTO;
import com.ricardodev.crudclientes.entities.Client;
import com.ricardodev.crudclientes.repositories.ClientRepository;
import com.ricardodev.crudclientes.services.exceptions.DatabaseException;
import com.ricardodev.crudclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

        @Transactional
        public ClientDTO insert(ClientDTO clientDTO){

        Client client = new Client();
        copyDtoToEntity(clientDTO, client);

        client= clientRepository.save(client);
        return new  ClientDTO(client);
        }

        @Transactional
        public ClientDTO update(Long id, ClientDTO clientDTO){

        try{
            //Instanciar o Client comm a referencia do id que eu passar no argumento
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(clientDTO,client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
            }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Id não existente");
        }
        try{
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyDtoToEntity(ClientDTO clientDTO, Client client){
    client.setName(clientDTO.getName());
    client.setCpf(clientDTO.getCpf());
    client.setIncome(clientDTO.getIncome());
    client.setBirthDate(clientDTO.getBirthDate());
    client.setChildren(clientDTO.getChildren());

    }
}

