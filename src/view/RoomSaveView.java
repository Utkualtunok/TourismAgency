package view;

import core.Helper;
import dao.HotelsDao;
import dao.PensionsDao;
import dao.RoomsDao;
import dao.SeasonDao;
import dto.RoomWithDetails;
import entity.Pension;
import entity.Season;
import entity.hotel.Hotel;
import entity.room.Room;
import entity.room.RoomDetails;

import javax.swing.*;
import java.util.ArrayList;

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

    public RoomSaveView() {
        this.add(conteiner);
        this.guiInitilaze(1500,400);
        HotelsDao hotelsDao = new HotelsDao();
        PensionsDao pensionsDao = new PensionsDao();
        SeasonDao seasonDao = new SeasonDao();

        ArrayList<Hotel> otels = hotelsDao.getAllHotels();
        ArrayList<Pension> pensions =  pensionsDao.getAllPensions();
        ArrayList<Season> seasons =  seasonDao.getAllSeasons();

        cmb_hotel.removeAllItems();
        cmb_pension.removeAllItems();
        cmb_room_type.removeAllItems();
        cmb_season.removeAllItems();

        for(int i = 0; i < otels.size(); i++){
            cmb_hotel.addItem(otels.get(i).getHotel_id() + " - " + otels.get(i).getHotel_name());
        }

        for(int i = 0; i < pensions.size(); i++){
            cmb_pension.addItem(pensions.get(i).getPension_id() + " - " + pensions.get(i).getPension_name());
        }
        for(int i = 0; i < seasons.size(); i++){
            cmb_season.addItem(seasons.get(i).getSeason_id() + " - " + seasons.get(i).getSeason_name());
        }

        cmb_room_type.addItem("Single");
        cmb_room_type.addItem("Double");
        cmb_room_type.addItem("Suit");
        cmb_room_type.addItem("Junior Suit");

        btn_room_save.addActionListener(e -> {
            Room room = new Room();
            RoomDetails roomDetails = new RoomDetails();

            String stock = fld_stock.getText();
            String adultPrice = fld_adult_price.getText();
            String childPrice = fld_child_price.getText();
            String bedCount = fld_bed_count.getText();
            String meters = fld_meters.getText();

            int hotelId = Helper.extractNumber((String) cmb_hotel.getSelectedItem());
            int pensionId = Helper.extractNumber((String) cmb_pension.getSelectedItem());
            int seasonId = Helper.extractNumber((String) cmb_season.getSelectedItem());
            String roomType = (String) cmb_room_type.getSelectedItem();

            room.setHotel_id(hotelId);
            room.setPension_id(pensionId);
            room.setSeason_id(seasonId);
            room.setRoom_type(roomType);
            room.setRoom_stock(Integer.parseInt(stock));
            room.setAdult_price(Integer.parseInt(adultPrice));
            room.setChild_price(Integer.parseInt(childPrice));

            roomDetails.setRoom_meter(Integer.parseInt(meters));
            roomDetails.setBed_count(Integer.parseInt(bedCount));
            roomDetails.setIs_tv(rbtn_tv.isSelected());
            roomDetails.setIs_minibar(rbtn_minibar.isSelected());
            roomDetails.setIs_case(rbtn_case.isSelected());
            roomDetails.setIs_console(rbtn_console.isSelected());
            roomDetails.setIs_projection(rbtn_projection.isSelected());

            RoomWithDetails roomWithDetails = new RoomWithDetails(room, roomDetails);
            RoomsDao roomsDao = new RoomsDao();
            roomsDao.addRoom(roomWithDetails);

        });
    }
}
