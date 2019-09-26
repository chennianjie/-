package ds;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 9/23/2019
 */
public class Student {

    private String exam;
    private String examid;
    private String idcard;
    private String name;
    private String location;
    private String grade;



    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Student{" +
                ", exam='" + exam + '\'' +
                ", examid='" + examid + '\'' +
                ", idcard='" + idcard + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
