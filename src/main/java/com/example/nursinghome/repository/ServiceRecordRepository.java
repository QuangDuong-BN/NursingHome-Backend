package com.example.nursinghome.repository;

import com.example.nursinghome.entity.ServiceRecord;
import com.example.nursinghome.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    @Query("SELECT SUM(s.price) FROM ServiceRecord s")
    Double countRevenue();

    @Query(value = "SELECT sr.id,si.name, us.name,sr.booking_time,sr.production_date,sr.expiration_date,sr.price, sr.payment_status  FROM nursinghome.service_record as sr inner join user as us on sr.user_id_fk = us.id inner join service_info si on sr.service_info_id_fk = si.id where sr.family_member_id_fk= :familyMemberIdFk and sr.record_status ='ACTIVE' order by sr.id DESC ;", nativeQuery = true)
    List<Object[]> getAllByIdFamilyMemberIdFk(@Param("familyMemberIdFk") Long familyMemberIdFk);

    @Query("SELECT s FROM ServiceRecord s WHERE s.userIdFk = :user AND :dateOfVisit BETWEEN s.productionDate AND s.expirationDate")
    List<ServiceRecord> getServiceRecordByUserIdFk(@Param("user") User user, @Param("dateOfVisit") Date dateOfVisit);

//    @Query("SELECT s.id, s.serviceInfoIdFk.name, s.userIdFk.name, s.productionDate,s.expirationDate,s.bedIdFk.name,s.bedIdFk.roomIdFk.name,s.bedIdFk.roomIdFk.description,s.price,s.paymentStatus FROM ServiceRecord s WHERE s.id = :id")
//    Object[] getServiceById(@Param("id") Long id);
    @Query("SELECT s.id, s.serviceInfoIdFk.name, s.userIdFk.name, s.productionDate,s.expirationDate,'null','null','null',s.price,s.paymentStatus FROM ServiceRecord s WHERE s.id = :id")
    Object getServiceById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ServiceRecord s SET s.recordStatus = 'CANCELLED' WHERE s.id = :id")
    void updateServiceRecordById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ServiceRecord s WHERE s.id = :id")
    void deleteServiceRecordById(@Param("id") Long id);
}
