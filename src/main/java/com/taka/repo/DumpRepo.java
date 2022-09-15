package com.taka.repo;

import com.taka.domain.Dump;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DumpRepo extends JpaRepository<Dump, Long> {
}
