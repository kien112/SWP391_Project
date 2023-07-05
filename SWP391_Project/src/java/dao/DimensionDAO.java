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
        String sql = "select d.id, d.name, d.description, dt.name as [type]\n"
                + "from dimension d join dimension_type dt\n"
                + "on d.type_id = dt.id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DimensionType type = new DimensionType();
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
    
    
    public static void main(String[] args) {
        DimensionDAO dao = new DimensionDAO();
        for (Dimension allDimension : dao.getAllDimensions()) {
            System.out.println(allDimension);
        }
    }

}
