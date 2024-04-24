package entity.room;

public class Room {
    private int room_id;
    private int hotel_id;
    private int season_id;
    private int pension_id;
    private String room_type;
    private int room_stock;
    private int child_price;
    private int adult_price;

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
}
