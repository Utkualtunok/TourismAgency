package view;

import business.*;
import core.Helper;
import dao.HotelsDao;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

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
    private JTable tbl_season;
    private JButton btn_room_add;
    private JButton btn_reset;
    private JTable tbl_pension;
    private JScrollPane scrl_pension;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private DefaultTableModel tmdl_pencion = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private JPopupMenu hotel_Menu;
    private JPopupMenu room_Menu;
    private JPopupMenu reservation_Menu;
    private Room room;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private HotelsManager hotelsManager;
    Object[] col_room_list;
    Object[] col_hotel;
    Object[] col_reservation;
    Object[] col_pension;
    Object[] col_season;
    private RoomSaveView roomSaveView;

    public EmployeeView() {
        this.add(conteiner);
        this.guiInitilaze(1200,600);
        this.room = new Room();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.hotelsManager = new HotelsManager();
        this.pensionManager = new PensionManager(null);
        this.seasonManager = new SeasonManager(null);
        HotelsDao hotelsDao = new HotelsDao();
        HotelsManager hotelsManager = new HotelsManager();

        loadHotelComponent();
        loadHotelTable(null);
        loadPencionTable(null);
        loadSeasonTable(null);
        loadRoomTable(null);
        loadRoomComponent();
        loadReservationTable(null);
        loadReservationComponent();


        btn_quit.addActionListener(e -> {
            dispose();
            LoginView LoginView = new LoginView();
        });
    }
    public void loadHotelComponent(){
        // Otel tablosunu oluşturup güncelleme
        col_hotel = new Object[] {"Otel ID", "Otel Adı", "Otel Şehri","Otel Adresi", "Otel Maili", "Otel Telefonu", "Otel Yıldızı", "Otel Otoparkı", "Otel Wifi", "Otel Havuzu", "Otel Spor Salonu", "Otel Kapı Hizmeti", "Otel Spa", "Otel Oda Servisi"};
        ArrayList<Hotel> hotelList = this.hotelsManager.findAll();
        tmdl_hotel.setColumnIdentifiers(col_hotel);
        for (Hotel hotel : hotelList) {
            Object[] obj = {hotel.getHotel_id(), hotel.getHotel_name(),hotel.getHotel_city(),hotel.getHotel_address(), hotel.getHotel_mail(), hotel.getHotel_mpno(), hotel.getHotel_star(), hotel.isCar_park(), hotel.isWifi(), hotel.isPool(), hotel.isFitness(), hotel.isConcierge(), hotel.isSpa(), hotel.isRoom_service()};
            tmdl_hotel.addRow(obj);
        }
        this.tbl_hotel.setModel(tmdl_hotel);
        this.tbl_hotel.getTableHeader().setReorderingAllowed(false);
        this.tbl_hotel.setEnabled(false);

        this.tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        this.hotel_Menu = new JPopupMenu();
        this.hotel_Menu.add("Pansiyon Tipi Ekle").addActionListener(e -> {
            int selectedId = getTableSelectedRow(tbl_hotel, 0);
            PensionView pensionView = new PensionView(hotelsManager.getById(selectedId));
            pensionView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPencionTable(null);
                }
            }));
            loadPencionTable(null);
        });

        this.hotel_Menu.add("Sezon Ekle").addActionListener(e -> {
            int selectedId = getTableSelectedRow(tbl_hotel, 0);
            SeasonView seasonView = new SeasonView(hotelsManager.getById(selectedId));
            seasonView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);
                }
            }));
            loadSeasonTable(null);
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_Menu);
        loadPencionTable(null);
        loadSeasonTable(null);

        btn_hotel_add.addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView(null);
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });
    }

    public void loadPencionTable(ArrayList<Object[]> pensionList) {
        col_pension = new Object[]{"ID", "Otel ID", "Pansiyon Tipi"};
        if (pensionList == null) {
            pensionList = this.pensionManager.getForTable(this.col_pension.length, this.pensionManager.findAll());
        }
        createTable(this.tmdl_pencion, this.tbl_pension, col_pension, pensionList);

    }
    public void loadSeasonTable(ArrayList<Object[]> seasonList) {
        col_season = new Object[]{"ID", "Otel ID", "Giriş Tarihi", "Çıkış Tarihi"};
        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(this.col_season.length, this.seasonManager.findAll());
        }
        createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        col_hotel = new Object[]{"Otel ID", "Otel Adı", "Otel Şehri","Otel Adresi", "Otel Maili", "Otel Telefonu", "Otel Yıldızı", "Otel Otoparkı", "Otel Wifi", "Otel Havuzu", "Otel Spor Salonu", "Concierge", "Otel Spa", "Otel Oda Servisi"};
        if (hotelList == null) {
            hotelList = this.hotelsManager.getForTable(this.col_hotel.length, this.hotelsManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }
    private void loadRoomTable(ArrayList<Object[]> roomList){
        col_room_list = new Object[] {"Oda ID","Otel ID","Pansiyon ID","Sezon ID","Oda Tipi","Oda Stoğu","Yetişkin Fiyatı","Çocuk Fiyatı","Yatak kapasitesi","Metrekare", "Tv", "Minibar", "Konsol", "Kasa","Projeksiyon"};
        if (roomList == null) {
          roomList = this.roomManager.getForTable(this.col_room_list.length, this.roomManager.findAll());
        }else if (roomList.size() <10) {
            System.err.println("Room listesi beklenenden küçük.");
        }
        createTable(this.tmdl_room, this.tbl_room, col_room_list,roomList);
    }
    private void loadRoomComponent(){
        btn_room_add.addActionListener(e -> {

            // Yeni bir RoomSaveView penceresi oluşturuluyor.
            RoomSaveView roomSaveView = new RoomSaveView(null);

            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadRoomTable(null);
                    loadReservationTable(null);
                }
            });
            loadReservationTable(null);
        });

        this.tbl_room.setModel(tmdl_room);
        this.tbl_room.getTableHeader().setReorderingAllowed(false);
        this.tbl_room.setEnabled(false);

        this.tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_room.rowAtPoint(e.getPoint());
                tbl_room.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        tableRowSelect(this.tbl_room);

        this.room_Menu = new JPopupMenu();
        this.room_Menu.add("Rezervasyon Yap").addActionListener(e -> {

            int selectedRoomId = getTableSelectedRow(this.tbl_room,0);
            ReservationView reservationView = new ReservationView(
            new Reservation(),
                    roomManager.getById(selectedRoomId),
                    this.fld_in_date,
                    this.fld_out_date,
                    this.fld_adult_count,
                    this.fld_child_count);
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadReservationTable(null);
                }
            });
            loadRoomTable(null);
            loadReservationTable(null);
        });

        this.tbl_room.setComponentPopupMenu(room_Menu);

        loadPencionTable(null);
        loadSeasonTable(null);

        btn_search.addActionListener(e -> {
            String adultPriceText = (fld_adult_count.getText());
            String childPriceText = (fld_child_count.getText());

            if (!isNumeric(adultPriceText)) {
                Helper.showMessage(adultPriceText + " Geçerli bir sayı değil.");
                return;
            }
            if (!isNumeric(childPriceText)) {
                Helper.showMessage(childPriceText + " Geçerli bir sayı değil.");
                return;
            }
            ArrayList<Room> roomList = this.roomManager.searchForRoom(
                    // Oda arama işlemi yapılıyor.
                    fld_hotel_name.getText(),
                    fld_city.getText(),
                    fld_in_date.getText(),
                    fld_out_date.getText(),
                    fld_adult_count.getText(),
                    fld_child_count.getText()
            );
            ArrayList<Object[]> roomRow = this.roomManager.getForTable(this.col_room_list.length, roomList);
            loadRoomTable(roomRow);
        });
    }
    public void loadReservationTable(ArrayList<Object[]> reservationList) {
         col_reservation = new Object[]{"ID", "Oda ID", "Giriş Tarihi", "Çıkış Tarihi", "Toplam Tutar", "Misafir Sayısı", "Misafir Adı", "Misafir Kimlik No", "Mail", "Telefon"};

        // Eğer reservationList null ise, tüm rezervasyonları içeren bir liste oluşturuluyor.
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }

        this.createTable(this.tmdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }
    public void loadReservationComponent() {

        this.tbl_reservation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_reservation.rowAtPoint(e.getPoint());
                tbl_reservation.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        tableRowSelect(tbl_reservation);
        this.reservation_Menu = new JPopupMenu();

        this.reservation_Menu.add("Güncelle").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);

            // Seçilen rezervasyon ve odanın bilgileri alınıyor.
            Reservation selectReservation = this.reservationManager.getById(selectReservationId);
            int selectRoomId = selectReservation.getRoom_id();
            Room selectRoom = this.roomManager.getById(selectRoomId);

            // ReservationView penceresi oluşturuluyor.
            ReservationView reservationView = new ReservationView(
                    selectReservation,
                    selectRoom,
                    (JFormattedTextField) this.fld_in_date,
                    (JFormattedTextField) this.fld_out_date,
                    this.fld_adult_count,
                    this.fld_child_count);

            reservationView.addWindowListener((new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);
                }
            }));
            loadReservationTable(null);
        });

        this.tbl_reservation.setComponentPopupMenu(reservation_Menu);
        this.reservation_Menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                // Seçilen rezervasyonun ID'si ve oda ID'si alınıyor.
                int selectResId = this.getTableSelectedRow(tbl_reservation, 0);
                int selectRoomId = reservationManager.getById(selectResId).getRoom_id();
                Room selectRoom = roomManager.getById(selectRoomId);

                // Odanın stok miktarı bir arttırılıyor.
                selectRoom.setRoom_stock(selectRoom.getRoom_stock() + 1);
                roomManager.updateStock(selectRoom);
                if (this.reservationManager.delete(selectResId)) {
                    Helper.showMessage("done");

                    loadReservationTable(null);
                    loadRoomTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        // Rezervasyon tablosuna sağ tık menüsünü ekliyor.
        this.tbl_reservation.setComponentPopupMenu(reservation_Menu);
        loadReservationTable(null);
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
    private void createUIComponents() throws ParseException {
        this.fld_in_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_in_date.setText("01/01/2024");
        this.fld_out_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_out_date.setText("01/06/2024");
    }
}
