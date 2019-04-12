package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entity.Cinema;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAll();

    Cinema saveCinema(Cinema cinema);

    List<Cinema> getCinemaByMovieRoomsCapacity(Integer capacity);

    Cinema getCinemaById(Long id) throws NotFoundException;
}
