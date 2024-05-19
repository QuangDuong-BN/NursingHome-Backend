package com.example.nursinghome.repository;

import com.example.nursinghome.entity.ServiceRecord;
import com.example.nursinghome.entity.User;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    @Query("SELECT SUM(s.price) FROM ServiceRecord s")
    Double countRevenue();

    @Query(value = "SELECT sr.id,si.name, us.name,sr.booking_time,sr.production_date,sr.expiration_date,sr.price, sr.payment_status  FROM nursinghome.service_record as sr inner join user as us on sr.user_id_fk = us.id inner join service_info si on sr.service_info_id_fk = si.id;" ,nativeQuery = true)
//    @Query("SELECT s FROM ServiceRecord s WHERE s.familyMemberIdFk = :familyMemberIdFk")
    List<Object[]> getAllByIdFamilyMemberIdFk(@Param("familyMemberIdFk") User familyMemberIdFk);
}
