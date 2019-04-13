package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entity.Reservation;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.Collection;

public interface ReservationService {


    Collection<Reservation> getAllReservations();

    Reservation getReservationById(Long id) throws NotFoundException;

    Reservation saveReservation(Reservation reservation);

    void deleteReservation(Reservation reservation);
}
