package dao;

import java.sql.Connection;
import dao.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.CourseCategory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class courseDAO extends DBContext {

    public List<Course> getAllCourse() {
        ResultSet rs = null;

        List<Course> listC = new ArrayList<>();
        String sql = "select * from Course";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(6),
                        rs.getString(9)));
            }
        } catch (Exception e) {
        }
        return listC;
    }

    public List<Course> getAllCourseByEmail(String email) {
        ResultSet rs = null;

        List<Course> listC = new ArrayList<>();
        String sql = "select c.* from course c\n"
                + "left join registrations r\n"
                + "on r.course_id = c.course_id\n"
                + "where r.email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(6),
                        rs.getString(9)));
            }
        } catch (Exception e) {
        }
        return listC;
    }

    public List<Course> getAllCourseList() {
        ResultSet rs = null;

        List<Course> cList = new ArrayList<>();
        String sql = "select * from Course";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getInt(1),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(9),
                        rs.getDate(10)
                );
                cList.add(c);
            }
        } catch (Exception e) {
        }
        return cList;
    }

    public List<Course> searchCourseByName(String searchCourse) {
        ResultSet rs = null;

        List<Course> listC = new ArrayList<>();
        String sql = "select * from Course where [name] like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchCourse + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(6),
                        rs.getString(9)));
            }
        } catch (Exception e) {
        }
        return listC;
    }

    public List<Course> getListCourses(String search, String cateId, String sortType, String index) {
        int curIndex = Integer.valueOf(index);
        String orderBy = "order by c.[course_id] asc";
        switch (sortType) {
            case "1":
                orderBy = "order by c.[name] asc";
                break;
            case "2":
                orderBy = "order by c.[name] desc";
                break;
            case "3":
                orderBy = "order by c.[course_id] desc";
                break;
        }
        List<Course> listB = new ArrayList<>();
        String sql = "SELECT *  FROM [Course] c join CategoryCourse cc on c.cate_id = cc.id \n"
                + "where c.[name] like '%" + search + "% ' ";
        if (!cateId.isEmpty()) {
            sql += " and c.cate_id = " + cateId + " ";
        }
        sql += orderBy
                + " OFFSET " + (curIndex - 1) * 9 + " ROWS FETCH NEXT 9  ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course b = new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getInt(11));
                b.setCateName(rs.getString(13));
                b.setOriginal_priceString(rs.getString(4));
                b.setSale_priceString(rs.getString(5));
                listB.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listB;
    }

    public List<Course> getTop3ListCourses() {
        String orderBy = "order by c.[course_id] desc";

        List<Course> listB = new ArrayList<>();
        String sql = "SELECT TOP (3) *  FROM [Course] c join CategoryCourse cc on c.cate_id = cc.id \n"
                + orderBy;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course b = new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getInt(11));
                b.setCateName(rs.getString(13));
                b.setOriginal_priceString(rs.getString(4));
                b.setSale_priceString(rs.getString(5));
                listB.add(b);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return listB;
    }

    public int countCourses(String search, String cateId) {
        String sql = "SELECT *  FROM [Course] c join CategoryCourse cc on c.cate_id = cc.id \n"
                + "where c.[name] like '%" + search + "% ";
        if (!cateId.isEmpty()) {
            sql += " and c.cate_id = " + cateId + " ";
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<CourseCategory> getBlogCategories() {
        List<CourseCategory> listB = new ArrayList<>();
        String sql = "  select * from CategoryCourse";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CourseCategory b = new CourseCategory(rs.getInt(1),
                        rs.getString(2));
                listB.add(b);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listB;
    }

    public static void main(String[] args) {
//        courseDAO dao = new courseDAO();
//        Course course = new Course();
//            course.setName("dsf");
//            course.setBrief_infor("dsfsd");
//            course.setImage("image/"+"pipe1.png");
//            course.setCate_id(1);
//            course.setUser_id(1);
//            course.setStatus(true);
//            
//            dao.addNewCourse(course);
//        List<Course> listC = courseDAO.getListCourses("", "", "", "1");
//        for (Course c : listC) {
//            System.out.println(c.getBrief_infor());
//        }
    }

    public Course findByCouseId(int courseId) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM Course WHERE course_id = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setTitle(rs.getString(3));
                course.setOriginal_price(rs.getDouble(4));
                course.setBrief_infor(rs.getString(6));
                course.setImage(rs.getString(9));
                return course;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Course> getSubjectList(String name, String sortCate, String sortSTT, int index) {
        List<Course> listS = new ArrayList<>();
        String sql = "SELECT [course_id]\n"
                + "         ,c.[name]\n"
                + "          ,cate.[name]\n"
                + "         ,[lessons]\n"
                + "          ,u.[full_name]\n"
                + "         ,c.[status]\n"
                + "     FROM [dbo].[Course] c \n"
                + "	 JOIN CategoryCourse cate ON  c.cate_id = cate.id \n"
                + "	 JOIN [user] u ON  u.user_id = c.user_id\n "
                + "WHERE c.name LIKE ?\n"
                + "AND cate.id LIKE ?\n"
                + "AND c.status LIKE ?\n"
                + "Order by course_id\n"
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            pre.setString(2, "%" + sortCate + "%");
            pre.setString(3, "%" + sortSTT + "%");
            pre.setInt(4, ((index - 1) * 8));
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Course c = new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getBoolean(6)
                );

                listS.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listS;
    }

    public int getTotalSubject() {
        String sql = "select count(*) from Course";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
        }

        return 0;
    }

    public List<CourseCategory> getFilterCate() {
        List<CourseCategory> listCate = new ArrayList<>();
        String sql = "SELECT  * from CategoryCourse";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                CourseCategory cateC = new CourseCategory(
                        rs.getInt(1),
                        rs.getString(2)
                );
                listCate.add(cateC);
            }

        } catch (SQLException e) {
        }
        return listCate;
    }

    public boolean checkUserRegisterCourse(int user_id, int courseId) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT  [registration_id]\n"
                    + "      ,[course_id]\n"
                    + "      ,[price_package_id]\n"
                    + "      ,[user_id]\n"
                    + "      ,[created]\n"
                    + "  FROM [SWP391_OnlineLearning].[dbo].[registration]\n"
                    + "  where user_id = ? and course_id = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean registerCourse(int courseId, int user_id, int price_packageId) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "INSERT INTO [registration] (course_id,price_package_id,user_id,created) VALUES (?,?,?,?)";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.setInt(2, price_packageId);
            ps.setInt(3, user_id);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(4, timestamp);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
