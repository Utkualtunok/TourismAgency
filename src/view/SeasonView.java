package view;

import business.HotelsManager;
import business.SeasonManager;
import entity.Hotel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel conteiner;
    private JButton btn_season_save;
    private JLabel lbl_start_date;
    private JLabel lbl_finish_date;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_finish_date;
    private Hotel hotel;
    private HotelsManager hotelManager;
    private SeasonManager seasonManager;

    public SeasonView(Hotel hotel) {
        this.seasonManager = new SeasonManager(null);
        this.hotelManager = new HotelsManager();
        this.hotel = hotel;
        this.add(conteiner);
        this.guiInitilaze(400, 350);

        // Otele ait sezon bilgileri kaydedilir.
        btn_season_save.addActionListener(e -> {
            LocalDate convertedSeaonStrtDate = LocalDate.parse(this.fld_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate convertedSeaonFnshDate = LocalDate.parse(this.fld_finish_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            seasonManager.saveSeason(hotel, convertedSeaonStrtDate, convertedSeaonFnshDate);
            dispose();
        });
    }
    private void createUIComponents() throws ParseException {
        this.fld_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));

    }
}
