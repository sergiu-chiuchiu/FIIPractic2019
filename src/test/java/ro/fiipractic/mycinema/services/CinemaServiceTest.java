package ro.fiipractic.mycinema.services;

import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entity.Cinema;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.services.impl.CinemaServiceImpl;

public class CinemaServiceTest {


    @Mock
    CinemaRepository cinemaRepository;

    @InjectMocks
    CinemaServiceImpl cinemaService;

    Cinema cinema;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cinema = new Cinema();
        cinema.setId(1L);
        cinema.setName("cinemaCity");
        cinema.setAddress("tudor");
    }

    @Test
    public void shouldReturnCinemaById() throws NotFoundException {
        // arrange
        Mockito.when(cinemaRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(cinema));


        //act
        Cinema cinemaById = cinemaService.getCinemaById(1L);

        //assert
        Assertions.assertThat(cinemaById).isNotNull();
        Assertions.assertThat(cinemaById).isEqualToComparingFieldByFieldRecursively(cinema);

    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenCinemaById() throws NotFoundException {
        // arrange
        Mockito.when(cinemaRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        //act
        Cinema cinemaById = cinemaService.getCinemaById(2L);
    }

}
