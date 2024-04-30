package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

//RezervationView rezervasyon ekleme ve güncelleme işlemlerinin yapıldığı yer

public class ReservationView extends Layout {
    private JPanel conteiner;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JPanel pnl_hotel_info;
    private JLabel lbl_star;
    private JTextField fld_star;
    private JRadioButton rbtn_parking;
    private JRadioButton rbtn_wifi;
    private JRadioButton rbtn_pool;
    private JRadioButton rbtn_fitness;
    private JRadioButton rbtn_concierge;
    private JRadioButton rbtn_spa;
    private JRadioButton rbtn_room_service;
    private JLabel lbl_city;
    private JLabel lbl_room_type;
    private JTextField fld_room_type;
    private JLabel lbl_pension_type;
    private JTextField fld_pension_type;
    private JLabel lbl_in_date;
    private JTextField fld_in_date;
    private JLabel lbl_out_date;
    private JTextField fld_out_date;
    private JTextField fld_total_price;
    private JLabel lbl_total_price;
    private JLabel lbl_bed_capacity;
    private JTextField fld_bed_capacity;
    private JTextField fld_meter;
    private JLabel lbl_meter;
    private JRadioButton rbtn_tv;
    private JRadioButton rbtn_minibar;
    private JRadioButton rbtn_console;
    private JRadioButton rbtn_case;
    private JRadioButton rbtn_projection;
    private JPanel pnl_room_info;
    private JPanel pnl_customer_info;
    private JTextField fld_customer_count;
    private JLabel lbl_customer_count;
    private JLabel lbl_customer_name;
    private JTextField fld_customer_name;
    private JTextField fld_customer_citizen_id;
    private JLabel lbl_customer_citizen_id;
    private JTextField fld_customer_mail;
    private JLabel lbl_customer_mail;
    private JLabel lbl_customer_mpno;
    private JTextField fld_customer_mpno;
    private JButton btn_reservation_save;
    private Room room;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private EmployeeView employeeView;
    private Hotel hotel;


