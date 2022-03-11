package javase.comparetest;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Person[] ps = new Person[]{
                new Person("Bob", 61),
                new Person("Alice", 88),
                new Person("Lily", 75),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }
}
