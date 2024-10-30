package com.alfredTech.hospitality_management_application.data.repositories;

import com.alfredTech.hospitality_management_application.data.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
