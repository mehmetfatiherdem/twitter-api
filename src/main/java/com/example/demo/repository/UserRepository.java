package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    //TODO: move this to a custom repository see: https://www.baeldung.com/spring-data-jpa-query#2-custom-repositories-and-the-jpa-criteria-api
    @Query("SELECT u FROM User u WHERE u.email = ?1 ")
    User findByEmail(String email);

}
