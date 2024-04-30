package entity;

import core.ComboItem;

import java.time.LocalDate;
import java.util.Date;

public class Season {
    private int season_id;
    private int hotel_id;
    private LocalDate season_srt_date;
    private LocalDate season_fns_date;

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getSeason_srt_date() {
        return season_srt_date;
    }

    public void setSeason_srt_date(LocalDate season_srt_date) {
        this.season_srt_date = season_srt_date;
    }

    public LocalDate getSeason_fns_date() {
        return season_fns_date;
    }

    public void setSeason_fns_date(LocalDate season_fns_date) {
        this.season_fns_date = season_fns_date;
    }

    @Override
    public String toString() {
        return "Season{" +
                "season_id=" + season_id +
                ", hotel_id=" + hotel_id +
                ", season_srt_date=" + season_srt_date +
                ", season_fns_date=" + season_fns_date +
                '}';
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getSeason_id(), this.getSeason_srt_date() + " - " + this.getSeason_fns_date());
    }
}
