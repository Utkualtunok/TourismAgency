package entity.reservations;

public class ReservationDetails {
    private int reservation_detail_id;
    private int reservation_id;
    private String guest_name;
    private String guest_region;
    private String guest_citizen_id;

    public int getReservation_detail_id() {
        return reservation_detail_id;
    }

    public void setReservation_detail_id(int reservation_detail_id) {
        this.reservation_detail_id = reservation_detail_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_region() {
        return guest_region;
    }

    public void setGuest_region(String guest_region) {
        this.guest_region = guest_region;
    }

    public String getGuest_citizen_id() {
        return guest_citizen_id;
    }

    public void setGuest_citizen_id(String guest_citizen_id) {
        this.guest_citizen_id = guest_citizen_id;
    }
}
