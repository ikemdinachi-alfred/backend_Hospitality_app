package com.alfredTech.hospitality_management_application.services.interfac;

import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.dtos.requests.AddNewRoomRequest;
import com.alfredTech.hospitality_management_application.dtos.requests.UpdateRoomRequest;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
     Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description);
    List<String> getAllRoomType();
    Response getAllRoomTypes();
    Response updateRoom(Long roomId,  String description, String roomType, BigDecimal roomPrice,
                        MultipartFile photo);
    Response getRoomById(Long roomId);
    Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate,String roomType );
    Response allAvailableRooms();
    Response getAllRooms();
    Response deleteRoom(Long roomId);
}
