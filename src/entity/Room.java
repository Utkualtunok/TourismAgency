package entity;

public class Room {
    private int room_id;
    private int hotel_id;
    private int season_id;
    private int pension_id;
    private String room_type;
    private int room_stock;
    private int child_price;
    private int adult_price;
    private int room_bed_capacity;
    private int room_meter;
    private boolean room_tv;
    private boolean room_minibar;
    private boolean room_console;
    private boolean room_projection;
    private boolean room_case;
    private Hotel hotel;
    private Season season;
    private Pension pencion;

    public enum RoomType {
        Single_room,
        Double_room,
        junior_suite_room,
        suite_room
    }

    public Room() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getRoom_stock() {
        return room_stock;
    }

    public void setRoom_stock(int room_stock) {
        this.room_stock = room_stock;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getRoom_bed_capacity() {
        return room_bed_capacity;
    }

    public void setRoom_bed_capacity(int room_bed_capacity) {
        this.room_bed_capacity = room_bed_capacity;
    }

    public int getRoom_meter() {
        return room_meter;
    }

    public void setRoom_meter(int room_meter) {
        this.room_meter = room_meter;
    }

    public boolean isRoom_tv() {
        return room_tv;
    }

    public void setRoom_tv(boolean room_tv) {
        this.room_tv = room_tv;
    }

    public boolean isRoom_minibar() {
        return room_minibar;
    }

    public void setRoom_minibar(boolean room_minibar) {
        this.room_minibar = room_minibar;
    }

    public boolean isRoom_console() {
        return room_console;
    }

    public void setRoom_console(boolean room_console) {
        this.room_console = room_console;
    }

    public boolean isRoom_projection() {
        return room_projection;
    }

    public void setRoom_projection(boolean room_projection) {
        this.room_projection = room_projection;
    }

    public boolean isRoom_case() {
        return room_case;
    }

    public void setRoom_case(boolean room_case) {
        this.room_case = room_case;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pension getPencion() {
        return pencion;
    }

    public void setPencion(Pension pencion) {
        this.pencion = pencion;
    }
}
