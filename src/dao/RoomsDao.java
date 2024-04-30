package dao;

import core.Db;
import entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomsDao {
    //Oda veritabanı bağlantıları

    private Connection connection;

    private PensionsDao pencionDao;
    private SeasonDao seasonDao;
    private HotelsDao hotelDao;

    public RoomsDao() {
        this.connection = Db.getInstance();
        this.hotelDao = new HotelsDao();
        this.pencionDao = new PensionsDao();
        this.seasonDao = new SeasonDao();
    }

    //ResultSet'ten alınan verileri eşlemek için kullandığım metot
    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setRoom_type(rs.getString("room_type"));
        obj.setRoom_stock(rs.getInt("room_stock"));
        obj.setAdult_price(rs.getInt("adult_price"));
        obj.setChild_price(rs.getInt("child_price"));
        obj.setRoom_bed_capacity(rs.getInt("room_bed_capacity"));
        obj.setRoom_meter(rs.getInt("room_meter"));
        obj.setRoom_tv(rs.getBoolean("room_tv"));
        obj.setRoom_minibar(rs.getBoolean("room_minibar"));
        obj.setRoom_console(rs.getBoolean("room_console"));
        obj.setRoom_projection(rs.getBoolean("room_projection"));
        obj.setRoom_case(rs.getBoolean("room_case"));

        //DAO sınıflarını kullanarak ilgili nesnelerin set edilmesi.
        obj.setPencion(this.pencionDao.getById(rs.getInt("pension_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("season_id")));
        obj.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        return obj;
    }
    //Oda ekleme metotu
    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id, " +
                "pension_id, " +
                "season_id, " +
                "room_type, " +
                "room_stock, " +
                "adult_price, " +
                "child_price, " +
                "room_bed_capacity, " +
                "room_meter, " +
                "room_tv, " +
                "room_minibar, " +
                "room_console, " +
                "room_projection, " +
                "room_case " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getRoom_type());
            pr.setInt(5, room.getRoom_stock());
            pr.setInt(6, room.getAdult_price());
            pr.setInt(7, room.getChild_price());
            pr.setInt(8, room.getRoom_bed_capacity());
            pr.setInt(9, room.getRoom_meter());
            pr.setBoolean(10, room.isRoom_tv());
            pr.setBoolean(11, room.isRoom_minibar());
            pr.setBoolean(12, room.isRoom_console());
            pr.setBoolean(13, room.isRoom_projection());
            pr.setBoolean(14, room.isRoom_case());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //Oda ID'sine göre odayı getiren metot
    public Room getById(int id) {
        Room room = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id); //
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                room = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
    //Tüm odaları getiren metot
    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
    //Sorguya göre oda getiren metot
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return roomList;
    }
    //Oda stok miktarını güncelleyen metot
    public boolean updateStock(Room room){
        String query = "UPDATE public.room SET room_stock = ? WHERE room_id = ?";
        try {PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,room.getRoom_stock());
            pr.setInt(2,room.getRoom_id());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
