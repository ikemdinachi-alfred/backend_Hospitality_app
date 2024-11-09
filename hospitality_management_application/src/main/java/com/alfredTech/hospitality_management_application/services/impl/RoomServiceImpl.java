package com.alfredTech.hospitality_management_application.services.impl;

import com.alfredTech.hospitality_management_application.data.models.Room;
import com.alfredTech.hospitality_management_application.data.repositories.BookingRepository;
import com.alfredTech.hospitality_management_application.data.repositories.RoomRepository;
import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.dtos.requests.AddNewRoomRequest;
import com.alfredTech.hospitality_management_application.dtos.requests.RoomDTO;
import com.alfredTech.hospitality_management_application.dtos.requests.UpdateRoomRequest;
import com.alfredTech.hospitality_management_application.exception.OurException;
import com.alfredTech.hospitality_management_application.services.GoogleCloudStorage;
import com.alfredTech.hospitality_management_application.services.interfac.RoomService;
import com.alfredTech.hospitality_management_application.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
   @Autowired
   private RoomRepository roomRepository;
   @Autowired
   private BookingRepository bookingRepository;
   @Autowired
   private GoogleCloudStorage googleCloudStorage;
    @Override
    public Response addNewRoom(AddNewRoomRequest addNewRoomRequest) {
        Response response = new Response();
        try {
            String imageUrl = googleCloudStorage.uploadImage(addNewRoomRequest.getPhoto());
            Room room = new Room();
            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(addNewRoomRequest.getRoomType());
            room.setRoomPrice(addNewRoomRequest.getRoomPrice());
            room.setRoomDescription(addNewRoomRequest.getDescription());
            Room savedRoom = roomRepository.save(room);
            RoomDTO roomDTO = Utils.mapRoomModelToRoomDTO(savedRoom);
            response.setStatusCode(200);
            response.setMessage("Successfully added new room");
            response.setRoom(roomDTO);

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room  "+e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getAllRoomType() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public Response getAllRoomTypes() {

        Response response = new Response();
        try{
           List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
           List<RoomDTO> roomListDTO = Utils.mapRoomListModelToRoomListDTO(roomList);
           response.setStatusCode(200);
           response.setMessage("Successfully retrieved all the rooms");
            response.setRoomList(roomListDTO);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving all the rooms "+e.getMessage());

        }
        return response;
    }

    @Override
    public Response deleteAllRooms(Long roomId) {
        Response response = new Response();

        try {
            roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
            roomRepository.deleteById(roomId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response update(UpdateRoomRequest request) {
        Response response = new Response();
        try{
          String imageUrl = null;
          if (request.getPhoto() != null && !request.getPhoto().isEmpty() ){
              imageUrl = googleCloudStorage.uploadImage(request.getPhoto());
          }
          Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new OurException("Room not Found "));
          if (request.getRoomType()!= null) room.setRoomType(request.getRoomType());
          if (request.getRoomPrice() != null) room.setRoomPrice(request.getRoomPrice());
          if (request.getDescription() != null) room.setRoomDescription(request.getDescription());
          if(imageUrl != null) room.setRoomPhotoUrl(imageUrl);

          Room updateRoom = roomRepository.save(room);
          RoomDTO roomDTO = Utils.mapRoomModelToRoomDTO(updateRoom);

          response.setStatusCode(200);
          response.setMessage("successful");
          response.setRoom(roomDTO);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room "+ e.getMessage());
        }
        return response;
    }

    @Override
    public Response getRoomById(Long roomId) {
        Response response = new Response();
        try {
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
            RoomDTO roomDTO = Utils.mapRoomModelToRoomDTOPlusBookings(room);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAvailableRoomsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return null;
    }

    @Override
    public Response allAvailableRooms() {
        return null;
    }
}
