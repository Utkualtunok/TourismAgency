package view;

import business.UserManager;
import common.Role;
import core.Helper;
import entity.User;

import javax.swing.*;

//Uygulama giriş ekranı burada girilen bilgilere göre Role göre sayfa yönlendirmesi yapılıyor.

public class LoginView extends Layout {
    private JTextField fld_username;
    private JTextField fld_password;
    private JButton btn_login;
    private JPanel conteiner;
    private final UserManager userManager;

    public LoginView(){
        this.userManager = new UserManager();
        this.add(conteiner);
        this.guiInitilaze(400,400);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMessage("fill");
            }else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser != null) {
                    if (loginUser.getUser_role() == Role.ADMIN) {
                        new AdminView().setVisible(true);
                    } else if (loginUser.getUser_role() == Role.EMPLOYEE) {
                        new EmployeeView().setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Geçersiz kimlik bilgileri!");
                }
            }
        });
    }
}
