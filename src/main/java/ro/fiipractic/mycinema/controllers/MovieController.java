package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dto.MovieDto;
import ro.fiipractic.mycinema.entity.Movie;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Collection<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovieById(@PathVariable("id") Long id) throws NotFoundException {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieDto movieToSave) throws URISyntaxException {
        Movie movie = movieService.saveMovie(modelMapper.map(movieToSave, Movie.class));
        return ResponseEntity.created(new URI("/api/movies/" + movie.getId())).body(movie);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity updateMovie(@PathVariable Long id, @RequestBody MovieDto movieToUpdate) throws BadRequestException, NotFoundException, URISyntaxException {
        if (!id.equals(movieToUpdate.getId()))
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + movieToUpdate.getId() + " from RequestBody");

        Movie movieDb = movieService.getMovieById(id);
        modelMapper.map(movieToUpdate, movieDb);
        movieService.saveMovie(movieDb);
        return ResponseEntity.ok(new URI("/api/movies/" + movieDb.getId()));
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) throws NotFoundException {
        Movie movie = movieService.getMovieById(id);
        movieService.deleteMovie(movie);
    }
}
