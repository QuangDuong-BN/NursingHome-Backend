package com.example.nursinghome.repository;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.entity.UserStaffAssignment;
import com.example.nursinghome.entity.VisitRecord;
import com.example.nursinghome.projectioninterface.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStaffAssignmentRepository extends JpaRepository<UserStaffAssignment, Long> {
    @Query("SELECT u.id AS id, u.name AS name, u.address AS address, u.gender AS gender, u.imageUrl AS imageUrl FROM User u JOIN UserStaffAssignment as us on u.id = us.user.id where us.staff =:docter")
    List<UserProjection> getAllUserByDocter(User docter);
}
