package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entity.MovieRoom;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.MovieRoomService;

import java.util.List;

@Service
public class MovieRoomServiceImpl implements MovieRoomService {

    @Autowired
    private MovieRoomRepository movieRoomRepository;

    @Override
    public List<MovieRoom> getAll() {
       return movieRoomRepository.findAll();
    }

    @Override
    public MovieRoom saveMovieRoom(MovieRoom movieRoom) {
        return movieRoomRepository.save(movieRoom);
    }

    @Override
    public List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId) {
        return movieRoomRepository.getMovieRoomByCinema_Id(cinemaId);
    }

    @Override
    public MovieRoom getMovieRoomById(Long id) throws NotFoundException {
        return movieRoomRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Movie Room with id=%s was not found.", id)));
    }

    @Override
    public void deleteMovieRoom(MovieRoom movieRoom) {
        movieRoomRepository.delete(movieRoom);
    }

}
