package com.example.nursinghome.repository;

import com.example.nursinghome.entity.User;
import com.example.nursinghome.enumcustom.RoleUser;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // tim kiem user theo email
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);


    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email")
    Long countByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.username = :username")
    Long countByUsername(@Param("username") String username);


    // admin repository
    @Transactional
    @Modifying
    //deleteMarketById
    void deleteUserById (Integer id);

    @Query("SELECT u FROM User u")
    Iterable<User> findAllUser ();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.username = :username WHERE u.id = :id")
    void updateById( @Param("id") Integer id, @Param("name") String name, @Param("email") String email, @Param("username") String username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :roleStaff")
    Integer countAllByRole(RoleUser roleStaff);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUerByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.familyMember = :familyMember")
    List<User> getAllUserByFamilyMember(User familyMember);
}
