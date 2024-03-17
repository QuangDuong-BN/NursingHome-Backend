package com.example.nursinghome.repository;

import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.projectioninterface.ServiceInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, Long> {
    @Query("SELECT s FROM ServiceInfo s WHERE s.id = ?1")
    ServiceInfo getServiceInfoById(Long id);

    @Query("SELECT s.id AS id, s.name AS name, s.descriptionService AS descriptionService,s.imageUrlIcon AS imageUrlIcon FROM ServiceInfo s ")
    List<ServiceInfoProjection> findAllInfoProjection();
}
