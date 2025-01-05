package com.voyage.agence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.voyage.agence.Entity.Voyage;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {

}
