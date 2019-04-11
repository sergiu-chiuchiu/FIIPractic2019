package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dto.CinemaDto;
import ro.fiipractic.mycinema.entity.Cinema;
import ro.fiipractic.mycinema.services.CinemaService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cinemas")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaService.getAll();
    }

    @PostMapping
    public ResponseEntity<Cinema> saveCinema(@RequestBody CinemaDto cinemaDto) throws URISyntaxException {
        Cinema cinema = cinemaService.saveCinema(modelMapper.map(cinemaDto, Cinema.class));
        return ResponseEntity.created(new URI("/api/cinemas/" + cinema.getId())).body(cinema);
    }

    @GetMapping(value = "/capacity")
    public List<Cinema> getCinemaByMovieRoomCapacity(@RequestParam("capacity") Integer capacity) {
        return cinemaService.getCinemaByMovieRoomsCapacity(capacity);
    }
}
