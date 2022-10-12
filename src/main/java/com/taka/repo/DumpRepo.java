package com.taka.repo;

import com.taka.domain.Dump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DumpRepo extends JpaRepository<Dump, Long> {

    List<Dump> findByMunicipal(String municipal);
}
