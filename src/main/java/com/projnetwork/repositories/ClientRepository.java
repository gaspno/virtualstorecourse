package com.projnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projnetwork.entities.Client;
//configuração padraão de um repositório no spring
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
