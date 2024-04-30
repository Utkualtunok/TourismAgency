package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ReservationDao {

    private final Connection connection;

    public ReservationDao() {
        this.connection = Db.getInstance();
    }

    //Tüm rezervasyonları getiren metot
    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    //Alınan verileri eşleyen metot
    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setReservation_id(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("room_id"));
        String inDateStr = rs.getString("reservation_in_date");
        if (isValidDate(inDateStr)) {
            obj.setReservation_in_date(LocalDate.parse(inDateStr));
        } else {
            throw new DateTimeParseException("Geçersiz giriş tarihi: " + inDateStr, inDateStr, 0);
        }

        String outDateStr = rs.getString("reservation_out_date");
        if (isValidDate(outDateStr)) {
            obj.setReservation_out_date(LocalDate.parse(outDateStr));
        } else {
            throw new DateTimeParseException("Geçersiz çıkış tarihi: " + outDateStr, outDateStr, 0);
        }
        obj.setTotal_price(rs.getInt("total_price"));
        obj.setGuest_count(rs.getInt("guest_count"));
        obj.setCustomer_name(rs.getString("customer_name"));

        obj.setCustomer_mail(rs.getString("customer_mail"));
        String citizenIdStr = rs.getString("customer_citizen_id");
        if (isNumeric(citizenIdStr)) {
            obj.setCustomer_citizen_id(Integer.parseInt(citizenIdStr));
        } else {
            throw new NumberFormatException("Geçersiz kimlik numarası: " + citizenIdStr);
        }
        String phoneStr = rs.getString("customer_phone");
        if (isNumeric(phoneStr)) {
            obj.setCustomer_phone(Integer.parseInt(phoneStr));
        } else {
            throw new NumberFormatException("Geçersiz telefon numarası: " + phoneStr);
        }

        return obj;
    }
    private boolean isNumeric(String strNum) {

        // Verilen string bir sayıya dönüştürülebilir mi kontrol ediliyor.
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //id ye göre rezervasyonları getiren metot
    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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
    //Rezervasyon kayıt ekleme
    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                "reservation_in_date," +
                "reservation_out_date," +
                "total_price," +
                "guest_count," +
                "customer_name," +
                "customer_citizen_id," +
                "customer_mail," +
                "customer_phone" +
                ")" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getReservation_in_date()));
            pr.setDate(3, Date.valueOf(reservation.getReservation_out_date()));
            pr.setInt(4, reservation.getTotal_price());
            pr.setInt(5, reservation.getGuest_count());
            pr.setString(6, reservation.getCustomer_name());
            pr.setInt(7, reservation.getCustomer_citizen_id());
            pr.setString(8, reservation.getCustomer_mail());
            pr.setInt(9, reservation.getCustomer_phone());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    //Rezervasyon güncelleme
    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "guest_count= ? , " +
                "customer_name= ? , " +
                "customer_citizen_id= ? , " +
                "customer_mail= ? , "  +
                "customer_phone= ? " +
                "WHERE reservation_id = ? ";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, reservation.getGuest_count());
            pr.setString(2, reservation.getCustomer_name());
            pr.setInt(3, reservation.getCustomer_citizen_id());
            pr.setString(4, reservation.getCustomer_mail());
            pr.setInt(5, reservation.getCustomer_phone());
            pr.setInt(6,reservation.getReservation_id());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    //Rezervasyon silme işlemi
    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
