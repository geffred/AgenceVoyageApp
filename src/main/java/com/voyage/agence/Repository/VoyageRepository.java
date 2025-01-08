package com.voyage.agence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.voyage.agence.Entity.Voyage;
import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByDestinationContaining(String destination);

    List<Voyage> findByPrix(double prix);
}
