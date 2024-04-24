package business;

import entity.hotel.Hotel;

import java.util.ArrayList;

public class HotelsManager {

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> cars){
        ArrayList<Object[]> carList = new ArrayList<>();
        for (Hotel obj : cars) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getHotel_name();
            rowObject[i++] = obj.getHotel_city();
            rowObject[i++] = obj.getHotel_district();
            rowObject[i++] = obj.getHotel_address();
            rowObject[i++] = obj.getHotel_mail();
            rowObject[i++] = obj.getHotel_star();
            rowObject[i++] = obj.getHotel_mpno();
            carList.add(rowObject);
        }
        return carList;
    }
}
