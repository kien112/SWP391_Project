
package model;

import java.sql.Date;


public class Exam {
    private int id;
    private String name;
    private int subject_id;
    private String subject_name;
    private Level level;
    private int duration;
    private float pass_rate;
    private int number_of_question;
    private String description;
    private Date created;
    private boolean mode;
    private ExamType examType;

    public Exam() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPass_rate() {
        return pass_rate;
    }

    public void setPass_rate(float pass_rate) {
        this.pass_rate = pass_rate;
    }

    public int getNumber_of_question() {
        return number_of_question;
    }

    public void setNumber_of_question(int number_of_question) {
        this.number_of_question = number_of_question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", name=" + name + ", subject_id=" + subject_id + ", subject_name=" + subject_name + ", level=" + level + ", duration=" + duration + ", pass_rate=" + pass_rate + ", number_of_question=" + number_of_question + ", description=" + description + ", created=" + created + ", mode=" + mode + ", examType=" + examType + '}';
    }
    
}
