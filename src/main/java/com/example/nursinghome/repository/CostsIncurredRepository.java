package com.example.nursinghome.repository;

import com.example.nursinghome.model.CostsIncurred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostsIncurredRepository extends JpaRepository<CostsIncurred, Long> {
}
