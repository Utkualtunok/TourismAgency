package entity.room;

public class RoomDetails {
    private int room_details_id;
    private int room_id;
    private int bed_count;
    private int room_meter;
    private boolean is_tv;
    private boolean is_minibar;
    private boolean is_console;
    private boolean is_case;
    private boolean is_projection;

    public int getRoom_details_id() {
        return room_details_id;
    }

    public void setRoom_details_id(int room_details_id) {
        this.room_details_id = room_details_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getBed_count() {
        return bed_count;
    }

    public void setBed_count(int bed_count) {
        this.bed_count = bed_count;
    }

    public int getRoom_meter() {
        return room_meter;
    }

    public void setRoom_meter(int room_meter) {
        this.room_meter = room_meter;
    }

    public boolean isIs_tv() {
        return is_tv;
    }

    public void setIs_tv(boolean is_tv) {
        this.is_tv = is_tv;
    }

    public boolean isIs_minibar() {
        return is_minibar;
    }

    public void setIs_minibar(boolean is_minibar) {
        this.is_minibar = is_minibar;
    }

    public boolean isIs_console() {
        return is_console;
    }

    public void setIs_console(boolean is_console) {
        this.is_console = is_console;
    }

    public boolean isIs_case() {
        return is_case;
    }

    public void setIs_case(boolean is_case) {
        this.is_case = is_case;
    }

    public boolean isIs_projection() {
        return is_projection;
    }

    public void setIs_projection(boolean is_projection) {
        this.is_projection = is_projection;
    }
}
