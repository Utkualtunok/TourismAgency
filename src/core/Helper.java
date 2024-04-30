package core;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static void setTheme(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }catch (Exception e ){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }
    public static void showMessage(String str){
        optionPaneTR();
        String msg;
        String title;

        switch (str){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz.";
                title = "Hata!";
                break;
            case "fail":
                msg = "Kullanıcı kaydı yapılamadı!";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem başarılı.";
                title = "Sonuç";
                break;
            case "notFound":
                msg = "Kayıt bulunamadı.";
                title = "Bulunamadı.";
                break;
            case "error":
                msg = "Hatalı işlem yaptınız.";
                title = "Hata!";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null,msg, title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static int getLocationPoint (String type, Dimension size){
        switch (type){
            case "x":
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.getSize().width) / 2;
            case "y":
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.getSize().height) / 2;
            default:
                return 0;
        }
    }

    public static boolean confirm(String str){
        optionPaneTR();
        String msg;
        if (str.equals("sure")){
            msg = "Bu işlemi yapmak istediğine emin misin ?";
        }else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?",JOptionPane.YES_NO_OPTION) == 0;

    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for (JTextField field : fieldList){
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }
    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");

    }

}
