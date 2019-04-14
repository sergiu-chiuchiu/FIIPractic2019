package ro.fiipractic.mycinema.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.fiipractic.mycinema.dto.CinemaDto;
import ro.fiipractic.mycinema.entity.Cinema;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
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

    private Logger logger = LogManager.getLogger(this.getClass());


    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaService.getAll();
    }

    @PostMapping
    public ResponseEntity<Cinema> saveCinema(@RequestBody CinemaDto cinemaDto) throws URISyntaxException {
        Cinema cinema = cinemaService.saveCinema(modelMapper.map(cinemaDto, Cinema.class));
        ResponseEntity<Cinema> res = ResponseEntity.created(new URI("/api/cinemas/" + cinema.getId())).body(cinema);

        logger.info("The new record can be found at the following path: " + res.getHeaders().getLocation().toString());
        return res;
    }

    @GetMapping(value = "/capacity")
    public List<Cinema> getCinemaByMovieRoomCapacity(@RequestParam("capacity") Integer capacity) {
        return cinemaService.getCinemaByMovieRoomsCapacity(capacity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteCinema(@PathVariable("id") Long id) throws NotFoundException {
        Cinema cinema = cinemaService.getCinemaById(id);
        logger.info("The cinema to be deleted has the name: " + cinema.getName() + " and the id " + id);
        cinemaService.deleteCinema(cinema);
    }

}
