package dto;

import entity.reservations.Reservation;
import entity.reservations.ReservationDetails;

public class ReservationWithDetails {
    private Reservation reservation;
    private ReservationDetails reservationDetails;

    public ReservationWithDetails(Reservation reservation, ReservationDetails reservationDetails) {
        this.reservation = reservation;
        this.reservationDetails = reservationDetails;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ReservationDetails getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(ReservationDetails reservationDetails) {
        this.reservationDetails = reservationDetails;
    }
}