    public ReservationView(Reservation reservation, Room room, JTextField startDate, JTextField endDate, JTextField adult, JTextField child) {
        this.reservation = reservation;
        this.room = room;
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        String strGuestCount;
        LocalDate from = null;
        LocalDate to = null;
        double totalPrice;

        this.add(conteiner);
        guiInitilaze(1000,800);

        //Rezervasyon ID 0 değil ise yani bir rezervasyonda güncelleme yapılıyor ise mecvut bilgiler kullanılır.
        if (this.reservation.getReservation_id() != 0) {
            from = this.reservation.getReservation_in_date();
            to = this.reservation.getReservation_out_date();
            strGuestCount = String.valueOf(this.reservation.getGuest_count());
            this.fld_customer_count.setText(strGuestCount);
            this.fld_customer_name.setText(this.reservation.getCustomer_name());
            this.fld_customer_citizen_id.setText(String.valueOf(this.reservation.getCustomer_citizen_id()));
            this.fld_customer_mail.setText(this.reservation.getCustomer_mail());
            this.fld_customer_mpno.setText(String.valueOf(this.reservation.getCustomer_phone()));
            totalPrice = this.reservation.getTotal_price();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                from = LocalDate.parse(startDate.getText(), formatter);
                to = LocalDate.parse(endDate.getText(), formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Geçersiz tarih formatı: " + e.getMessage());
                return;
            }
            if (child.getText().isEmpty() || child.getText() == null) {
                child.setText("0");
            }
            if (adult.getText().isEmpty() || child.getText() == null) {
                adult.setText("0");
            }
            int adultCount;
            int childCount;
            try {
                adultCount = Integer.parseInt(adult.getText());
                childCount = Integer.parseInt(child.getText());
            } catch (NumberFormatException e) {
                System.err.println("Sayısal olmayan değerler girildi: " + e.getMessage());
                return;
            }
            int totalGuestCount = adultCount + childCount;
            strGuestCount = String.valueOf(totalGuestCount);
            this.fld_customer_count.setText(strGuestCount);

            //Girilen tarih aralığı hesaplanıyor
            long daysBetween = ChronoUnit.DAYS.between(from, to);

            //Girilen tarih aralığına göre fiyat hesaplaması yapılıyor
            double adultPrice = this.room.getAdult_price();
            double childPrice = this.room.getChild_price();
            totalPrice = (adultPrice * adultCount + childPrice * childCount) * daysBetween;
        }

        // Arayüzdeki bileşenlere otel ve oda bilgileri set edilir.
        this.fld_hotel_name.setText(this.room.getHotel().getHotel_name());
        this.fld_city.setText(this.room.getHotel().getHotel_city());
        this.fld_star.setText(this.room.getHotel().getHotel_star());
        this.fld_room_type.setText(this.room.getRoom_type());
        this.fld_pension_type.setText(String.valueOf(this.room.getPension_id()));
        this.fld_in_date.setText(from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.fld_out_date.setText(to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.fld_total_price.setText(String.valueOf(totalPrice));
        this.fld_meter.setText(String.valueOf(this.room.getRoom_meter()));
        this.fld_bed_capacity.setText(String.valueOf(this.room.getRoom_bed_capacity()));

        // Otel özellikleri için radio button'lar kontrol edilip set edilir.
        this.rbtn_concierge.setSelected(hotel.isConcierge());
        this.rbtn_parking.setSelected(hotel.isCar_park());
        this.rbtn_fitness.setSelected(hotel.isFitness());
        this.rbtn_wifi.setSelected(hotel.isWifi());
        this.rbtn_spa.setSelected(hotel.isSpa());
        this.rbtn_pool.setSelected(hotel.isPool());
        this.rbtn_room_service.setSelected(hotel.isRoom_service());
        this.rbtn_tv.setSelected(this.room.isRoom_tv());
        this.rbtn_console.setSelected(this.room.isRoom_console());
        this.rbtn_projection.setSelected(this.room.isRoom_projection());
        this.rbtn_minibar.setSelected(this.room.isRoom_minibar());


        btn_reservation_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Gerekli text alanları kontrol edilir
                JTextField[] checkFieldList = {fld_customer_name, fld_customer_citizen_id, fld_customer_mail, fld_customer_mpno, fld_in_date, fld_out_date};
                if (Helper.isFieldListEmpty(checkFieldList)) {
                    Helper.showMessage("fill");
                } else {
                    boolean result = true;
                    reservation.setRoom_id(room.getRoom_id());
                    reservation.setCustomer_name(fld_customer_name.getText());
                    reservation.setCustomer_citizen_id(Integer.parseInt(String.valueOf((fld_customer_citizen_id.getText()))));
                    reservation.setCustomer_mail(fld_customer_mail.getText());
                    reservation.setCustomer_phone(Integer.parseInt(String.valueOf(fld_customer_mpno.getText())));
                    reservation.setReservation_in_date(LocalDate.parse(startDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    reservation.setReservation_out_date(LocalDate.parse(endDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    reservation.setTotal_price((int) totalPrice);
                    reservation.setGuest_count(Integer.parseInt(strGuestCount));

                    // Rezervasyon ID'si 0 değilse (yani mevcut bir rezervasyon güncelleniyorsa), güncelleme yapılır.
                    if (reservation.getReservation_id() != 0) {
                        result = reservationManager.update(reservation);
                        dispose();
                    } else {

                        // Yeni bir rezervasyon ekleniyorsa, rezervasyon kaydedilir ve oda stok güncellenir.
                        result = reservationManager.save(reservation);
                        room.setRoom_stock(room.getRoom_stock() - 1);
                        roomManager.updateStock(room);
                        dispose();
                    }
                    if (result) {
                        Helper.showMessage("done");


                    } else {
                        Helper.showMessage("error");
                    }

                }
            }
        });

    }
}
