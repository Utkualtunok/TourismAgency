package view;

import business.UserManager;
import common.Role;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView  extends Layout {
    private JPanel cointeiner;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JLabel lbl_welcome;
    private JButton btn_quit;
    private JTabbedPane tab_menu;
    private JPanel pnl_users;
    private JTable tbl_users;
    private JScrollPane scrl_users;
    private JComboBox cmb_role;
    private JButton btn_search;
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cmb_user_role;
    private JButton btn_user_save;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_Menu;


    public AdminView() {
        this.userManager = new UserManager();
        this.user = new User();
        this.add(cointeiner);
        this.guiInitilaze(400,400);

        for (Role role : Role.values()) {
            cmb_user_role.addItem(role);
        }
        for (Role role : Role.values()) {
            cmb_role.addItem(role);
        }

        loadUserTable();
        loadUserComponent();
        btn_user_save.addActionListener(e -> {
            String username = fld_username.getText();
            String password = fld_password.getText();
            Role selectedRole = (Role) cmb_user_role.getSelectedItem();
            if (username.isEmpty() || password.isEmpty()) {
                Helper.showMessage("fill");
            } else {
                User newUser = new User();
                newUser.setUser_name(username);
                newUser.setUser_password(password);
                newUser.setUser_role(selectedRole);

               boolean isSaved = userManager.save(newUser);
               if (isSaved){
                   fld_username.setText("");
                   fld_password.setText("");
                   cmb_user_role.setSelectedIndex(-1);
                   Helper.showMessage("done");
                   loadUserTable();
               }else {
                   Helper.showMessage("fail");
               }
            }
        });
        btn_search.addActionListener(e -> {
            Role selectedRole = (Role) cmb_role.getSelectedItem(); // Seçili rolü al
            loadUserTableByRole(selectedRole); // Rol'e göre tabloyu yükle
        });
    }
    public void loadUserTableByRole(Role role) {
        Object[] col_User = {"User ID", "Kullanıcı Adı","Şifre", "Rol"};
        ArrayList<Object[]> filteredUsers = userManager.getForTableByRole(role, col_User.length);
        this.createTable(this.tmdl_user, this.tbl_users, col_User, filteredUsers);
    }

    public void loadUserTable(){
        Object[] col_User = {"User ID", "Kullanıcı Adı","Şifre","Rol"};
        ArrayList<Object[]> userList = userManager.getForTable(col_User.length);
        this.createTable(this.tmdl_user, this.tbl_users,col_User,userList);
    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_users);
        this.user_Menu = new JPopupMenu();

        this.user_Menu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(null);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        this.user_Menu.add("Güncelle").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_users, 0);
            UserView userView = new UserView(this.userManager.getById(selectedUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        this.user_Menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedUserId = this.getTableSelectedRow(tbl_users, 0);
                if (this.userManager.delete(selectedUserId)) {
                    Helper.showMessage("done");
                    loadUserTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_users.setComponentPopupMenu(user_Menu);
    }

}
