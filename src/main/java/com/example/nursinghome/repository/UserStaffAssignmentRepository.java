package com.example.nursinghome.repository;

import com.example.nursinghome.entity.UserStaffAssignment;
import com.example.nursinghome.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStaffAssignmentRepository extends JpaRepository<UserStaffAssignment, Long> {

}
