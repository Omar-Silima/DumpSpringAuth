package com.taka.service;

import com.taka.domain.Role;
import com.taka.domain.Users;
import com.taka.repo.RoleRepo;
import com.taka.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users saveNewUser(Users user){
        Role role = roleRepo.findById("MUNICIPAL").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepo.save(user);
    }

    public void initUserRole(){
//        admin role
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setRoleDesc("Managing municipals");
        roleRepo.save(adminRole);

//        municipal role
        Role municipalRole = new Role();
        municipalRole.setRoleName("MUNICIPAL");
        municipalRole.setRoleDesc("Managing dumps");
        roleRepo.save(municipalRole);

//        admin data
        Users admin = new Users();
        admin.setUsername("admin123");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(getEncodedPassword("admin"));

        Set<Role> adRole = new HashSet<>();
        adRole.add(adminRole);
        admin.setRole(adRole);
        userRepo.save(admin);

//        municipal data
//        Users municipal = new Users();
//        municipal.setUsername("municipal");
//        municipal.setEmail("municipal@gmail.com");
//        municipal.setPassword(getEncodedPassword("municipal"));
//
//        Set<Role> muRole = new HashSet<>();
//        muRole.add(municipalRole);
//        municipal.setRole(muRole);
//        userRepo.save(municipal);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
