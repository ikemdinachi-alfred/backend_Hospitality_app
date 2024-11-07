package com.alfredTech.hospitality_management_application.services.impl;

import com.alfredTech.hospitality_management_application.data.models.Booking;
import com.alfredTech.hospitality_management_application.data.repositories.BookingRepository;
import com.alfredTech.hospitality_management_application.dtos.reponse.Response;
import com.alfredTech.hospitality_management_application.services.interfac.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;



    @Override
    public Response saveBooking(Long roomId, Long UserId, Booking bookingRequest) {
        return null;
    }

    @Override
    public Response findBookingByConfirmationCode(String confirmationCode) {
        return null;
    }

    @Override
    public Response getAllBookings() {
        return null;
    }

    @Override
    public Response cancelBooking(Long id) {
        return null;
    }
}
