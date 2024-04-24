package dao;

import core.Db;
import dto.HotelWithDetails;
import dto.RoomWithDetails;
import entity.hotel.HotelDetails;
import entity.room.RoomDetails;

import java.sql.*;

public class RoomsDao {

    private Connection connection;

    public RoomsDao() {
        this.connection = Db.getInstance();
    }

    public boolean addRoomDetail(RoomDetails roomDetails){
        String query = "INSERT INTO public.room_details (room_id,bed_count,room_meter,is_tv,is_minibar,is_console,is_case,is_projection)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, roomDetails.getRoom_id());
            pr.setInt(2, roomDetails.getBed_count());
            pr.setInt(3, roomDetails.getRoom_meter());
            pr.setBoolean(4, roomDetails.isIs_tv());
            pr.setBoolean(5, roomDetails.isIs_minibar());
            pr.setBoolean(6, roomDetails.isIs_console());
            pr.setBoolean(7, roomDetails.isIs_case());
            pr.setBoolean(8, roomDetails.isIs_projection());
            return pr.executeUpdate() != -1;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean addRoom(RoomWithDetails roomWithDetails) {
        String query = "INSERT INTO public.room (hotel_id,season_id,pension_id,room_type,room_stock,child_price,adult_price)" +
                " VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, roomWithDetails.getRoom().getHotel_id());
            pr.setInt(2, roomWithDetails.getRoom().getSeason_id());
            pr.setInt(3, roomWithDetails.getRoom().getPension_id());
            pr.setString(4, roomWithDetails.getRoom().getRoom_type());
            pr.setInt(5, roomWithDetails.getRoom().getRoom_stock());
            pr.setInt(6, roomWithDetails.getRoom().getChild_price());
            pr.setInt(7, roomWithDetails.getRoom().getAdult_price());

            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = pr.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long generatedId = generatedKeys.getLong(1);
                    roomWithDetails.getRoomDetails().setRoom_id(Math.toIntExact(generatedId));
                    boolean detail = addRoomDetail(roomWithDetails.getRoomDetails());
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

}
