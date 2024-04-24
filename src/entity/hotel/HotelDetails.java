package entity.hotel;

public class HotelDetails {
    private int hotel_detail_id;
    private int hotel_id;
    private boolean is_car_parking;
    private boolean is_wifi;
    private boolean is_fitness;
    private boolean is_pool;
    private boolean is_concierge;
    private boolean is_spa;
    private boolean is_room_service;

    public HotelDetails(boolean is_car_parking, boolean is_wifi, boolean is_fitness, boolean is_pool, boolean is_concierge, boolean is_spa, boolean is_room_service) {
        this.is_car_parking = is_car_parking;
        this.is_wifi = is_wifi;
        this.is_fitness = is_fitness;
        this.is_pool = is_pool;
        this.is_concierge = is_concierge;
        this.is_spa = is_spa;
        this.is_room_service = is_room_service;
    }

    public int getHotel_detail_id() {
        return hotel_detail_id;
    }

    public void setHotel_detail_id(int hotel_detail_id) {
        this.hotel_detail_id = hotel_detail_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public boolean isIs_car_parking() {
        return is_car_parking;
    }

    public void setIs_car_parking(boolean is_car_parking) {
        this.is_car_parking = is_car_parking;
    }

    public boolean isIs_wifi() {
        return is_wifi;
    }

    public void setIs_wifi(boolean is_wifi) {
        this.is_wifi = is_wifi;
    }

    public boolean isIs_fitness() {
        return is_fitness;
    }

    public void setIs_fitness(boolean is_fitness) {
        this.is_fitness = is_fitness;
    }

    public boolean isIs_pool() {
        return is_pool;
    }

    public void setIs_pool(boolean is_pool) {
        this.is_pool = is_pool;
    }

    public boolean isIs_concierge() {
        return is_concierge;
    }

    public void setIs_concierge(boolean is_concierge) {
        this.is_concierge = is_concierge;
    }

    public boolean isIs_spa() {
        return is_spa;
    }

    public void setIs_spa(boolean is_spa) {
        this.is_spa = is_spa;
    }

    public boolean isIs_room_service() {
        return is_room_service;
    }

    public void setIs_room_service(boolean is_room_service) {
        this.is_room_service = is_room_service;
    }
}
