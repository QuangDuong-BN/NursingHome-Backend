package com.example.nursinghome.repository;

import com.example.nursinghome.entity.Bed;
import com.example.nursinghome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long>{
    @Query("SELECT count(b) FROM Bed b WHERE b.roomIdFk = ?1")
    int countBedByRoomIdFk(Room room);

    @Query("SELECT b FROM Bed b WHERE b.id = ?1")
    Bed getBedByID(Long id);
}
