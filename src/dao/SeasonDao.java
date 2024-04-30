package dao;

import core.Db;
import entity.Season;
import entity.Hotel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    //Tüm sezonları getiren metot
    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.season";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }
    public ArrayList<Season> findByHotelId(int hotelId) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.season WHERE hotel_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }
    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setSeason_srt_date(LocalDate.parse(rs.getString("season_srt_date")));
        obj.setSeason_fns_date(LocalDate.parse(rs.getString("season_fns_date")));
        return obj;
    }
    public boolean saveSeason(Hotel hotel, LocalDate strDate, LocalDate endDate) {
        String query = "INSERT INTO public.season" +
                "(hotel_id, season_srt_date, season_fns_date)" +
                " VALUES ( ?, ?, ?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, hotel.getHotel_id());
            pr.setDate(2, Date.valueOf(strDate));
            pr.setDate(3, Date.valueOf(endDate));

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
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

