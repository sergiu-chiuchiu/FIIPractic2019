package ro.fiipractic.mycinema.controllers;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.fiipractic.mycinema.dto.CinemaDto;
import ro.fiipractic.mycinema.entity.Cinema;
import ro.fiipractic.mycinema.services.impl.CinemaServiceImpl;

import java.net.URISyntaxException;

public class CinemaControllerTest {


    //mocking:
    @Mock
    private CinemaServiceImpl cinemaService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    CinemaController cinemaController;

    CinemaDto cinemaDto = new CinemaDto();
    Cinema cinema = new Cinema();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // numele la teste trb sa fie ff descriptiv!!!
    //good practice la numirea testelor e sa spui ce ar trebui sa faca/returneze
    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        createObjects(cinema, cinemaDto);

        // arrange
        Mockito.when(cinemaService.saveCinema(cinema)).thenReturn(cinema);
        Mockito.when(modelMapper.map(cinemaDto, Cinema.class)).thenReturn(cinema);

        // act
        // apelam metoda ce vreau sa o testez
        ResponseEntity<Cinema> cinemaResponseEntity = cinemaController.saveCinema(cinemaDto);

        // assert
        Assertions.assertThat(cinemaResponseEntity).isNotNull();
        Assertions.assertThat(cinemaResponseEntity.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(cinemaResponseEntity.getBody().getAddress()).isEqualTo(cinema.getAddress());

        Assertions.assertThat(cinemaResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(cinema);
        Assertions.assertThat(cinemaResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/cinemas/1");

    }

    private void createObjects(Cinema cinema, CinemaDto cinemaDto) {
        // arrange pregatim metodele de care avem nevoi

        cinemaDto.setId(1L);
        cinemaDto.setAddress("acasa");
        cinemaDto.setName("CinemaCity");

        cinema.setId(1L);
        cinema.setAddress("acasa");
        cinema.setName("CinemaCity");
    }


    //    dereferentiez obiectele pt a fi sterse de garabage collector pt a elibera memorpa
    @After
    public void tearDown() {
        cinema = null;
        cinemaDto = null;
    }


}
