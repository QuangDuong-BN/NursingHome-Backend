package com.example.nursinghome.repository;

import com.example.nursinghome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    @Query("SELECT r FROM Room r WHERE r.id = ?1")
    Room getRoomByID(Long roomIdFk);
}
