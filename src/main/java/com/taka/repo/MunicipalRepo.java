package com.taka.repo;

import com.taka.domain.Municipal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalRepo extends JpaRepository<Municipal, String> {

//    public Municipal findByEmail(String email);
}
