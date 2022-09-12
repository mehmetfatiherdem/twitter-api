package com.example.demo.services;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.exception.auth.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public User signUp(UserRegisterDTO dto) throws UserAlreadyExistsException {
        if (userExists(dto.getEmail())) {
            throw new UserAlreadyExistsException(dto.getEmail());
        }

        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        

        userRepo.save(user);

        return user;
    }

    private boolean userExists(String email){
        User user = userRepo.findByEmail(email);
        if(user != null) return true;
        return false;
    }
}
