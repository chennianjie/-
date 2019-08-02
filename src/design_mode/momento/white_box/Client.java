package design_mode.momento.white_box;

/**
 * 备忘录模式  在不破坏对象封装的前提下，把对象内部信息外部化并存储，进而达到备份并还原的目的
 *
 */
public class Client {
    public static void main(String[] args) {
        //创建发起者
        Originator originator = new Originator("ON");
        System.out.println("发起者当前状态为"+originator.getState());
        Momento momento = originator.createMomento();
        CareTaker careTaker = new CareTaker(momento);
        //改变发起者状态
        originator.setState("OFF");
        System.out.println("改变之后当前状态为"+originator.getState());
        System.out.println("使用备忘录恢复");
        originator.retoreMomento(momento);
        System.out.println("恢复之后当前状态为"+originator.getState());
    }
}
