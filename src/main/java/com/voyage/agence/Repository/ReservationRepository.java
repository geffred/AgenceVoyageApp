package com.voyage.agence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.voyage.agence.Entity.Reservation;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByClientIdAndVoyageId(Long clientId, Long voyageId);

    List<Reservation> findByPaye(Boolean paye);

    List<Reservation> findByClientNomContaining(String nom);

    List<Reservation> findByPayeAndClientNomContaining(Boolean paye, String nom);

}
