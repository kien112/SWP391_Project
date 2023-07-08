package dao;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import model.Dimension;
import model.Lesson;
import model.Level;
import model.Question;
import model.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QuestionDAO extends DBContext {

    public List<Question> getAllQuestions(int subject_id, int dimension_id, int lesson_id, int level_id, int status, String content) {
        ResultSet rs = null;

        try {
            List<Question> list = new ArrayList<>();
            String sql = "select q.id, q.lesson_id, q.content,\n"
                    + "q.option_a, q.option_b, q.option_c,\n"
                    + "q.option_d, q.answer, q.explanation, q.status,\n"
                    + "q.modified, l.[name] as lesson_name,\n"
                    + "l.subject_id, s.[name] as subject_name,\n"
                    + "s.dimension_id, d.[name] as dimension_name,\n"
                    + "le.[level], q.level_id\n"
                    + "from question q left join lesson l\n"
                    + "on q.lesson_id = l.id\n"
                    + "left join [subject] s\n"
                    + "on s.id = l.subject_id\n"
                    + "left join dimension d \n"
                    + "on d.id = s.dimension_id\n"
                    + "left join [level] le\n"
                    + "on le.id = q.level_id\n"
                    + "where (? = -1 or l.subject_id = ?) "
                    + "and (? = -1 or s.dimension_id = ?) "
                    + "and (? = -1 or q.lesson_id = ?) "
                    + "and (? = -1 or q.level_id = ?) "
                    + "and (? = -1 or q.status = ?) "
                    + "and (? = '' or q.content like ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, subject_id);
            ps.setInt(2, subject_id);
            ps.setInt(3, dimension_id);
            ps.setInt(4, dimension_id);
            ps.setInt(5, lesson_id);
            ps.setInt(6, lesson_id);
            ps.setInt(7, level_id);
            ps.setInt(8, level_id);
            ps.setInt(9, status);
            ps.setInt(10, status);
            ps.setString(11, content);
            ps.setString(12, "%" + content + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setContent(rs.getString("content"));
                question.setOption_a(rs.getString("option_a"));
                question.setOption_b(rs.getString("option_b"));
                question.setOption_c(rs.getString("option_c"));
                question.setOption_d(rs.getString("option_d"));
                question.setAnswer(rs.getString("answer"));
                question.setExplanation(rs.getString("explanation"));
                question.setModified(rs.getDate("modified"));
                question.setStatus(rs.getBoolean("status"));

                Dimension dimension = new Dimension();
                dimension.setId(rs.getInt("dimension_id"));
                dimension.setName(rs.getString("dimension_name"));

                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setName(rs.getString("subject_name"));
                subject.setDimension(dimension);
                question.setSubject(subject);

                Level level = new Level();
                level.setId(rs.getInt("level_id"));
                level.setLevel(rs.getString("level"));
                question.setLevel(level);

                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("lesson_id"));
                lesson.setName(rs.getString("lesson_name"));
                question.setLesson(lesson);

                list.add(question);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Object[]> getQuestionsFromExcel(String file) throws FileNotFoundException, IOException {
        File myFile = new File(file);
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        List<Object[]> list = new ArrayList<>();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum()>0){
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                Object[] obj = new Object[8];
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if(cell.getColumnIndex() < 8){
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                obj[cell.getColumnIndex()] = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                obj[cell.getColumnIndex()] = cell.getNumericCellValue();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                obj[cell.getColumnIndex()] = cell.getBooleanCellValue();
                                break;
                            default:
                                break;
                        }
                    }
                }
                list.add(obj);
            }
        }
        return list;
    }
    
    public void insertQuestionToDBFromExcel(List<Object[]> list, Question q){
        Question question = new Question();
        question.setLesson(new Lesson());
        question.getLesson().setId(q.getLesson().getId());
        List<Level> levels = getAllLevelsByStatus(-1);
        for (Object[] obj : list) {
            if(obj[0] != null){
                for (int i = 0; i < obj.length; i++) {
                    question.setContent((String)obj[0]);
                    question.setOption_a((String)obj[1]);
                    question.setOption_b((String)obj[2]);
                    question.setOption_c((String)obj[3]);
                    question.setOption_d((String)obj[4]);
                    question.setAnswer((String)obj[5]);
                    question.setExplanation((String)obj[6]);
                    int level = levels.get(0).getId();
                    if(obj[7]!=null){
                        for (Level le : levels) {
                            if(le.getLevel().equals(obj[7])){
                                level = le.getId();
                                break;
                            }
                        }
                    }
                    question.setLevel(new Level());
                    question.getLevel().setId(level);
                }
                addNewQuestion(question);
            }
        }
    }

    public void writeDataToExcel(String file) throws FileNotFoundException, IOException {
        File myFile = new File(file);
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        CellRangeAddressList addressList = new CellRangeAddressList(1,4,1,1);
        DataValidationHelper dvHelper = mySheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("myDataArea");
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, addressList);
        mySheet.addValidationData(dataValidation);

