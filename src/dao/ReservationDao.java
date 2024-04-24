package dao;

import core.Db;
import dto.HotelWithDetails;
import dto.ReservationDto;
import dto.ReservationModelDto;
import dto.RoomWithDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private Connection connection;

    public ReservationDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Object[]> searchReservation(ReservationDto dto) {
        String baseQuery = "SELECT room.room_id, room.room_type, room.child_price, room.adult_price, room_details.bed_count, room_details.is_tv, room_details.is_minibar, room_details.is_console, room_details.is_case, room_details.is_projection " +
                "FROM public.room " +
                "INNER JOIN room_details ON room.room_id = room_details.room_id " +
                "INNER JOIN hotel ON hotel.hotel_id = room.hotel_id " +
                "LEFT JOIN reservation ON room.room_id = reservation.room_id"; // Reservation tablosunu INNER JOIN veya LEFT JOIN olarak ekle

        List<String> conditions = new ArrayList<>();

        if (dto.getHotelName() != null && !dto.getHotelName().isEmpty()) {
            conditions.add("hotel.hotel_name = '" + dto.getHotelName() + "'");
        }

        if (dto.getHotelCity() != null && !dto.getHotelCity().isEmpty()) {
            conditions.add("hotel.hotel_city = '" + dto.getHotelCity() + "'");
        }

        if (dto.getInDate() != null && dto.getOutDate() != null &&
                !dto.getInDate().isEmpty() && !dto.getOutDate().isEmpty()) {
            conditions.add(
                    "NOT (reservation.reservation_out_date < '" + dto.getInDate() + "' OR reservation.reservation_in_date > '" + dto.getOutDate() + "')"
            );
        }

        int requiredRoomStock = 0;
        if (dto.getAdultCount() != null && !dto.getAdultCount().isEmpty()) {
            requiredRoomStock += Integer.valueOf(dto.getAdultCount());
        }
        if (dto.getChildCount() != null && !dto.getChildCount().isEmpty()) {
            requiredRoomStock += Integer.valueOf(dto.getChildCount());
        }

        conditions.add("room.room_stock > " + requiredRoomStock);

        if (!conditions.isEmpty()) {
            baseQuery += " WHERE " + String.join(" AND ", conditions) + " ";
        }

        baseQuery += " ORDER BY room.room_id ASC";

        System.out.println(baseQuery);

        try (PreparedStatement stmt = connection.prepareStatement(baseQuery)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Object[]> resultList = new ArrayList<>();

            while (rs.next()) {
                Object[] row = new Object[10]; // 10 columns in the SELECT query
                row[0] = rs.getInt("room_id");
                row[1] = rs.getString("room_type");
                row[2] = rs.getInt("child_price");
                row[3] = rs.getInt("adult_price");
                row[4] = rs.getInt("bed_count");
                row[5] = rs.getBoolean("is_tv");
                row[6] = rs.getBoolean("is_minibar");
                row[7] = rs.getBoolean("is_console");
                row[8] = rs.getBoolean("is_case");
                row[9] = rs.getBoolean("is_projection");

                resultList.add(row);
            }

            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HotelWithDetails> listHotels(){
        return null;
    }
}
