package view;

import business.HotelsManager;
import dao.HotelsDao;
import dao.ReservationDao;
import dto.ReservationDto;
import dto.ReservationModelDto;
import entity.room.Room;
import entity.room.RoomDetails;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends Layout {
    private JPanel conteiner;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JButton btn_hotel_add;
    private JButton btn_quit;
    private JPanel pnl_room;
    private JTextField fld_hotel_name;
    private JLabel lbl_hotel_name;
    private JLabel lbl_city;
    private JTextField fld_city;
    private JLabel lbl_in_date;
    private JTextField fld_in_date;
    private JLabel lbl_out_date;
    private JTextField fld_out_date;
    private JLabel lbl_adult_count;
    private JTextField fld_adult_count;
    private JLabel lbl_child_count;
    private JTextField fld_child_count;
    private JButton btn_search;
    private JScrollPane scrl_room;
    private JTable tbl_room;
    private JPanel pnl_rezervation;
    private JScrollPane scrl_reservation;
    private JTable tbl_reservation;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private JPopupMenu hotel_Menu;
    private JPopupMenu room_Menu;
    private Room room;
    private RoomDetails roomDetails;

    public EmployeeView() {
        this.add(conteiner);
        this.guiInitilaze(1400,300);
        this.room = new Room();
        this.roomDetails = new RoomDetails();
        HotelsDao hotelsDao = new HotelsDao();
        HotelsManager hotelsManager = new HotelsManager();
        ArrayList<Object[]> hotelsForTable = hotelsManager.getForTable(8, hotelsDao.getAllHotels());

        btn_hotel_add.addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView();
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    reloadHotelTable();
                }
            });
            hotelSaveView.setVisible(true);
        });

        //Otel tab menu
        loadHotelTable(hotelsForTable);
        loadHotelComponent();
        reloadHotelTable();


        btn_search.addActionListener(e -> {
            String hotelName = fld_hotel_name.getText();
            String city = fld_city.getText();
            String date = fld_in_date.getText();
            String outDate = fld_out_date.getText();
            String childCount = fld_child_count.getText();
            String adultCount = fld_adult_count.getText();


            ReservationDto reservationDto = new ReservationDto(hotelName,city,date,outDate,adultCount,childCount);

            ReservationDao reservationDao = new ReservationDao();
            ArrayList<Object[]> roomList = reservationDao.searchReservation(reservationDto);
            System.out.println(roomList.size());
            loadRoomTable(roomList);

        });
    }
    private void reloadHotelTable() {
        HotelsManager hotelsManager = new HotelsManager();
        HotelsDao hotelsDao = new HotelsDao();
        ArrayList<Object[]> hotelsForTable = hotelsManager.getForTable(8, hotelsDao.getAllHotels());
        loadHotelTable(hotelsForTable);
    }
    private void loadHotelTable(ArrayList<Object[]> hotelList){
        Object[] col_hotel_list = {"ID", "Otel Adı", "Şehir", "İlçe", "Adres", "Mail", "Yıldız", "Numara"};
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel_list,hotelList);
    }
    private void loadHotelComponent(){
        tableRowSelect(this.tbl_hotel);
        this.hotel_Menu = new JPopupMenu();
        this.hotel_Menu.add("Oda Ekle").addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView();
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    loadHotelComponent();
                }
            });
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_Menu);
    }
    private void loadRoomTable(ArrayList<Object[]> roomList){
        Object[] col_room_list = {"Oda ID","Oda Tipi","Çocuk Fiyatı","Yetişkin Fiyatı", "Yatak Sayısı", "Tv", "Minibar", "Konsol", "Kasa","Projeksiyon"};
        createTable(this.tmdl_room, this.tbl_room, col_room_list,roomList);
    }

}
