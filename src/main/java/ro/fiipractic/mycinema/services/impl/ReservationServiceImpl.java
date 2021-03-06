package ro.fiipractic.mycinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entity.Reservation;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.ReservationRepository;
import ro.fiipractic.mycinema.services.ReservationService;

import java.util.Collection;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) throws NotFoundException {
        logger.info("Retrieving the Reservation with id=" + id);
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Reservation with id=%s was not found.", id)));
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservation.removeReservation();
        if (reservation.getMovieInstance() != null || reservation.getPerson() != null) {
            logger.error("Could not remove Reservation Child from one of its parents. The instance will not be deleted");
        }
        reservationRepository.delete(reservation);
    }
}
