package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PensionsDao {

    private Connection connection;

    public PensionsDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Pension> getAllPensions() {
        ArrayList<Pension> pensions = new ArrayList<>();

        String query = "SELECT * FROM public.pension ORDER BY pension_id ASC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int pensionId = rs.getInt("pension_id");
                String pensionName = rs.getString("pension_name");

                Pension pension = new Pension();
                pension.setPension_id(pensionId);
                pension.setPension_name(pensionName);

                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }

}
