package com.example.nursinghome.repository;

import com.example.nursinghome.model.User;
import com.example.nursinghome.constants.enums.RoleUser;
import com.example.nursinghome.projectioninterface.UserProjection;
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
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.role = 'FAMILY_MEMBER' OR u.role = 'SERVICE_USER'")
    List<User> findAllUserAndFamilyUser();

    @Query("SELECT u FROM User u WHERE u.id = :id OR u.name LIKE %:name%")
    List<User> findAllByIDOrName(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findByID(@Param("id") Long id);

    @Query("SELECT u.name,u.dateOfBirth,u.address,u.gender FROM User u WHERE u.id = :id")
    Object getNameDateAddressGenderByID(@Param("id") Long id);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email")
    Long countByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.username = :username")
    Long countByUsername(@Param("username") String username);

    // admin repository
    @Transactional
    @Modifying
    void deleteUserById(Long id);

    @Query("SELECT u FROM User u")
    Iterable<User> findAllUser();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.username = :username WHERE u.id = :id")
    void updateById(@Param("id") Integer id, @Param("name") String name, @Param("email") String email, @Param("username") String username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :roleStaff")
    Integer countAllByRole(RoleUser roleStaff);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUerByUserName(String username);

    @Query("SELECT u.id AS id, u.name AS name, u.address AS address, u.gender AS gender, u.imageUrl AS imageUrl FROM User u WHERE u.familyMember = :familyMember")
    List<UserProjection> getAllUserByFamilyMember(User familyMember);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(Long id);

    @Query("SELECT ut.name, ut.address,ut.phone,ut.email,ut.imageUrl FROM User u join User ut on u.familyMember.id=ut.id WHERE u.id = :id")
    Object[] getFamilyUserByIdUser(Long id);
}
