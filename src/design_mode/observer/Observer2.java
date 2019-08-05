package design_mode.observer;

public class Observer2 implements Observer {

    private String name;

    public Observer2(String name) {
        this.name = name;
    }

    @Override
    public void update(String mess) {
        System.out.println(this.name+"--"+mess);
    }
}
