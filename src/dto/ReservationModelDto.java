package dto;

public class ReservationModelDto {
    private int roomId;
    private String roomType;
    private int childPrice;
    private int adultPrice;
    private int bedCount;
    private boolean isTv;
    private boolean isMinibar;
    private boolean isConsole;
    private boolean isCase;
    private boolean isProjection;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public boolean isTv() {
        return isTv;
    }

    public void setTv(boolean tv) {
        isTv = tv;
    }

    public boolean isMinibar() {
        return isMinibar;
    }

    public void setMinibar(boolean minibar) {
        isMinibar = minibar;
    }

    public boolean isConsole() {
        return isConsole;
    }

    public void setConsole(boolean console) {
        isConsole = console;
    }

    public boolean isCase() {
        return isCase;
    }

    public void setCase(boolean aCase) {
        isCase = aCase;
    }

    public boolean isProjection() {
        return isProjection;
    }

    public void setProjection(boolean projection) {
        isProjection = projection;
    }
}
