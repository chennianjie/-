package javase.annotation;

import java.lang.reflect.Field;

public class ScanAnnotation {

    public static void main(String[] args) {
        Student student = new Student();
        Field[] declaredFields = Student.class.getDeclaredFields();
        for (Field field : declaredFields) {
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                try {
                    field.setAccessible(true);
                    Object anIntObject = field.get(student);
                    if (anIntObject instanceof Integer) {
                        int anInt = (int) anIntObject;
                        System.out.println(field.getName() + "  " + anInt);
                        if (anInt > range.max() || anInt < range.min()) {
                            System.err.println("超出范围 0-256 ：" + anInt);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
