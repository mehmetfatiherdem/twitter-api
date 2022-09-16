/*
package com.example.demo.seeder;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//FIXME: find a way not to run this every time we run the app
@Configuration
public class RoleSeeder {

    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    @Bean
    CommandLineRunner initDb(RoleRepository repo){
        return args -> {
            log.info("Seeding " + repo.save(new Role("normal")));
            log.info("Seeding " + repo.save(new Role("admin")));
        };
    }

}
*/