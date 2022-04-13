package javase.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 4/13/2022
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Person> people = new PriorityQueue<>(new PersonComparable());
        Person ban = new Person("Ban", "a1");
        Person jay = new Person("Jay", "v1");
        Person po = new Person("po", "a2");
        people.offer(po);
        people.offer(jay);
        people.offer(ban);
        System.out.println(people.poll().toString());
        System.out.println(people.poll().toString());
        System.out.println(people.poll().toString());
    }

}


//比较器
class PersonComparable implements Comparator<Person> {
    /**
     *
     * @param o1
     * @param o2
     * @return 负数o1排序在前  正数o2排序在前
     */
    @Override
    public int compare(Person o1, Person o2) {

        if (o1.getNumber().charAt(0) == o2.getNumber().charAt(0)){
            return o1.getNumber().compareTo(o2.getNumber());
        }

        if (o1.getNumber().charAt(0) == 'v') {
            return -1;
        }else {
            return 1;
        }
    }
}

class Person{
    private String name;
    private String number;

    public Person() {
    }

    public Person(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
