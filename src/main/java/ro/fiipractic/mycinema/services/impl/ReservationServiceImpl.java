package ro.fiipractic.mycinema.services.impl;

import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entity.Reservation;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.ReservationRepository;
import ro.fiipractic.mycinema.services.ReservationService;

import java.util.Collection;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) throws NotFoundException {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Reservation with id=%s was not found.", id)));
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservation.removeReservation();
        reservationRepository.delete(reservation);
    }
}
