package com.taka.service;

import com.taka.domain.JwtRequest;
import com.taka.domain.JwtResponse;
import com.taka.domain.Users;
import com.taka.repo.UserRepo;
import com.taka.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest request) throws Exception{
        String username = request.getUsername();
        String password = request.getPassword();
        authenticate(username, password);

        final UserDetails userDetails = loadUserByUsername(username);
        String newToken = jwtUtils.generateTokens(userDetails);

        Users user = userRepo.findById(username).get();
        return new JwtResponse(user, newToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findById(username).get();

        if(user != null){
            return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
        }else {
            throw  new UsernameNotFoundException("Username could not found");
        }
    }

    private Set getAuthorities(Users user){
        Set authorities = new HashSet();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }

    public void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException b){
            throw new Exception("Bad credentials");
        }catch (DisabledException d){
            throw new Exception("User is disabled");
        }

    }
}
