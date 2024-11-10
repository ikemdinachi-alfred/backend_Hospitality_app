package com.alfredTech.hospitality_management_application.services.interfac;

import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.dtos.requests.AddNewRoomRequest;
import com.alfredTech.hospitality_management_application.dtos.requests.UpdateRoomRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
   Response addNewRoom (AddNewRoomRequest addNewRoomRequest);
    List<String> getAllRoomType();
    Response getAllRoomTypes();
    Response updateRoom(Long roomId, UpdateRoomRequest request);
    Response getRoomById(Long roomId);
    Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate,String roomType );
    Response allAvailableRooms();
    Response getAllRooms();
    Response deleteRoom(Long roomId);
}
