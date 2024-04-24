package dto;

public class ReservationDto {
    private String hotelName;
    private String hotelCity;
    private String inDate;
    private String outDate;
    private String adultCount;
    private String childCount;

    public ReservationDto(String hotelName, String hotelCity, String inDate, String outDate, String adultCount, String childCount) {
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.inDate = inDate;
        this.outDate = outDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(String adultCount) {
        this.adultCount = adultCount;
    }

    public String getChildCount() {
        return childCount;
    }

    public void setChildCount(String childCount) {
        this.childCount = childCount;
    }
}
