package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    //TODO: search why we really need this and can we find another solution
    //The overridden method is username but we have email in the user entity so I customized it to work it out

    //FIXME: Well here's the problem,
    // I try to pass user ID as a jwt payload. However this load by user name method runs somewhere directly and gets the user email even if I pass the user ID and signin gives 401. After generating the token this works normal and gets ID as a parameter.

    //To Reproduce the error: do userRepo.findByEmail and sign in. You get the token with the ID then change it to userRepo.findById and get req to auth test ep boom it works.

    //TODO: figure out where this is called before jwt auth and gets email as a param even if you pass id
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("in loadbyusername method email =====> " + email);
        User user = userRepo.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found with username or email:" + email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public UserDetails loadUserById(String id)throws UsernameNotFoundException{
        System.out.println("in loaduserbyID method id =====> " + id);
        UUID uuid = UUID.fromString(id);
        System.out.println("id from str => " + uuid);
        User user = userRepo.findById(uuid).get();
        System.out.println("user here " + user.getId());
        if(user == null) throw new UsernameNotFoundException("User not found with username or email:" + uuid);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
