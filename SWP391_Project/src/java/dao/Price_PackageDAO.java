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
import model.PricePackage;

/**
 *
 * @author admin
 */
public class Price_PackageDAO extends DBContext {

    public List<PricePackage> findAll() {
        try {
            List<PricePackage> price_packages = new ArrayList<>();
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
                PricePackage price_package = new PricePackage(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getBoolean(5));
                price_packages.add(price_package);
            }
            return price_packages;

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addNewPricePackage(PricePackage p) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "insert into "
                    + "price_package(duration, price, sale, [status])\n"
                    + "values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getDuration());
            ps.setDouble(2, p.getPrice());
            ps.setDouble(3, p.getSale());
            ps.setBoolean(4, p.isStatus());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePricePackage(PricePackage p) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "update price_package set duration = ?, \n"
                    + "price = ?, sale = ?, status = ?\n"
                    + "where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getDuration());
            ps.setDouble(2, p.getPrice());
            ps.setDouble(3, p.getSale());
            ps.setBoolean(4, p.isStatus());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void changeStatusPricePackage(int pid) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "update price_package set status = \n"
                    + "case\n"
                    + "when status=1 then 0\n"
                    + "else  1\n"
                    + "end\n"
                    + "where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
