package com.voyage.agence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.voyage.agence.Entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {

}
