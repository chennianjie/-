package design_mode.observer;

/**
 * 被观察对象接口
 */
public interface Subject {

    void addObserver(Observer observer);

    void deleteObserver(Observer observer);

    void nofifyAll(String mess);
}
