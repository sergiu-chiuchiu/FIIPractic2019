package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entity.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieInstanceRepository;
import ro.fiipractic.mycinema.services.MovieInstanceService;

import java.util.Collection;

@Service
public class MovieInstanceServiceImpl implements MovieInstanceService {

    public final MovieInstanceRepository movieInstanceRepository;

    @Autowired
    public MovieInstanceServiceImpl(MovieInstanceRepository movieInstanceRepository) {
        this.movieInstanceRepository = movieInstanceRepository;
    }

    @Override
    public Collection<MovieInstance> getAllMovieInstances() {
        return movieInstanceRepository.findAll();
    }

    @Override
    public MovieInstance getMovieInstanceById(Long id) throws NotFoundException {
        return movieInstanceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("MovieInstance with id=%s was not found.", id)));
    }

    @Override
    public MovieInstance saveMovieInstance(MovieInstance movieInstance) {
        return movieInstanceRepository.save(movieInstance);
    }

    @Override
    public void deleteMovieInstance(MovieInstance movieInstance) {
        movieInstance.removeMovieInstance();
        movieInstanceRepository.delete(movieInstance);
    }
}
