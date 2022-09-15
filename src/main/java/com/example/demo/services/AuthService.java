package com.example.demo.services;

import com.example.demo.dto.UserLogInDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.exception.auth.UserAlreadyExistsException;
import com.example.demo.exception.auth.UserDoesNotExistException;
import com.example.demo.exception.auth.WrongPasswordException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Password;
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
        user.setPassword(dto.getPassword()); //FIXME: check why password hashing takes a lot of time: https://stackoverflow.com/questions/51777464/why-is-spring-boot-security-basic-authentication-slow#:~:text=The%20number%20of%20rounds%20that%20this%20java%20code%20used%20for%20generating%20the%20BCrypt%20encoded%20password%20is%2016%20which%20is%20too%20high.%20the%20standard%20round%20number%20that%20can%20help%20to%20serve%20the%20balance%20between%20time%2C%20memory%20and%20security%20is%2010.

        userRepo.save(user);

        return user;
    }

    @Override
    public String signIn(UserLogInDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail());

        if(user == null) throw new UserDoesNotExistException(dto.getEmail());

        String password = dto.getPassword();
        String hash = user.getPassword();

        if(!Password.matchPassword(password, hash)) throw new WrongPasswordException();

        // authenticate here


        return "Welcome back " + user.getName();

    }

    private boolean userExists(String email){
        User user = userRepo.findByEmail(email);
        if(user != null) return true;
        return false;
    }
}
