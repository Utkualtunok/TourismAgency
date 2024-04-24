package entity;

import java.util.Date;

public class Season {
    private int season_id;
    private int hotel_id;
    private Date season_srt_date;
    private Date season_fns_date;
    private String season_name;

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

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

    public Date getSeason_srt_date() {
        return season_srt_date;
    }

    public void setSeason_srt_date(Date season_srt_date) {
        this.season_srt_date = season_srt_date;
    }

    public Date getSeason_fns_date() {
        return season_fns_date;
    }

    public void setSeason_fns_date(Date season_fns_date) {
        this.season_fns_date = season_fns_date;
    }
}
