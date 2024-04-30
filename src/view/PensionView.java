package view;

import business.HotelsManager;
import business.PensionManager;
import entity.Hotel;

import javax.swing.*;

public class PensionView extends Layout {
    private JPanel conteiner;
    private JComboBox cmb_pension;
    private JButton btn_pension_save;
    private Hotel hotel;
    private HotelsManager hotelManager;
    private PensionManager pensionManager;

    public PensionView(Hotel hotel) {
        this.pensionManager = new PensionManager(hotel);
        this.hotelManager = new HotelsManager();
        this.hotel = hotel;
        this.add(conteiner);
        this.guiInitilaze(400, 350);

        btn_pension_save.addActionListener(e -> {

            // Seçilen pansiyon tipi alınıp ekrana yazdırılıyor.
            this.cmb_pension.getSelectedItem().toString();
            pensionManager.savePencion(hotel, String.valueOf(cmb_pension.getSelectedItem()));
            dispose();
        });
    }
}
