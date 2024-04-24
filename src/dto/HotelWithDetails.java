package dto;

import entity.hotel.Hotel;
import entity.hotel.HotelDetails;

public class HotelWithDetails {
    private Hotel hotel;
    private HotelDetails details;

    public HotelWithDetails(Hotel hotel, HotelDetails details) {
        this.hotel = hotel;
        this.details = details;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public HotelDetails getDetails() {
        return details;
    }

    public void setDetails(HotelDetails details) {
        this.details = details;
    }
}
