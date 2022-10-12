package com.taka.service;

import com.taka.domain.Municipal;
import com.taka.domain.Role;
import com.taka.domain.Users;
import com.taka.repo.MunicipalRepo;
import com.taka.repo.RoleRepo;
import com.taka.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    MunicipalRepo muniRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users saveNewUser(Users user){
        Role role = roleRepo.findById("MUNICIPAL").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setPassword(getEncodedPassword("municipal"));

        Municipal municipal = new Municipal();
        municipal.setName(user.getMunicipal().getName());
        municipal.setAddress(user.getMunicipal().getAddress());
        municipal.setDistrict(user.getMunicipal().getDistrict());
        municipal.setPhone(user.getMunicipal().getPhone());
        municipal.setRegion(user.getMunicipal().getRegion());
        municipal.setZipCode(user.getMunicipal().getZipCode());
        municipal.setEmail(user.getEmail());
        municipal.setId(user.getMunicipal().getId());

        muniRepo.save(municipal);

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
//        admin.setUsername("admin123");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(getEncodedPassword("admin"));

        Set<Role> adRole = new HashSet<>();
        adRole.add(adminRole);
        admin.setRole(adRole);
        userRepo.save(admin);

//        municipal data
//        Municipal municipal = new Municipal();

        Users userMuni = new Users();
//        muniRepo.save(municipal);
//        municipal.setUsername("municipal");
//        municipal.setEmail("municipal@gmail.com");
        userMuni.setPassword(getEncodedPassword("municipal"));

//        userMuni.setMunicipal(municipal);
//
//        Set<Role> muRole = new HashSet<>();
//        muRole.add(municipalRole);
//        municipal.setRole(muRole);
//        userRepo.save(userMuni);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

    public long countUsers(){
        return userRepo.count();
    }

    public List<Users> getAll() {
        return userRepo.findAll();
    }
}
