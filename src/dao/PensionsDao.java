package dao;

import core.Db;
import entity.Pension;
import entity.Hotel;

import java.sql.*;
import java.util.ArrayList;

public class PensionsDao {

    private Connection connection;

    public PensionsDao() {
        this.connection = Db.getInstance();
    }

    public boolean savePencion(Hotel hotel, String val) {
        String query = "INSERT INTO public.pension" +
                " (hotel_id, pension_name)" +
                " VALUES ( ?, ? )";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, hotel.getHotel_id());
            pr.setString(2, val);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pension";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }
    public ArrayList<Pension> findByHotelId(int hotelId) {
        ArrayList<Pension> pencionList = new ArrayList<>();
        String sql = "SELECT * FROM public.pension WHERE hotel_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                pencionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pencionList;
    }
    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_name(rs.getString("pension_name"));
        return obj;
    }

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
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
