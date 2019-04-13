package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entity.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.Collection;

public interface MovieInstanceService {


    Collection<MovieInstance> getAllMovieInstances();

    MovieInstance getMovieInstanceById(Long id) throws NotFoundException;

    MovieInstance saveMovieInstance(MovieInstance movieInstance);

    void deleteMovieInstance(MovieInstance movieInstance);
}
