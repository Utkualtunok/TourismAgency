package business;

import core.Helper;
import dao.HotelsDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelsManager {
    private final HotelsDao hotelsDao;

    public HotelsManager() {
        this.hotelsDao = new HotelsDao();
    }

    //Dao ile bağlantı kurarak tüm otelleri getirir
    public ArrayList<Hotel> findAll() {
        return this.hotelsDao.findAll();
    }
    //Dao ile bağlantı kurarak belirtilen id yi getirir
    public Hotel getById(int id) {
        return this.hotelsDao.getById(id);
    }

    //Belirtilen boyutta bir nesne dizisi olarak getirilir.
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel obj : hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getHotel_name();
            rowObject[i++] = obj.getHotel_city();
            rowObject[i++] = obj.getHotel_address();
            rowObject[i++] = obj.getHotel_mail();
            rowObject[i++] = obj.getHotel_mpno();
            rowObject[i++] = obj.getHotel_star();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_service();

            hotelList.add(rowObject);
        }
        return hotelList;
    }
    //Otel kayıt için dao ile iletişim kurar
    public boolean save(Hotel hotel) {
        if (hotel.getHotel_id() != 0) {
            Helper.showMessage("error");

        }
        return this.hotelsDao.save(hotel);
    }
}
