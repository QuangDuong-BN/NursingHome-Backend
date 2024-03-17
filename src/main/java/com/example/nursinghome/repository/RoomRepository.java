package com.example.nursinghome.repository;

import com.example.nursinghome.entity.Room;
import com.example.nursinghome.enumcustom.RoomType;
import com.example.nursinghome.projectioninterface.GetNameRoomProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    @Query("SELECT r FROM Room r WHERE r.id = ?1")
    Room getRoomByID(Long roomIdFk);

    @Query("SELECT r.id AS id,r.name AS name FROM Room r WHERE r.roomType = ?1")
    List<GetNameRoomProjection> getListNameRoomByRoomType(RoomType roomType);
}
