package com.voyage.agence.Repository;

import org.springframework.stereotype.Repository;
import com.voyage.agence.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}