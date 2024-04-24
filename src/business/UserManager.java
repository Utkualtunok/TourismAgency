package business;

import common.Role;
import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;


    public UserManager() {
        this.userDao = new UserDao();
    }
    public User getById(int id){return this.userDao.getById(id);}
    public User findByLogin(String username , String password){
        return this.userDao.findByLogin(username,password);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : this.userDao.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getUser_id();
            rowObject[i++] = user.getUser_name();
            rowObject[i++] = user.getUser_password();
            rowObject[i++] = user.getUser_role();
            userRowList.add(rowObject);
        }
        return userRowList;
    }
    public boolean save (User user){
        if (this.userDao.getById(user.getUser_id()) != null){
            Helper.showMessage("error");
            return false;
        }
        return this.userDao.save(user);
    }
    public boolean update(User user){
        if (this.userDao.getById(user.getUser_id()) == null){
            Helper.showMessage(user.getUser_id() + "Kayıtlı kullanıcı bulunamadı.");
            return false;
        }
        return this.userDao.update(user);
    }
    public boolean delete(int id){
        if (this.userDao.getById(id) == null){
            Helper.showMessage(id + "ID Kayıtlı kullanıcı bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }
    public ArrayList<Object[]> getForTableByRole(Role role, int columnCount) {
        ArrayList<Object[]> result = new ArrayList<>();
        for (User user : this.userDao.findAll()) {
            if (user.getUser_role() == role) {
                Object[] userRow = new Object[columnCount];
                userRow[0] = user.getUser_id();
                userRow[1] = user.getUser_name();
                userRow[2] = user.getUser_role().toString();
                result.add(userRow);
            }
        }
        return result;
    }

}
