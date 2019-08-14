package design_pattern.momento.black_box;

/**
 * 自述历史模式和多重检查点模式的备忘录设计模式
 */
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator("ON");
        System.out.println("发起人开始状态"+originator.getState());
        Momento4Out momento = originator.createMemento();
        originator.setState("OFF");
        System.out.println("发起人修改之后的状态"+originator.getState());
        originator.restoreMemento(momento);
        System.out.println("备忘录恢复到开始时的状态" + originator.getState());
    }
}
