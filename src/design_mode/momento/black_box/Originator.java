package design_mode.momento.black_box;

import design_mode.mediator.Mediator;

/**
 * 发起者类
 * 因为要求备忘录类对发起者类提供宽接口，对其他类提供窄接口，
 * 所以在发起者内部创建一个私有的备忘录内部类以此实现
 */
public class Originator {

    private String state;

    public Originator(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 创建一个备忘录
     * @return
     */
    public Momento createMomento(){
        return new Momento(this.state);
    }

    /**
     * 从一个备忘录恢复
     * @param momento
     */
    public void restoreMomento(Momento momento) {
        this.state = momento.getState();
    }

    /**
     * 内部备忘录类
     */
    private class Momento implements Momento4Out{
        private String state;

        public Momento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
