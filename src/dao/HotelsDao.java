package dao;

import core.Db;
import entity.Hotel;
import java.sql.*;
import java.util.ArrayList;

public class HotelsDao {

    private Connection connection;

    public HotelsDao() {
        this.connection = Db.getInstance();
    }

    //Tüm otelleri getiren metot
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    //Verileri Otel nesnesine eşleyen metot
    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setHotel_city(rs.getString("hotel_city"));
        obj.setHotel_address(rs.getString("hotel_address"));
        obj.setHotel_mail(rs.getString("hotel_mail"));
        obj.setHotel_mpno(rs.getString("hotel_mpno"));
        obj.setHotel_star(rs.getString("hotel_star"));
        obj.setCar_park(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoom_service(rs.getBoolean("room_service"));
        return obj;
    }

    //Otel kayıt metotu
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel" +
                "(" +
                "hotel_name," +
                "hotel_city," +
                "hotel_mail," +
                "hotel_mpno," +
                "hotel_address," +
                "hotel_star," +
                "car_park," +
                "wifi," +
                "pool," +
                "fitness," +
                "concierge," +
                "spa," +
                "room_service" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_city());
            pr.setString(3, hotel.getHotel_mail());
            pr.setString(4, hotel.getHotel_mpno());
            pr.setString(5, hotel.getHotel_address());
            pr.setString(6, hotel.getHotel_star());
            pr.setBoolean(7, hotel.isCar_park());
            pr.setBoolean(8, hotel.isWifi());
            pr.setBoolean(9, hotel.isPool());
            pr.setBoolean(10, hotel.isFitness());
            pr.setBoolean(11, hotel.isConcierge());
            pr.setBoolean(12, hotel.isSpa());
            pr.setBoolean(13, hotel.isRoom_service());


            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Belirli bir otel ID'si ile oteli getiren metot
    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }

}
