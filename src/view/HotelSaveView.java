package view;

import business.HotelsManager;
import core.Helper;
import entity.Hotel;
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
    private HotelsManager hotelManager;
    private Hotel hotel;

    public HotelSaveView(Object o) {
        this.hotel = hotel;
        this.hotelManager = new HotelsManager();
        this.add(conteiner);
        this.guiInitilaze(1000, 500);
        loadHotelAddComponent();

    }

    public void loadHotelAddComponent() {

        btn_hotel_save.addActionListener(e -> {

            // Kontrol edilecek text alanları bir diziye ekleniyor.
            JTextField[] checkFieldList = {this.fld_hotel_name, this.fld_hotel_mail, this.fld_hotel_phone, this.fld_address};

            // Eğer kontrol edilecek alanlardan biri boşsa, kullanıcıya bir uyarı mesajı gösterilir.
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMessage("fill");
            } else {

                // Eğer tüm alanlar dolu ise, yeni bir otel nesnesi oluşturulur ve bilgileri alınır.
                boolean result = true;
                Hotel hotels = new Hotel();
                hotels.setHotel_name(fld_hotel_name.getText());
                hotels.setHotel_mail(fld_hotel_mail.getText());
                hotels.setHotel_mpno(fld_hotel_phone.getText());
                hotels.setHotel_city(fld_hotel_city.getText());
                hotels.setHotel_address(fld_address.getText());
                hotels.setHotel_star(fld_hotel_star.getText());
                hotels.setCar_park(rbtn_parking.isSelected());
                hotels.setPool(rbtn_pool.isSelected());
                hotels.setFitness(rbtn_fitness.isSelected());
                hotels.setConcierge(rbtn_concierge.isSelected());
                hotels.setSpa(rbtn_spa.isSelected());
                hotels.setRoom_service(rbtn_room_service.isSelected());

                if (hotels.getHotel_id() == 0) {
                    result = this.hotelManager.save(hotels);
                    dispose();
                }
                if (result) {
                    Helper.showMessage("done");

                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
