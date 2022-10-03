package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService, ICustomUserDetailsService {
    private UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found with username or email:" + email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    public UserDetails loadUserById(String id)throws UsernameNotFoundException{

        UUID uuid = UUID.fromString(id);
        User user = userRepo.findById(uuid).get();

        if(user == null) throw new UsernameNotFoundException("User not found with username or email:" + uuid);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User getLoggedInUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();

        var principal = auth.getPrincipal();

        String email = null;
        if(principal instanceof UserDetails){
             email = ((UserDetails)principal).getUsername();

        }else{
             email = principal.toString();
        }


        User user = userRepo.findByEmail(email);

        return user;
    }
}
