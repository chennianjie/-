package design_mode.momento.black_box;

import design_mode.momento.white_box.Momento;

/**
 * 负责人角色
 * 用于存储备忘录对象
 * 只有发起者对象可以访问备忘角色
 */
public class CareTaker {

    private Momento momento;

    public CareTaker(Momento momento){
        this.momento = momento;
    }

    public Momento getMomento(){
        return momento;
    }

    public void setMomento(Momento momento){
        this.momento = momento;
    }
}
