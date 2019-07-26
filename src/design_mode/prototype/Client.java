package design_mode.prototype;

public class Client {

    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype("chennianjie");
        Prototype prototype1 = prototype.myClone();
        System.out.println(prototype == prototype1);
    }
}
