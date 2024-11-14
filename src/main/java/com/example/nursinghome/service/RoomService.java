package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.entitydto.RoomDTO;
import com.example.nursinghome.constants.enums.RoomType;
import com.example.nursinghome.projectioninterface.GetNameRoomProjection;
import com.example.nursinghome.repository.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.nursinghome.model.Room;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final JwtService jwtService;

    public void addRoom(HttpServletRequest httpServletRequest, RoomDTO roomDTO) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        if (!username.equals("ADMIN")){
            throw new RuntimeException("You don't have permission to add room");
        }

        var room = Room.builder()
                .name(roomDTO.getName())
                .roomType(roomDTO.getRoomType())
                .description(roomDTO.getDescription())
                .build();
        roomRepository.save(room);
    }

    public List<GetNameRoomProjection> getListNameRoomByRoomType(HttpServletRequest httpServletRequest, RoomType roomType){
        return roomRepository.getListNameRoomByRoomType(roomType);
    }


    public List<RoomDTO> getListRoom(){
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private RoomDTO mapToDTO(Room room) {
        return RoomDTO.builder()
                .name(room.getName())
                .roomType(room.getRoomType())
                .description(room.getDescription())
                .build();
    }
}
