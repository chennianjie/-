package design_mode.momento.black_box;


/**
 * 负责人角色
 * 用于存储备忘录对象
 * 只有发起者对象可以访问备忘角色
 */
public class CareTaker {

    private Momento4Out momento;

    public CareTaker(Momento4Out momento){
        this.momento = momento;
    }

    public Momento4Out getMomento() {
        return momento;
    }

    public void setMomento(Momento4Out momento) {
        this.momento = momento;
    }
}
