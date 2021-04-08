package com.accenture.userservice.repository;

import com.accenture.userservice.domains.CEP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CEPRepository extends JpaRepository<CEP, Long> {
}
