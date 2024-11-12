package com.alfredTech.hospitality_management_application.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Check in date is required")
    private LocalDate checkInDate;
    @Future(message = "Check out date must be in the future")
    private LocalDate checkOutDate;
    @Min(value = 1, message = "Number of Adult must not be less than 1")
    private int numberOfAdult;
    @Min(value = 0, message = "number of children must not be less than 0" )
    private int numberOfChildren;
    private int totalNumberOfGuest;
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;


    public void calculateTotalNumberOfGuest() {
        this.totalNumberOfGuest = this.numberOfAdult + this.numberOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numberOfAdult = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numberOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                "bookingConfirmationCode='" + bookingConfirmationCode + '\'' +
                ", totalNumberOfGuest=" + totalNumberOfGuest +
                ", numberOfChildren=" + numberOfChildren +
                ", numberOfAdult=" + numberOfAdult +
                ", checkOutDate=" + checkOutDate +
                ", checkInDate=" + checkInDate +
                '}';
    }
}
