package entity;

import core.ComboItem;

public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_city;
    private String hotel_address;
    private String hotel_mpno;
    private String hotel_mail;
    private String hotel_star;
    private boolean car_park;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean room_service;

    public Hotel() {
    }

    public Hotel(int hotel_id, String hotel_name, String hotel_city, String hotel_address, String hotel_mpno, String hotel_mail, String hotel_star, boolean car_park, boolean wifi, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean room_service) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_city = hotel_city;
        this.hotel_address = hotel_address;
        this.hotel_mpno = hotel_mpno;
        this.hotel_mail = hotel_mail;
        this.hotel_star = hotel_star;
        this.car_park = car_park;
        this.wifi = wifi;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.room_service = room_service;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_mpno() {
        return hotel_mpno;
    }

    public void setHotel_mpno(String hotel_mpno) {
        this.hotel_mpno = hotel_mpno;
    }

    public String getHotel_mail() {
        return hotel_mail;
    }

    public void setHotel_mail(String hotel_mail) {
        this.hotel_mail = hotel_mail;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isCar_park() {
        return car_park;
    }

    public void setCar_park(boolean car_park) {
        this.car_park = car_park;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoom_service() {
        return room_service;
    }

    public void setRoom_service(boolean room_service) {
        this.room_service = room_service;
    }
    public ComboItem getComboItem() {return  new ComboItem(this.getHotel_id(), this.getHotel_name() + " - " + this.getHotel_city());}
}
