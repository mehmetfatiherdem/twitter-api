package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    //TODO: move this to a custom repository see: https://www.baeldung.com/spring-data-jpa-query#2-custom-repositories-and-the-jpa-criteria-api
    //TODO: we do not need the Query annotation probably JPA will interpret by the method name
    @Query("SELECT u FROM User u WHERE u.email = ?1 ")
    User findByEmail(String email);

}
