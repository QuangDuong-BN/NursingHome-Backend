package com.example.nursinghome.service;

import com.example.nursinghome.config.JwtService;
import com.example.nursinghome.model.Bed;
import com.example.nursinghome.model.Room;
import com.example.nursinghome.entitydto.BedDTO;
import com.example.nursinghome.enumcustom.RoomType;
import com.example.nursinghome.exception.NotImplementedException;
import com.example.nursinghome.projectioninterface.BedProjection;
import com.example.nursinghome.repository.BedRepository;
import com.example.nursinghome.repository.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BedService {
    private final JwtService jwtService;
    private final RoomRepository roomRepository;
    private final BedRepository bedRepository;
    private final RoomService roomService;

    public void addBed(HttpServletRequest httpServletRequest, BedDTO bedDTO) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
        if (!username.equals("ADMIN")) {
            throw new RuntimeException("You don't have permission to add room");
        }
        Room room = roomRepository.getRoomByID(bedDTO.getRoomIdFk());
        int bedCount = bedRepository.countBedByRoomIdFk(room);
        RoomType roomType = room.getRoomType();
        int maxBed;
        if (roomType == RoomType.ONE_BED) {
            maxBed = 1;
        } else if (roomType == RoomType.TWO_BED) {
            maxBed = 2;
        } else {
            maxBed = 3;
        }
        if(bedCount < maxBed){
            var bed = Bed.builder()
                    .name(bedDTO.getName())
                    .roomIdFk(roomRepository.getRoomByID(bedDTO.getRoomIdFk()))
                    .build();
            bedRepository.save(bed);
        }
        else {
            throw new NotImplementedException("Room is full (bed count = " + bedCount + ", max bed = " + maxBed + ")");
        }
    }

    public List<BedProjection> getListBedByRoomId(HttpServletRequest httpServletRequest, Long roomIdFk) {
        String token = httpServletRequest.getHeader("Authorization"); // Lấy token từ Header (thường được gửi trong header Authorization)
        token = token.substring(7); // Loại bỏ "Bearer " từ token
        String username = jwtService.extractUsername(token);
//        if (!username.equals("ADMIN")) {
//            throw new RuntimeException("You don't have permission to get list bed");
//        }

        return bedRepository.getListBedByRoomIdFk(roomRepository.getRoomByID(roomIdFk));
    }

}
