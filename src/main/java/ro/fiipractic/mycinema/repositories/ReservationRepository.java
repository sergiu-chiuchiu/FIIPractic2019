package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fiipractic.mycinema.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
