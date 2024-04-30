    package business;

    import core.Db;
    import core.Helper;
    import dao.RoomsDao;
    import entity.Hotel;
    import entity.Room;

    import java.sql.Connection;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;

    public class RoomManager {
        private Connection connection;
    private final RoomsDao roomsDao;
        private Hotel hotel;
        private HotelsManager hotelManager;
        private Room room;

    public RoomManager() {
        this.roomsDao = new RoomsDao();
        this.connection = Db.getInstance();
        this.hotel = new Hotel();
        this.hotelManager = new HotelsManager();
        this.room = new Room();
    }
        //Oda Id'sine göre odayı getiren metot
        public Room getById(int id){return this.roomsDao.getById(id);}

        //Stok güncellemesi yapan metot
        public boolean updateStock(Room room) {
            return this.roomsDao.updateStock(room);
        }

        //Tüm odaları getiren metıt
        public ArrayList<Room> findAll() {return this.roomsDao.findAll();}


        //Tablo için veri yapışı oluşturuluyor
        public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
            ArrayList<Object[]> roomObjList = new ArrayList<>();
            for (Room obj : rooms) {
                Object[] rowObject = new Object[size];
                int i = 0;
                rowObject[i++] = obj.getRoom_id();
                rowObject[i++] = obj.getHotel_id();
                rowObject[i++] = obj.getPension_id();
                rowObject[i++] = obj.getSeason_id();
                rowObject[i++] = obj.getRoom_type();
                rowObject[i++] = obj.getRoom_stock();
                rowObject[i++] = obj.getAdult_price();
                rowObject[i++] = obj.getChild_price();
                rowObject[i++] = obj.getRoom_bed_capacity();
                rowObject[i++] = obj.getRoom_meter();
                rowObject[i++] = obj.isRoom_tv();
                rowObject[i++] = obj.isRoom_minibar();
                rowObject[i++] = obj.isRoom_console();
                rowObject[i++] = obj.isRoom_projection();
                rowObject[i++] = obj.isRoom_case();
                roomObjList.add(rowObject);
            }
            return roomObjList;
        }
        //Oda kaydı
        public boolean save(Room room) {
            if (room.getRoom_id() != 0) {
                Helper.showMessage("error");

            }
            return this.roomsDao.save(room);
        }
        public ArrayList<Room> searchForRoom(String hotel_name, String hotel_city, String start_date, String finish_date, String adult_num, String child_num) {

            // SQL sorgusunu oluştur
            String query = "SELECT * FROM room r " +
                    "JOIN hotel h ON r.hotel_id = h.hotel_id " +
                    "LEFT JOIN season s ON r.season_id = s.season_id";

            // WHERE ve JOIN ON şartlarını tutacak ArrayList'ler
            ArrayList<String> where = new ArrayList<>();
            ArrayList<String> joinWhere = new ArrayList<>();

            // Tarih formatını uygun formata çevirme işlemi
            start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            finish_date = LocalDate.parse(finish_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

            // Otel adı şartı ekle
            if (hotel_name != null) {
                where.add("h.hotel_name ILIKE '%" + hotel_name + "%'");
            }

            // Otelin şehirlerini şartı ekle
            if (hotel_city != null) {
                where.add("h.hotel_city ILIKE '%" + hotel_city + "%'");
            }

            // Yetişkin ve çocuk sayısına göre şartları ekle
            if (adult_num != null && !adult_num.isEmpty() && child_num != null && !child_num.isEmpty()) {
                try {
                    int adultNum = Integer.parseInt(adult_num);
                    int childNum = Integer.parseInt(child_num);
                    int total_person = adultNum + childNum;
                    where.add("r.room_bed_capacity >= '" + (total_person) + "'");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            where.add("(s.season_srt_date <= '" + start_date + "')");
            where.add("(s.season_fns_date >= '" + finish_date + "')");

            where.add("r.room_stock > 0");

            String whereStr = String.join(" AND ", where);
            String joinStr = String.join(" AND ", joinWhere);

            if (joinStr.length() > 0) {
                query += " ON " + joinStr;
            }
            if (whereStr.length() > 0) {
                query += " WHERE " + whereStr;
            }

            // Oluşturulan sorguyu ekrana yazdır (opsiyonel, sorguyu kontrol etmek için)
            System.out.println(query);

            // Oluşturulan sorguya göre odaları getir
            return this.roomsDao.selectByQuery(query);
        }

    }
