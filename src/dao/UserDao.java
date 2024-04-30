package dao;

import common.Role;
import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection conn;

    public UserDao() {
        this.conn = Db.getInstance();
    }

    //Tüm kullanıcıları getiren metot
    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    //Kullanıcı kontrolu yapılan metot
    public User findByLogin(String username, String password){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    //Verilerin modele dönüştüren metot
    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setUser_id(rs.getInt("user_id"));
        obj.setUser_name(rs.getString("user_name"));
        obj.setUser_password(rs.getString("user_password"));
        obj.setUser_role(Role.valueOf(rs.getString("user_role")));
        return obj;
    }

    //Seçilen Id ye göre user getiren metot
    public User getById(int id) {
        User user = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id); //
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                user = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //Kullanıcı kaydı
    public boolean save(User user) {
        String query = "INSERT INTO public.user " +
                "(" +
                "user_name," +
                "user_password," +
                "user_role" +
                ")" +
                " VALUES (?,?,?)";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, user.getUser_name());
            pr.setString(2, user.getUser_password());
            pr.setString(3, String.valueOf(user.getUser_role()));

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Kullanıcı bilgileri güncelleme
    public boolean update(User user) {
        String query = "UPDATE public.user SET " +
                "user_name = ?, " +
                "user_password = ?, " +
                "user_role = ? " +
                "WHERE id = ?";

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, user.getUser_name());
            pr.setString(2, user.getUser_password());
            pr.setString(3, String.valueOf(user.getUser_role()));
            pr.setInt(4, user.getUser_id());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //Silme işlemi
    public boolean delete(int user_id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1,user_id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }




}
