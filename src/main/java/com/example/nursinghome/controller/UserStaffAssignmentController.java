package com.example.nursinghome.controller;

import com.example.nursinghome.entity.UserStaffAssignment;
import com.example.nursinghome.entitydto.UserStaffAssignmentDTO;
import com.example.nursinghome.service.UserStaffAssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class UserStaffAssignmentController {

    @Autowired
    private UserStaffAssignmentService service;

    @GetMapping
    public List<UserStaffAssignment> getAllAssignments() {
        return service.getAllAssignments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStaffAssignment> getAssignmentById(@PathVariable Long id) {
        UserStaffAssignment assignment = service.getAssignmentById(id);
        if (assignment != null) {
            return ResponseEntity.ok(assignment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public UserStaffAssignment createAssignment(@RequestBody UserStaffAssignmentDTO userStaffAssignmentDTO) {
        return service.saveAssignment(userStaffAssignmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id) {
        service.deleteAssignment(id);
        return ResponseEntity.ok("Assignment deleted successfully.");
    }
}