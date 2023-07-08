
package model;

public class ExamType {
    private int id;
    private String name;

    public ExamType() {
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

    @Override
    public String toString() {
        return "ExamType{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
