package com.alfredTech.hospitality_management_application.utils;

import com.alfredTech.hospitality_management_application.data.models.Room;
import com.alfredTech.hospitality_management_application.data.models.User;
import com.alfredTech.hospitality_management_application.dtos.requests.RoomDTO;
import com.alfredTech.hospitality_management_application.dtos.requests.UserDTO;

import java.security.SecureRandom;

public class Utils {
    private static  final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    private static  String generateRandomConfirmationCode(int length) {
        StringBuilder randomCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomInt);
            randomCode.append(randomChar);
        }
        return randomCode.toString();
    }

    public static UserDTO mapUserModelToUserDTO(User user) {
       UserDTO userDTO = new UserDTO();
       userDTO.setId(user.getId());
       userDTO.setName(user.getName());
       userDTO.setEmail(user.getEmail());
       userDTO.setPhoneNumber(user.getPhoneNumber());
       userDTO.setRole(userDTO.getRole());
       return userDTO;
    }
    public static RoomDTO mapRoomModelToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());
        return roomDTO;
    }
}
