package com.example.nursinghome.repository;

import com.example.nursinghome.entity.ServiceInfo;
import com.example.nursinghome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInfoRepository extends JpaRepository<ServiceInfo, Long> {

}
