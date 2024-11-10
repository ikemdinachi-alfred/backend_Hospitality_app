package com.alfredTech.hospitality_management_application.controllers;

import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.dtos.requests.AddNewRoomRequest;
import com.alfredTech.hospitality_management_application.dtos.requests.UpdateRoomRequest;
import com.alfredTech.hospitality_management_application.services.interfac.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewRoom(@RequestBody AddNewRoomRequest request){
     if (request.getPhoto() == null || request.getPhoto().isEmpty() ||request.getRoomType()== null
     ||request.getRoomType().isBlank() || request.getRoomPrice()==null){
         Response response = new Response();
         response.setStatusCode(400);
         response.setMessage("please provide values for all fields (photo, room Type, room Price)");
         return ResponseEntity.status(response.getStatusCode()).body(response);
        }
     Response response = roomService.addNewRoom(request);
     return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllRooms(){
        Response response = roomService.getAllRooms();
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
    @GetMapping("/types")
    public List<String> getRoomTypes(){
        return roomService.getAllRoomType();
    }

    @GetMapping("/room-by-id/{roomId}")
    public ResponseEntity<Response> getRoomById(@PathVariable Long roomId){
        Response response = roomService.getRoomById(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/available-rooms-by-date-and-type")
    public ResponseEntity<Response> getAvailableRoomsByDateAndType(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam(required = false) String roomType
    ) {
        if (checkInDate == null || roomType == null || roomType.isBlank() || checkOutDate == null) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(checkInDate, roomType,checkOutDate)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = roomService.getAvailableRoomsByDataAndType(checkInDate, checkOutDate, roomType);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/update/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoom(@PathVariable Long roomId,
                                               @RequestBody UpdateRoomRequest request){
        Response response = roomService.updateRoom(roomId, request);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @DeleteMapping("/delete/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable Long roomId) {
        Response response = roomService.deleteRoom(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

}