//        Map<String, Object[]> data = new HashMap<String, Object[]>();
//        data.put("7", new Object[]{7d, "Sonya", "75K", "SALES", "Rupert"});
//        data.put("8", new Object[]{8d, "Kris", "85K", "SALES", "Rupert"});
//        data.put("9", new Object[]{9d, "Dave", "90K", "SALES", "Rupert"});
//
//        // Set to Iterate and add rows into XLS file
//        Set<String> newRows = data.keySet();
//
//        // get the last row number to append new data          
//        int rownum = mySheet.getLastRowNum();
//
//        for (String key : newRows) {
//
//            // Creating a new Row in existing XLSX sheet
//            Row row = mySheet.createRow(rownum++);
//            Object[] objArr = data.get(key);
//            int cellnum = 0;
//            for (Object obj : objArr) {
//                Cell cell = row.createCell(cellnum++);
//                if (obj instanceof String) {
//                    cell.setCellValue((String) obj);
//                } else if (obj instanceof Boolean) {
//                    cell.setCellValue((Boolean) obj);
//                } else if (obj instanceof Date) {
//                    cell.setCellValue((Date) obj);
//                } else if (obj instanceof Double) {
//                    cell.setCellValue((Double) obj);
//                }
//            }
//        }
//
//        // open an OutputStream to save written data into XLSX file
//        FileOutputStream os = new FileOutputStream(myFile);
//        myWorkBook.write(os);
        System.out.println("Writing on XLSX file Finished ...");
    }
    
    
    public List<Level> getAllLevelsByStatus(int status) {
        ResultSet rs = null;

        try {
            String sql = "select * from [level] "
                    + "where (? = -1 or status = ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, status);

            rs = ps.executeQuery();
            List<Level> list = new ArrayList<>();
            while (rs.next()) {
                Level level = new Level();
                level.setId(rs.getInt(1));
                level.setLevel(rs.getString(2));
                level.setStatus(rs.getBoolean(3));
                list.add(level);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addNewQuestion(Question q) {
        try {
            String sql = "insert into question(content, option_a, option_b, option_c,\n"
                    + "option_d, answer, explanation, lesson_id, level_id,\n"
                    + "[status], modified)\n"
                    + "values(?,?,?,?,?,?,?,?,?,1,GETDATE())";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, q.getContent());
            ps.setString(2, q.getOption_a());
            ps.setString(3, q.getOption_b());
            ps.setString(4, q.getOption_c());
            ps.setString(5, q.getOption_d());
            ps.setString(6, q.getAnswer());
            ps.setString(7, q.getExplanation());
            ps.setInt(8, q.getLesson().getId());
            ps.setInt(9, q.getLevel().getId());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateQuestion(Question q) {
        try {
            String sql = "update question set content = ?, option_a = ?,\n"
                    + "option_b = ?, option_c = ?, option_d = ?,\n"
                    + "answer = ?, explanation = ?, modified = GETDATE(),\n"
                    + "level_id = ?, lesson_id = ? \n"
                    + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, q.getContent());
            ps.setString(2, q.getOption_a());
            ps.setString(3, q.getOption_b());
            ps.setString(4, q.getOption_c());
            ps.setString(5, q.getOption_d());
            ps.setString(6, q.getAnswer());
            ps.setString(7, q.getExplanation());
            ps.setInt(8, q.getLevel().getId());
            ps.setInt(9, q.getLesson().getId());
            ps.setInt(10, q.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeStatusQuestion(int id) {
        try {
            String sql = "update question set [status] = \n"
                    + "case when status=1 then 0 \n"
                    + "else 1 end\n"
                    + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        QuestionDAO dao = new QuestionDAO();
        try {
            Question q = new Question();
            q.setLesson(new Lesson());
            q.getLesson().setId(6);
            List<Object[]> list = dao.getQuestionsFromExcel("E:\\excel\\Book1.xlsx");
            dao.insertQuestionToDBFromExcel(list, q);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
