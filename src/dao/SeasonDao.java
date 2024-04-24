package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Season> getAllSeasons() {
        ArrayList<Season> seasons = new ArrayList<>();

        String query = "SELECT * FROM public.season ORDER BY season_id ASC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Veritabanından değerleri al ve Season nesnesi oluştur
                int seasonId = rs.getInt("season_id");
                int hotelId = rs.getInt("hotel_id");
                Date seasonStartDate = rs.getDate("season_srt_date");
                Date seasonEndDate = rs.getDate("season_fns_date");
                String seasonName = rs.getString("season_name");

                Season season = new Season();
                season.setSeason_id(seasonId);
                season.setHotel_id(hotelId);
                season.setSeason_srt_date(seasonStartDate);
                season.setSeason_fns_date(seasonEndDate);
                season.setSeason_name(seasonName);

                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }
}

