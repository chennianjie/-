package design_pattern.mediator;


/**
 * 需要通过主板交互的相关抽象类
 * 与主板之间的聚合关系
 */
public abstract class Colleague {

    //需要一个主板属性，当该类改变需要通知到主板时调用
    private Mediator mediator;
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
