package view;

import business.HotelsManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Pension;
import entity.Season;
import entity.Hotel;
import entity.Room;

import javax.swing.*;

public class RoomSaveView extends Layout {
    private JPanel conteiner;
    private JComboBox cmb_hotel;
    private JLabel lbl_hotel;
    private JComboBox cmb_pension;
    private JLabel lbl_pension;
    private JComboBox cmb_room_type;
    private JLabel lbl_room_type;
    private JTextField fld_stock;
    private JLabel lbl_stock;
    private JTextField fld_adult_price;
    private JLabel lbl_adult_price;
    private JTextField fld_child_price;
    private JLabel lbl_bed_capacity;
    private JTextField fld_bed_count;
    private JTextField fld_meters;
    private JLabel lbl_meters;
    private JLabel lbl_tv;
    private JRadioButton rbtn_tv;
    private JLabel lbl_minibar;
    private JRadioButton rbtn_minibar;
    private JRadioButton rbtn_console;
    private JLabel lbl_console;
    private JLabel lbl_case;
    private JRadioButton rbtn_case;
    private JRadioButton rbtn_projection;
    private JLabel lbl_projection;
    private JButton btn_room_save;
    private JComboBox cmb_season;
    private RoomManager roomManager;
    private Room room;
    private HotelsManager hotelsManager;
    private SeasonManager seasonManager;
    private PensionManager pencionManager;

    //Yeni oda eklemek için kullanılan arayüz.

    public RoomSaveView(Object o) {
        this.add(conteiner);
        this.guiInitilaze(1000,600);
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager(null);
        this.pencionManager = new PensionManager(null);
        this.hotelsManager = new HotelsManager();
        this.room = new Room();
        this.room = room;
        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));

        //Combobox'a bilgiler doldurulur
        int counter = 0;
        for (Hotel hotel : this.hotelsManager.findAll()) {
            if (counter == 0) {
                getPencionByHotel(hotel.getHotel_id());
                getSeasonByHotel(hotel.getHotel_id());
            }
            cmb_hotel.addItem(hotel.getComboItem());
            counter++;
        }

        btn_room_save.addActionListener(e -> {

            JTextField[] checkFieldList = {this.fld_stock, this.fld_adult_price, this.fld_child_price, this.fld_bed_count, this.fld_meters};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMessage("fill");
            } else {
                boolean result = true;
                Room room = new Room();

                ComboItem selectedOtelInfo = (ComboItem) cmb_hotel.getSelectedItem();
                room.setHotel_id(selectedOtelInfo.getKey());

                ComboItem selectedSeasonInfo = (ComboItem) cmb_season.getSelectedItem();
                room.setSeason_id(selectedSeasonInfo.getKey());

                ComboItem selectedPensionInfo = (ComboItem) cmb_pension.getSelectedItem();
                room.setPension_id(selectedPensionInfo.getKey());

                //Set etme işlemleri
                this.room.setRoom_stock(Integer.parseInt(fld_stock.getText()));
                this.room.setAdult_price(Integer.parseInt(fld_adult_price.getText()));
                this.room.setChild_price(Integer.parseInt(fld_child_price.getText()));
                this.room.setRoom_bed_capacity(Integer.parseInt(fld_bed_count.getText()));
                this.room.setRoom_meter(Integer.parseInt(fld_meters.getText()));
                this.room.setRoom_type(String.valueOf((Room.RoomType) cmb_room_type.getSelectedItem()));
                this.room.setRoom_tv(rbtn_tv.isSelected());
                this.room.setRoom_minibar(rbtn_minibar.isSelected());
                this.room.setRoom_console(rbtn_console.isSelected());
                this.room.setRoom_projection(rbtn_projection.isSelected());
                this.room.setRoom_case(rbtn_case.isSelected());


                // Yeni bir oda ekleniyorsa, odanın bilgileri kaydedilir.
                if (room.getRoom_id() == 0) {
                    result = this.roomManager.save(room);
                    dispose();

                }
                if (result) {
                    Helper.showMessage("done");


                } else {
                    Helper.showMessage("error");
                }

            }
        });

        cmb_hotel.addActionListener(e -> {
            ComboItem item = (ComboItem) cmb_hotel.getSelectedItem();
            // Seçilen otel bilgisine göre pansiyon ve sezon bilgileri güncellenir.
            getPencionByHotel(item.getKey());
            getSeasonByHotel(item.getKey());

        });
    }
    //Pansiyonlar combobox'a eklenir
    private void getPencionByHotel(int hotel_id) {
        for (Pension pension : this.pencionManager.findByHotelId(hotel_id)) {
            cmb_pension.addItem((pension.getComboItem()));
        }
    }
    //Sezonlar combobox'a eklenir
    private void getSeasonByHotel(int hotel_id) {
        for (Season season : this.seasonManager.findByHotelId(hotel_id)) {
            cmb_season.addItem((season.getComboItem()));
        }
    }
}
