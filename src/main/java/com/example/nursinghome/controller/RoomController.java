package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.RoomDTO;
import com.example.nursinghome.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;
    @PostMapping("/add")
    public ResponseEntity<?> addRoom(HttpServletRequest httpServletRequest, @RequestBody RoomDTO roomDTO) {
        roomService.addRoom(httpServletRequest,roomDTO);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/get_all")
    public ResponseEntity<?> getAllRoom(){
        return ResponseEntity.ok(roomService.getListRoom());
    }
}
