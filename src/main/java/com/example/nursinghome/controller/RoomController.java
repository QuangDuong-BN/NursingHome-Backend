package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.RoomDTO;
import com.example.nursinghome.enumcustom.RoomType;
import com.example.nursinghome.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:63344")
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(HttpServletRequest httpServletRequest, @RequestBody RoomDTO roomDTO) {
        roomService.addRoom(httpServletRequest, roomDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.ok(roomService.getListRoom());
    }

    @GetMapping("/get_list_name_room_by_room_type")
    public ResponseEntity<?> getListNameRoomByRoomType(HttpServletRequest httpServletRequest, @RequestParam("roomType") RoomType roomType) {
        return ResponseEntity.ok(roomService.getListNameRoomByRoomType(httpServletRequest, roomType));
    }
}
