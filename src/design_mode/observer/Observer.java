package design_mode.observer;

/**
 * 观察者对象接口
 * 定义一个接收更新信息的方法
 */
public interface Observer {

    /**
     * 主题更新
     * @param mess
     */
    void update(String mess);
}
