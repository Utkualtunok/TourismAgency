package view;

import core.Helper;
import dao.HotelsDao;
import dto.HotelWithDetails;
import entity.hotel.Hotel;
import entity.hotel.HotelDetails;

import javax.swing.*;

public class HotelSaveView extends Layout {
    private JPanel conteiner;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_mail;
    private JLabel lbl_hotel_phone;
    private JLabel lbl_hotel_mail;
    private JTextField fld_hotel_phone;
    private JLabel lbl_address;
    private JTextField fld_address;
    private JLabel lbl_hotel_star;
    private JTextField fld_hotel_star;
    private JRadioButton rbtn_parking;
    private JRadioButton rbtn_wifi;
    private JRadioButton rbtn_pool;
    private JRadioButton rbtn_fitness;
    private JRadioButton rbtn_concierge;
    private JRadioButton rbtn_spa;
    private JRadioButton rbtn_room_service;
    private JButton btn_hotel_save;
    private JTextField fld_hotel_city;
    private JTextField fld_district;

    public HotelSaveView() {
        this.add(conteiner);
        this.guiInitilaze(1000,500);

        btn_hotel_save.addActionListener(e -> {
            HotelsDao hotelsDao = new HotelsDao();
            Hotel hotel = new Hotel();
            HotelDetails hotelDetails = new HotelDetails(rbtn_parking.isSelected(),rbtn_wifi.isSelected()
                    ,rbtn_fitness.isSelected(),rbtn_pool.isSelected(),rbtn_concierge.isSelected(),
                    rbtn_spa.isSelected(),rbtn_room_service.isSelected());

            String hotelName = fld_hotel_name.getText();
            String hotelMail = fld_hotel_mail.getText();
            String hotelPhone = fld_hotel_phone.getText();
            String hotelAddress = fld_address.getText();
            String hotelCity = fld_hotel_city.getText();
            String district = fld_district.getText();
            String hotelStar = fld_hotel_star.getText();

            hotel.setHotel_name(hotelName);
            hotel.setHotel_mail(hotelMail);
            hotel.setHotel_city(hotelCity);
            hotel.setHotel_district(district);
            hotel.setHotel_address(hotelAddress);
            hotel.setHotel_mpno(hotelPhone);
            hotel.setHotel_star(hotelStar);


            HotelWithDetails hotelWithDetails = new HotelWithDetails(hotel,hotelDetails);
            hotelsDao.addHotel(hotelWithDetails);
            Helper.showMessage("done");
        });
    }
}
