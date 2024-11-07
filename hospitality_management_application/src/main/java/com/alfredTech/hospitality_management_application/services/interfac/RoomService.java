package com.alfredTech.hospitality_management_application.services.interfac;

import com.alfredTech.hospitality_management_application.dtos.reponse.Response;

import java.util.List;

public interface RoomService {
    List<String> getAllRoomType();
    Response getAllRoomTypes();
}
