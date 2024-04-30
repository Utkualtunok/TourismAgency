package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;
import entity.Hotel;

import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    private Hotel hotel;

    public SeasonManager(Hotel hotel) {
        this.hotel = hotel;
        this.seasonDao = new SeasonDao();
    }
    public boolean saveSeason(Hotel hotel, LocalDate strDate, LocalDate endDate) {
        if (hotel.getHotel_id() != 0) {
            Helper.showMessage("done");
        }
        return this.seasonDao.saveSeason(hotel, strDate, endDate);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getSeason_srt_date();
            rowObject[i++] = obj.getSeason_fns_date();

            seasonList.add(rowObject);
        }
        return seasonList;
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public ArrayList<Season> findByHotelId(int hotel_id) {
        return this.seasonDao.findByHotelId(hotel_id);
    }
}
