package ro.fiipractic.mycinema.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.fiipractic.mycinema.dto.MovieRoomDto;
import ro.fiipractic.mycinema.entity.MovieRoom;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.typeMap(MovieRoomDto.class, MovieRoom.class).addMappings(n -> {
            n.<Long>map(MovieRoomDto::getCinema_id, (MovieRoom, v) -> MovieRoom.getCinema().setId(v));
        });

        return modelMapper;
    }
}
