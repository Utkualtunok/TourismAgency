package view;

import business.UserManager;
import common.Role;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserView extends Layout{
    private JPanel conteiner;
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cmb_role;
    private JButton btn_user_save;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_role;
    private User user;
    private UserManager userManager;

    public UserView(User user){
        this.user = user;
        this.userManager = new UserManager();
        this.add(conteiner);
        guiInitilaze(400,300);
        this.user = (user == null) ? new User() : user;

        for (Role role : Role.values()) {
            cmb_role.addItem(role);
        }
        if (this.user.getUser_id() != 0) {
            fld_username.setText(this.user.getUser_name());
            fld_password.setText(this.user.getUser_password());
            cmb_role.setSelectedItem(this.user.getUser_role());
        }

        btn_user_save.addActionListener(e -> {
            String username = fld_username.getText();
            String password = fld_password.getText();
            Role selectedRole = (Role) cmb_role.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                Helper.showMessage("fill");
            } else {
                this.user.setUser_name(username);
                this.user.setUser_password(password);
                this.user.setUser_role(selectedRole);

                boolean result;
                if (this.user.getUser_id() == 0) {
                    result = this.userManager.save(this.user);
                } else {
                    result = this.userManager.update(this.user);
                }
                if (result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("fail");
                }
            }
        });




    }
}
