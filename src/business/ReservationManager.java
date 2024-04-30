package business;

import core.Db;
import core.Helper;
import dao.ReservationDao;
import dao.RoomsDao;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import java.sql.Connection;
import java.util.ArrayList;

public class ReservationManager {
    private Connection con;
    private final RoomsDao roomsDao;
    private Hotel hotel;
    private HotelsManager hotelsManager;
    private Room room;
    private final ReservationDao reservationDao;
    private RoomManager roomManager;

    public ReservationManager(){
        reservationDao = new ReservationDao();
        this.con = Db.getInstance();
        this.roomsDao = new RoomsDao();
        this.hotel = new Hotel();
        this.room = room;
        this.hotelsManager = new HotelsManager();
        this.roomManager = new RoomManager();
    }
    //Id ye göre rezervasyon bilgilerini getirir
    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    //Rezervasyon silme işlmei
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            return false;
        }
        return this.reservationDao.delete(id);
    }
    //Rezervasyon güncelleme işlmei
    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getReservation_id()) == null) {
        }
        return this.reservationDao.update(reservation);
    }
    //Tüm rezervasyonları getirir
    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    //Yeni rezervasyon kaydı oluşturma
    public boolean save(Reservation reservation) {
        if (reservation.getReservation_id() != 0) {
            Helper.showMessage("error");

        }
        return this.reservationDao.save(reservation);
    }

    //Rezervasyonları belirtilen boyutta nesneye dönüştürür
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for (Reservation obj : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getReservation_id();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getReservation_in_date();
            rowObject[i++] = obj.getReservation_out_date();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getGuest_count();
            rowObject[i++] = obj.getCustomer_name();
            rowObject[i++] = obj.getCustomer_citizen_id();
            rowObject[i++] = obj.getCustomer_mail();
            rowObject[i++] = obj.getCustomer_phone();
            reservationList.add(rowObject);
        }
        return reservationList;
    }



}
