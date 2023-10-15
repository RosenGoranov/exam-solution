package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    private final StyleRepository styleRepository;

    private Initializer(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        initStyles();
    }

    private void initStyles() {
        if (this.styleRepository.count() == 0) {
            StyleEntity pop = new StyleEntity().setName(StyleTypeEnum.POP);
            StyleEntity rock = new StyleEntity().setName(StyleTypeEnum.ROCK);
            StyleEntity jazz = new StyleEntity().setName(StyleTypeEnum.JAZZ);

            this.styleRepository.saveAll(List.of(pop, rock, jazz));
        }

    }

}
