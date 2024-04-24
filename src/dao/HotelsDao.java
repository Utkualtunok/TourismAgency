package dao;

import core.Db;
import dto.HotelWithDetails;
import entity.hotel.Hotel;
import entity.hotel.HotelDetails;

import java.sql.*;
import java.util.ArrayList;

public class HotelsDao {

    private Connection connection;

    public HotelsDao() {
        this.connection = Db.getInstance();
    }

    public boolean addHotelDetail(HotelDetails details){
        String query = "INSERT INTO public.hotel_details (hotel_id,is_car_parking,is_wifi,is_fitness,is_pool,is_concierge,is_spa,is_room_service)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, details.getHotel_id());
            pr.setBoolean(2, details.isIs_car_parking());
            pr.setBoolean(3, details.isIs_wifi());
            pr.setBoolean(4, details.isIs_fitness());
            pr.setBoolean(5, details.isIs_pool());
            pr.setBoolean(6, details.isIs_concierge());
            pr.setBoolean(7, details.isIs_spa());
            pr.setBoolean(8, details.isIs_room_service());
            return pr.executeUpdate() != -1;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean addHotel(HotelWithDetails hotelWithDetails) {
        String query = "INSERT INTO public.hotel (hotel_name,hotel_city,hotel_district,hotel_address,hotel_mail,hotel_mpno,hotel_star)" +
                " VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, hotelWithDetails.getHotel().getHotel_name());
            pr.setString(2, hotelWithDetails.getHotel().getHotel_city());
            pr.setString(3, hotelWithDetails.getHotel().getHotel_district());
            pr.setString(4, hotelWithDetails.getHotel().getHotel_address());
            pr.setString(5, hotelWithDetails.getHotel().getHotel_mail());
            pr.setString(6, hotelWithDetails.getHotel().getHotel_mpno());
            pr.setString(7, hotelWithDetails.getHotel().getHotel_star());

            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = pr.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long generatedId = generatedKeys.getLong(1);
                    hotelWithDetails.getDetails().setHotel_id(Math.toIntExact(generatedId));
                    boolean detail = addHotelDetail(hotelWithDetails.getDetails());
                    if (detail){
                        return true;
                    }
                    return false;
                }
            }

            return false;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Hotel> getAllHotels() {
        ArrayList<Hotel> hotels = new ArrayList<>();

        String query = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // ResultSet'ten verileri alarak bir Hotel nesnesi oluştur
                int hotelId = rs.getInt("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelCity = rs.getString("hotel_city");
                String hotelDistrict = rs.getString("hotel_district");
                String hotelAddress = rs.getString("hotel_address");
                String hotelMpno = rs.getString("hotel_mpno");
                String hotelMail = rs.getString("hotel_mail");
                String hotelStar = rs.getString("hotel_star");

                Hotel hotel = new Hotel();
                hotel.setHotel_id(hotelId);
                hotel.setHotel_name(hotelName);
                hotel.setHotel_city(hotelCity);
                hotel.setHotel_district(hotelDistrict);
                hotel.setHotel_address(hotelAddress);
                hotel.setHotel_mpno(hotelMpno);
                hotel.setHotel_mail(hotelMail);
                hotel.setHotel_star(hotelStar);

                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

}
