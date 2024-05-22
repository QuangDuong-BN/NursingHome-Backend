package com.example.nursinghome.repository;

import com.example.nursinghome.entity.Action;
import com.example.nursinghome.entitydto.ActionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("SELECT a.name,a.description, a.dateOfAction, a.timeOfDay, a.isVisitable FROM Action a WHERE a.dateOfAction = :dateOfAction ORDER BY a.timeOfDay ASC ")
    List<Object[]> getAction(@Param("dateOfAction")Date dateOfAction);

}
