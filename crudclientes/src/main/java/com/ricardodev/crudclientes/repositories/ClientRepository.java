package com.ricardodev.crudclientes.repositories;

import com.ricardodev.crudclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
