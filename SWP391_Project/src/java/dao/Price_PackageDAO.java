/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Price_package;

/**
 *
 * @author admin
 */
public class Price_PackageDAO extends DBContext {

    public List<Price_package> findAll() {
        try {
            List<Price_package> price_packages = new ArrayList<>();
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT [id]\n"
                    + "      ,[duration]\n"
                    + "      ,[price]\n"
                    + "      ,[sale]\n"
                    + "      ,[status]\n"
                    + "  FROM [price_package]";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Price_package price_package = new Price_package(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getBoolean(5));
                price_packages.add(price_package);
            }
            return price_packages;

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}