package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dto.MovieInstanceDto;
import ro.fiipractic.mycinema.entity.MovieInstance;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieInstanceService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/movieInstances")
public class MovieInstanceController {

    private final ModelMapper modelMapper;
    private final MovieInstanceService movieInstanceService;

    @Autowired
    public MovieInstanceController(ModelMapper modelMapper, MovieInstanceService movieInstanceService) {
        this.modelMapper = modelMapper;
        this.movieInstanceService = movieInstanceService;
    }


    @GetMapping
    public Collection<MovieInstance> getAllMovieInstances() {
        return movieInstanceService.getAllMovieInstances();
    }

    @GetMapping(value = "/{id}")
    public MovieInstance getMovieInstanceById(@PathVariable("id") Long id) throws NotFoundException {
        return movieInstanceService.getMovieInstanceById(id);
    }

    @PostMapping
    public ResponseEntity<MovieInstance> saveReservation(@RequestBody MovieInstanceDto movieInstanceToSave) throws URISyntaxException {
        MovieInstance movieInstance = movieInstanceService.saveMovieInstance(modelMapper.map(movieInstanceToSave, MovieInstance.class));
        return ResponseEntity.created(new URI("/api/MovieInstances/" + movieInstance.getId())).body(movieInstance);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity updateMovieInstance(@PathVariable Long id, @RequestBody MovieInstanceDto movieInstanceToUpdate) throws BadRequestException, NotFoundException, URISyntaxException {
        if (!id.equals(movieInstanceToUpdate.getId()))
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + movieInstanceToUpdate.getId() + " from RequestBody");

        MovieInstance movieInstanceDb = movieInstanceService.getMovieInstanceById(id);
        modelMapper.map(movieInstanceToUpdate, movieInstanceDb);
        movieInstanceService.saveMovieInstance(movieInstanceDb);
        return ResponseEntity.ok(new URI("/api/movieInstances/" + movieInstanceDb.getId()));
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieInstance(@PathVariable Long id) throws NotFoundException {
        MovieInstance movieInstance = movieInstanceService.getMovieInstanceById(id);
        movieInstanceService.deleteMovieInstance(movieInstance);
    }


}
