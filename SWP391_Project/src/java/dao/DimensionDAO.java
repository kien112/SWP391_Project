/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Dimension;
import model.DimensionType;
import model.SubjectCategory;

/**
 *
 * @author 84877
 */
public class DimensionDAO extends DBContext {

    public List<Dimension> getAllDimensions() {
        ResultSet rs = null;

        List<Dimension> listC = new ArrayList<>();
        String sql = "select d.id, d.name, d.description, d.type_id, dt.name as [type]\n"
                + "from dimension d join dimension_type dt\n"
                + "on d.type_id = dt.id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DimensionType type = new DimensionType();
                type.setId(rs.getInt("type_id"));
                type.setName(rs.getString("type"));
                
                Dimension dimension = new Dimension();
                dimension.setId(rs.getInt("id"));
                dimension.setName(rs.getString("name"));
                dimension.setDescription(rs.getString("description"));
                dimension.setType(type);
                
                listC.add(dimension);
            }
            return listC;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public List<DimensionType> getDimensionTypes(){
        ResultSet rs = null;
        List<DimensionType> list = new ArrayList<>();
        String sql = "select * from dimension_type";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DimensionType type = new DimensionType();
                type.setId(rs.getInt(1));
                type.setName(rs.getString(2));
                
                list.add(type);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean addDimension(Dimension dimension) {
    String sql = "insert into dimension(name, type_id, description) values(?, ?, ?)";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, dimension.getName());
        ps.setInt(2, dimension.getType().getId());
        ps.setString(3, dimension.getDescription());
        int result = ps.executeUpdate();
        return result > 0;
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return false;
}

public boolean updateDimension(Dimension dimension) {
    String sql = "update dimension set name = ?, type_id = ?, description = ? where id = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, dimension.getName());
        ps.setInt(2, dimension.getType().getId());
        ps.setString(3, dimension.getDescription());
        ps.setInt(4, dimension.getId());
        int result = ps.executeUpdate();
        return result > 0;
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return false;
}

public boolean deleteDimension(int id) {
    String sql = "delete from dimension where id = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int result = ps.executeUpdate();
        return result > 0;
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return false;
}
    
    public static void main(String[] args) {
        DimensionDAO dao = new DimensionDAO();
        for (DimensionType allDimension : dao.getDimensionTypes()) {
            System.out.println(allDimension);
        }
    }

}
