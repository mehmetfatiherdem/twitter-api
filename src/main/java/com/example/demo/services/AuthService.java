package com.example.demo.services;

import com.example.demo.dto.UserLogInDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.exception.auth.UserAlreadyExistsException;
import com.example.demo.exception.auth.UserDoesNotExistException;
import com.example.demo.exception.auth.WrongPasswordException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.JWTAuthResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JWTTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.demo.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public User signUp(UserRegisterDTO dto) throws UserAlreadyExistsException {
        if (userExists(dto.getEmail())) {
            throw new UserAlreadyExistsException(dto.getEmail());
        }

        Role role = roleRepo.findRoleByName("normal");

        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user.addRole(role);

        userRepo.save(user);

        return user;
    }

    @Override
    public JWTAuthResponse signIn(UserLogInDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail());
        if(user == null) throw new UserDoesNotExistException(dto.getEmail());

        String password = dto.getPassword();
        String hash = user.getPassword();

        if(!Password.matchPassword(password, hash)) throw new WrongPasswordException();

        // authenticate here
        System.out.println("before authentication in sign in");
        // last println here
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getPassword()));

        if(authentication.isAuthenticated()){
            System.out.println("authenticated");
        }else{
            System.out.println("not authed");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(user);

        System.out.println("token in return " + token);

        return new JWTAuthResponse(token);

    }

    private boolean userExists(String email){
        User user = userRepo.findByEmail(email);
        if(user != null) return true;
        return false;
    }
}
