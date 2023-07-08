
package model;


public class QuestionExam {
    private int exam_id;
    private int question_id;
    private int order;
    private float mark;

    public QuestionExam() {
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "QuestionExam{" + "exam_id=" + exam_id + ", question_id=" + question_id + ", order=" + order + ", mark=" + mark + '}';
    }
    
    
}
