package com.alfredTech.hospitality_management_application.utils;

import com.alfredTech.hospitality_management_application.data.models.Booking;
import com.alfredTech.hospitality_management_application.data.models.Room;
import com.alfredTech.hospitality_management_application.data.models.User;
import com.alfredTech.hospitality_management_application.dtos.requests.BookingDTO;
import com.alfredTech.hospitality_management_application.dtos.requests.RoomDTO;
import com.alfredTech.hospitality_management_application.dtos.requests.UserDTO;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    public static String generateRandomConfirmationCode(int length) {
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

    public static BookingDTO mapBookingModelToBookingDTO(Booking booking, boolean mapUser) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumberOfAdult());
        bookingDTO.setNumOfChildren(booking.getNumberOfChildren());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumberOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        return bookingDTO;
    }
    public static RoomDTO mapRoomModelToRoomDTOPlusBookings(Room room) {
        if (room == null) {
            return null;
        }
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDescription(room.getRoomDescription());

        List<BookingDTO> bookingDTOs = new ArrayList<>();
        if (room.getBookings() != null) {
            for (Booking booking : room.getBookings()) {
                bookingDTOs.add(Utils.mapBookingModelToBookingDTO(booking, true));
            }

        }
            roomDTO.setBookings(bookingDTOs);


        return roomDTO;

    }
        public static BookingDTO mapBookingModelToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumberOfAdult());
        bookingDTO.setNumOfChildren(booking.getNumberOfChildren());
        bookingDTO.setTotalNumOfGuest(booking.getTotalNumberOfGuest());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        if (mapUser) {
            bookingDTO.setUser(Utils.mapUserModelToUserDTO(booking.getUser()));
        }
        if (booking.getRoom() != null) {
            RoomDTO roomDTO = new RoomDTO();

            roomDTO.setId(booking.getRoom().getId());
            roomDTO.setRoomType(booking.getRoom().getRoomType());
            roomDTO.setRoomPrice(booking.getRoom().getRoomPrice());
            roomDTO.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDTO.setRoomDescription(booking.getRoom().getRoomDescription());
            bookingDTO.setRoom(roomDTO);
        }
        return bookingDTO;
    }

    public static UserDTO mapUserModelToUserDTOPlusUserBookingAndRoom(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()) {
            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingModelToBookingDTO(booking,false)).collect(Collectors.toList()));
        }
        return userDTO;
    }

    public static List<UserDTO> mapUseListModelToUserListDTO(List<User> users){
        return users.stream().map(Utils::mapUserModelToUserDTO).collect(Collectors.toList());
    }

    public static List<RoomDTO> mapRoomListModelToRoomListDTO(List<Room> rooms){
        return rooms.stream().map(Utils::mapRoomModelToRoomDTO).collect(Collectors.toList());
    }

    public static List<BookingDTO> mapBookingListModelToBookingListDTO(List<Booking> bookings) {
        List<BookingDTO> bookingDTOs = new ArrayList<>();

        if (bookings != null) {
            for (Booking booking : bookings) {
                bookingDTOs.add(Utils.mapBookingModelToBookingDTO(booking, true));
            }
        }

        return bookingDTOs;
    }

}
