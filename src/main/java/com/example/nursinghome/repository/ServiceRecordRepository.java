package com.example.nursinghome.repository;

import com.example.nursinghome.entity.CostsIncurred;
import com.example.nursinghome.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    @Query("SELECT SUM(s.price) FROM ServiceRecord s")
    Double countRevenue();
}
