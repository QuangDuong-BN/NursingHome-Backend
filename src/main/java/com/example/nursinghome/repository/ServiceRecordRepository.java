package com.example.nursinghome.repository;

import com.example.nursinghome.entity.ServiceRecord;
import com.example.nursinghome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    @Query("SELECT SUM(s.price) FROM ServiceRecord s")
    Double countRevenue();

    @Query("SELECT s FROM ServiceRecord s WHERE s.familyMemberIdFk = :familyMemberIdFk")
    List<Object[]> getAllByIdFamilyMemberIdFk(@Param("familyMemberIdFk") User familyMemberIdFk);
}
