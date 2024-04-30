package entity;

import core.ComboItem;

public class Pension {
    private int pension_id;
    private int hotel_id;
    private String pension_name;

    public Pension() {
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public String getPension_name() {
        return pension_name;
    }

    public void setPension_name(String pension_name) {
        this.pension_name = pension_name;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Pension{" +
                "pension_id=" + pension_id +
                ", hotel_id=" + hotel_id +
                ", pension_name='" + pension_name + '\'' +
                '}';
    }
    public ComboItem getComboItem() {
        return new ComboItem(this.getPension_id(), this.getPension_name());
    }
}
