package com.alfredTech.hospitality_management_application.services.interfac;

import com.alfredTech.hospitality_management_application.data.models.Booking;
import com.alfredTech.hospitality_management_application.dtos.reponse.Response;

public interface BookingService {

    Response saveBooking(Long roomId, Long UserId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);
    Response getAllBookings();
    Response cancelBooking(Long id);
}
