package com.taka.service;

import com.taka.domain.Municipal;
import com.taka.domain.Users;
import com.taka.repo.MunicipalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MunicipalService {

    @Autowired
    private MunicipalRepo municipalRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Municipal saveMunicipal(Municipal municipal){
//        municipal.setPassword(passwordEncoder.encode(municipal.getPassword()));
        return municipalRepo.save(municipal);
    }

    public List<Municipal> getAllMunicipals(){
        return municipalRepo.findAll();
    }

    public Municipal update(String name, Municipal municipal) {
        municipalRepo.findById(name);
        return municipalRepo.save(municipal);
    }
}
