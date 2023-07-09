package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.PricePackage;
import model.Registrations;

public class RegistrationDAO extends DBContext {

    public List<Registrations> getAllRegistrations(String course, Date fromDate,
            Date toDate, int status, String email) {

        ResultSet rs = null;
        List<Registrations> list = new ArrayList<>();
        try {
            String sql = "select r.id, r.email, r.registration_time,\n"
                    + "r.course_id, c.name as course_name,\n"
                    + "r.price_package_id, \n"
                    + "p.price, p.name as package,\n"
                    + "p.sale, r.status, r.valid_from,\n"
                    + "r.valid_to, r.update_by\n"
                    + "from registrations r left join Course c\n"
                    + "on r.course_id = c.course_id\n"
                    + "left join price_package p\n"
                    + "on p.id = r.price_package_id\n"
                    + "where (? = '' or c.name like ?) "
                    + "and (? is null or r.registration_time between ? and ?) "
                    + "and (? = -1 or r.status = ?) "
                    + "and (? = '' or r.email like ?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, course);
            ps.setString(2, "%" + course + "%");
            ps.setDate(3, fromDate);
            ps.setDate(4, fromDate);
            ps.setDate(5, toDate);
            ps.setInt(6, status);
            ps.setInt(7, status);
            ps.setString(8, email);
            ps.setString(9, "%" + email + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Registrations r = new Registrations();
                r.setId(rs.getInt("id"));
                r.setEmail(rs.getString("email"));
                r.setRegistration_time(rs.getDate("registration_time"));
                r.setCourse_id(rs.getInt("course_id"));
                r.setCourse_name(rs.getString("course_name"));
                r.setPricePackage(new PricePackage());
                r.getPricePackage().setId(rs.getInt("price_package_id"));
                r.getPricePackage().setPrice(rs.getDouble("price"));
                r.getPricePackage().setName(rs.getString("package"));
                r.getPricePackage().setSale(rs.getDouble("sale"));
                r.setStatus(rs.getInt("status"));
                r.setValid_from(rs.getDate("valid_from"));
                r.setValid_to(rs.getDate("valid_to"));
                r.setUpdate_by(rs.getString("update_by"));

                list.add(r);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Registrations> getAllRegistrations7Day() {

        ResultSet rs = null;
        List<Registrations> list = new ArrayList<>();
        try {
            String sql = "select r.id, r.email, r.registration_time,\n"
                    + "r.course_id, c.name as course_name,\n"
                    + "r.price_package_id, \n"
                    + "p.price, p.name as package,\n"
                    + "p.sale, r.status, r.valid_from,\n"
                    + "r.valid_to, r.update_by\n"
                    + "from registrations r left join Course c\n"
                    + "on r.course_id = c.course_id\n"
                    + "left join price_package p\n"
                    + "on p.id = r.price_package_id\n"
                    + "where DATEDIFF(DAY,r.registration_time,GETDATE()) <= 7";

            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Registrations r = new Registrations();
                r.setId(rs.getInt("id"));
                r.setEmail(rs.getString("email"));
                r.setRegistration_time(rs.getDate("registration_time"));
                r.setCourse_id(rs.getInt("course_id"));
                r.setCourse_name(rs.getString("course_name"));
                r.setPricePackage(new PricePackage());
                r.getPricePackage().setId(rs.getInt("price_package_id"));
                r.getPricePackage().setPrice(rs.getDouble("price"));
                r.getPricePackage().setName(rs.getString("package"));
                r.getPricePackage().setSale(rs.getDouble("sale"));
                r.setStatus(rs.getInt("status"));
                r.setValid_from(rs.getDate("valid_from"));
                r.setValid_to(rs.getDate("valid_to"));
                r.setUpdate_by(rs.getString("update_by"));

                list.add(r);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
    
    public void addNewRegistration(Registrations r) {
        try {
            String sql = "insert into registrations(email, registration_time,\n"
                    + "course_id, price_package_id, status, valid_from,\n"
                    + "valid_to, update_by)\n"
                    + "values(?,GETDATE(),?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, r.getEmail());
            ps.setInt(2, r.getCourse_id());
            ps.setInt(3, r.getPricePackage().getId());
            ps.setInt(4, r.getStatus());
            ps.setDate(5, r.getValid_from());
            ps.setDate(6, r.getValid_to());
            ps.setString(7, r.getUpdate_by());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRegistration(Registrations r) {
        try {
            String sql = "update registrations set email = ?,\n"
                    + "registration_time = GETDATE(), course_id = ?,\n"
                    + "price_package_id = ?, status = ?,\n"
                    + "valid_from = ?, valid_to = ?,\n"
                    + "update_by = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, r.getEmail());
            ps.setInt(2, r.getCourse_id());
            ps.setInt(3, r.getPricePackage().getId());
            ps.setInt(4, r.getStatus());
            ps.setDate(5, r.getValid_from());
            ps.setDate(6, r.getValid_to());
            ps.setString(7, r.getUpdate_by());
            ps.setInt(8, r.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusRegistration(int id, int stauts, String updateBy) {
        try {
            String sql = "update registrations set status = ?,"
                    + "update_by = ? "
                    + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, stauts);
            ps.setString(2, updateBy);
            ps.setInt(3, id);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String checkEmailExist(int id) {
        try {
            ResultSet rs = null;
            String sql = "select r.email\n"
                    + "from registrations r join [user] u\n"
                    + "on r.email = u.email\n"
                    + "where r.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
                return rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public String getEmailRegistration(int id) {
        try {
            ResultSet rs = null;
            String sql = "select r.email\n"
                    + "from registrations r\n"
                    + "where r.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next())
                return rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        RegistrationDAO dao = new RegistrationDAO();
        long millis = System.currentTimeMillis();
        Date fromDate = new Date(millis);
        LocalDate date = fromDate.toLocalDate();
        LocalDate newDate = date.plusMonths(4);
        Date toDate = Date.valueOf(newDate);
        Registrations re = new Registrations();
        re.setCourse_id(2);
        re.setEmail("vvvc");
        re.setPricePackage(new PricePackage());
        re.getPricePackage().setId(1);
        re.setValid_from(fromDate);
        re.setValid_to(toDate);

        dao.addNewRegistration(re);

        for (Registrations r : dao.getAllRegistrations("", null, null, -1, "")) {
            System.out.println(r);
        }
    }

}
