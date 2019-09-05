package design_pattern.observer;

public class Observer1 implements Observer {

    private String name;

    public Observer1(String name) {
        this.name = name;
    }

    @Override
    public void update(String mess) {
        System.out.println(this.name+"--"+mess);
    }
}
