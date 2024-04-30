import core.Helper;
import view.*;

public class App {
    public static void main(String[] args) {
        //Uygulama teması
        Helper.setTheme();

        //Kullanıcı giriş sayfası
       // EmployeeView employeeView = new EmployeeView();

        //Uygulama başlangıç sayfası
       LoginView loginView = new LoginView();

    }
}
