package com.taka.service;

import com.taka.domain.JwtRequest;
import com.taka.domain.JwtResponse;
import com.taka.domain.Municipal;
import com.taka.domain.Users;
import com.taka.repo.MunicipalRepo;
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
    MunicipalRepo municipalRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest request) throws Exception{
        String email = request.getEmail();
//        String username = request.getUsername();
        String password = request.getPassword();

//        if (email != null && password != null){
//            authenticateMunicipal(email, password);
//
//            final UserDetails userDetails = loadUserByUsername(email);
//            String newToken = jwtUtils.generateTokens(userDetails);
//
//            Municipal municipal = municipalRepo.findByEmail(email);
//            return new JwtResponse(municipal, newToken);
//        }
//            String username = request.getUsername();
            authenticateUser(email, password);

            final UserDetails userDetails = loadUserByUsername(email);
            String newToken = jwtUtils.generateTokens(userDetails);

            Users user = userRepo.findById(email).get();
            return new JwtResponse(user, newToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Municipal municipal = municipalRepo.findByEmail(username);
        Users user = userRepo.findById(username).get();
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user));
//        if(user != null){

//            return new User(municipal.getEmail(), municipal.getPassword(), getAuthorities(municipal));
//        }else {
////            Users user = userRepo.findById(username).get();
//            return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
//        }
    }


//    authorize user
    private Set getAuthorities(Users user){
        Set authorities = new HashSet();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }

//    authorize municipal
    private Set getAuthorities(Municipal municipal){
        Set authorities = new HashSet();
//        municipal.getEmail().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//        });
        authorities.add(new SimpleGrantedAuthority("MUNICIPAL"));
        return authorities;
    }

//    authenticate municipal
    public void authenticateMunicipal(String email, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch (BadCredentialsException b){
            throw new Exception("Bad credentials");
        }catch (DisabledException d){
            throw new Exception("User is disabled");
        }

    }

//    authenticate user
    public void authenticateUser(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException b){
            throw new Exception("Bad credentials");
        }catch (DisabledException d){
            throw new Exception("User is disabled");
        }
    }
}
