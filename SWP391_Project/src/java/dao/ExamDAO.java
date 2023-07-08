package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Exam;
import model.ExamType;
import model.Level;

public class ExamDAO extends DBContext {

    public List<Exam> getAllExam(int subjectId, int typeId, String name) {
        ResultSet rs = null;
        List<Exam> list = new ArrayList<>();
        try {
            String sql = "select e.id, e.name, e.subject_id,\n"
                    + "s.name as subject_name, e.level_id,\n"
                    + "l.[level], e.duration, e.pass_rate,\n"
                    + "e.number_of_question, e.description,\n"
                    + "e.created, e.mode, e.type_id,\n"
                    + "et.name as type_name, mode\n"
                    + "from exam e left join exam_type et \n"
                    + "on e.type_id = et.id\n"
                    + "left join [subject] s \n"
                    + "on s.id = e.subject_id\n"
                    + "left join [level] l \n"
                    + "on l.id = e.level_id "
                    + "where (? = -1 or e.subject_id = ?) "
                    + "and (? = -1 or e.type_id = ?) "
                    + "and (? = '' or e.name like ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, subjectId);
            ps.setInt(2, subjectId);
            ps.setInt(3, typeId);
            ps.setInt(4, typeId);
            ps.setString(5, name);
            ps.setString(6, "%" + name + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setName(rs.getString("name"));
                exam.setSubject_id(rs.getInt("subject_id"));
                exam.setSubject_name(rs.getString("subject_name"));
                exam.setLevel(new Level());
                exam.getLevel().setId(rs.getInt("level_id"));
                exam.getLevel().setLevel(rs.getString("level"));
                exam.setDuration(rs.getInt("duration"));
                exam.setPass_rate(rs.getFloat("pass_rate"));
                exam.setNumber_of_question(rs.getInt("number_of_question"));
                exam.setDescription(rs.getString("description"));
                exam.setExamType(new ExamType());
                exam.getExamType().setId(rs.getInt("type_id"));
                exam.getExamType().setName(rs.getString("type_name"));
                exam.setMode(rs.getBoolean("mode"));
                list.add(exam);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
    public List<ExamType> getAllExamTypes(){
        ResultSet rs = null;
        
        try {
            String sql = "select * from exam_type";
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            List<ExamType> list = new ArrayList<>();
            while (rs.next()) {
                ExamType t = new ExamType();
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                list.add(t);
            }
            return list;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addNewExam(Exam exam) {

        try {
            String sql = "insert into exam(name, subject_id, level_id,\n"
                    + "duration, pass_rate, number_of_question, \n"
                    + "description,type_id, created, mode)\n"
                    + "values(?,?,?,?,?,?,?,?,GETDATE(),1)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, exam.getName());
            ps.setInt(2, exam.getSubject_id());
            ps.setInt(3, exam.getLevel().getId());
            ps.setInt(4, exam.getDuration());
            ps.setFloat(5, exam.getPass_rate());
            ps.setInt(6, exam.getNumber_of_question());
            ps.setString(7, exam.getDescription());
            ps.setInt(8, exam.getExamType().getId());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateExam(Exam exam) {

        try {
            String sql = "update exam set [name] = ?,\n"
                    + "subject_id = ?, level_id = ?,\n"
                    + "duration = ?, pass_rate = ?,\n"
                    + "number_of_question = ?,\n"
                    + "description = ?, type_id = ? \n"
                    + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, exam.getName());
            ps.setInt(2, exam.getSubject_id());
            ps.setInt(3, exam.getLevel().getId());
            ps.setInt(4, exam.getDuration());
            ps.setFloat(5, exam.getPass_rate());
            ps.setInt(6, exam.getNumber_of_question());
            ps.setString(7, exam.getDescription());
            ps.setInt(8, exam.getExamType().getId());
            ps.setInt(9, exam.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeModeExam(int id) {

        try {
            String sql = "update exam set [mode] = \n"
                    + "case when mode=1 then 0 \n"
                    + "else 1 end\n"
                    + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExamDAO dao = new ExamDAO();
        dao.changeModeExam(1);
//        Exam exam = new Exam();
//        exam.setName("kkk");
//        exam.setId(1);
//        exam.setLevel(new Level());
//        exam.getLevel().setId(1);
//        exam.setSubject_id(2);
//        exam.setExamType(new ExamType());
//        exam.getExamType().setId(1);
//        exam.setDuration(45);
//        exam.setNumber_of_question(40);
//        exam.setPass_rate(3.9f);
//        exam.setDescription("dsvd");
//        dao.updateExam(exam);
        for (Exam object : dao.getAllExam(-1, -1, "")) {
            System.out.println(object);
        }
    }
}
