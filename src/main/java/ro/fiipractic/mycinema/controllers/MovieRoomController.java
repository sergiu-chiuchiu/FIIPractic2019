package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dto.MovieRoomDto;
import ro.fiipractic.mycinema.entity.MovieRoom;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieRoomService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "api/movie-rooms")
public class MovieRoomController {

    private final MovieRoomService movieRoomService;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieRoomController(MovieRoomService movieRoomService, ModelMapper modelMapper) {
        this.movieRoomService = movieRoomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MovieRoom> getAllMovieRooms() {
        return movieRoomService.getAll();
    }

    @PostMapping
    public ResponseEntity<MovieRoom> saveMovieRoomResponseEntity(@RequestBody MovieRoomDto movieRoomDto) throws URISyntaxException {
        MovieRoom movieRoom = movieRoomService.saveMovieRoom(modelMapper.map(movieRoomDto, MovieRoom.class));
        return ResponseEntity.created(new URI("api/movie-rooms/" + movieRoom.getId())).body(movieRoom);
    }

    @GetMapping(value = "/filter/{cinemaId}")
    public List<MovieRoom> getAlMovieRoomsByCinemaid(@PathVariable("cinemaId") Long cinemaId) {
        return movieRoomService.getAllMovieRoomsByCinemaId(cinemaId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public void deleteMovieRoom(@PathVariable("id") Long id) throws NotFoundException {
        MovieRoom movieRoom = movieRoomService.getMovieRoomById(id);
        movieRoomService.deleteMovieRoom(movieRoom);
    }
}
