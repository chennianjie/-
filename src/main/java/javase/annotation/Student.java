package javase.annotation;

public class Student {

    @Range
    private Integer math = 10;
    @Range
    private Integer chinese = 266;

    public Student() {
    }

    public Student(Integer math, Integer chinese) {
        this.math = math;
        this.chinese = chinese;
    }
}
