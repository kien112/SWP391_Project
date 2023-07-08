/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.QuestionExam;

/**
 *
 * @author 84877
 */
public class QuestionExamDAO extends DBContext {

    public void addListQuestionToExam(int examId, int qId, int order, float mark) {

        try {
            String sql = "insert into question_exam\n"
                    + "values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examId);
            ps.setInt(2, qId);
            ps.setInt(3, order);
            ps.setFloat(4, mark);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public QuestionExam getQuestionExam(int examId, int qId) {
        ResultSet rs = null;

        try {
            String sql = "select exam_id, question_id\n"
                    + "from question_exam\n"
                    + "where exam_id = ? and question_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examId);
            ps.setInt(2, qId);
            rs = ps.executeQuery();
            if (rs.next()) {
                QuestionExam qe = new QuestionExam();
                qe.setExam_id(rs.getInt(1));
                qe.setQuestion_id(rs.getInt(2));
                return qe;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    
    public static void main(String[] args) {
        QuestionExamDAO dao = new QuestionExamDAO();
        System.out.println(dao.getQuestionExam(0, 0));
    }
}
