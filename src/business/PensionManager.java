package business;

import core.Helper;
import dao.PensionsDao;
import entity.Pension;
import entity.Hotel;

import java.util.ArrayList;

public class PensionManager {
    private final PensionsDao pensionDao;
    private Hotel hotel;

    public PensionManager(Hotel hotel) {
        this.hotel = hotel;
        this.pensionDao = new PensionsDao();
    }

    public boolean savePencion(Hotel hotel, String val) {
        if (hotel.getHotel_id() != 0) {
            Helper.showMessage("done");

        }
        return this.pensionDao.savePencion(hotel, val);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for (Pension obj : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_name();

            pensionList.add(rowObject);
        }
        return pensionList;
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public ArrayList<Pension> findByHotelId(int hotel_id) {
        return this.pensionDao.findByHotelId(hotel_id);
    }
}
