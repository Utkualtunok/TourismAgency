package entity;

import java.time.LocalDate;

public class Reservation {
    private int reservation_id;
    private int room_id;
    private String  customer_name;
    private int customer_phone;
    private String customer_mail;
    private LocalDate reservation_in_date;
    private LocalDate reservation_out_date;
    private int guest_count;
    private int total_price;
    private int customer_citizen_id;

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getCustomer_citizen_id() {
        return customer_citizen_id;
    }

    public void setCustomer_citizen_id(int customer_citizen_id) {
        this.customer_citizen_id = customer_citizen_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(int customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_mail() {
        return customer_mail;
    }

    public void setCustomer_mail(String customer_mail) {
        this.customer_mail = customer_mail;
    }

    public LocalDate getReservation_in_date() {
        return reservation_in_date;
    }

    public void setReservation_in_date(LocalDate reservation_in_date) {
        this.reservation_in_date = reservation_in_date;
    }

    public LocalDate getReservation_out_date() {
        return reservation_out_date;
    }

    public void setReservation_out_date(LocalDate reservation_out_date) {
        this.reservation_out_date = reservation_out_date;
    }

    public int getGuest_count() {
        return guest_count;
    }

    public void setGuest_count(int guest_count) {
        this.guest_count = guest_count;
    }
}
