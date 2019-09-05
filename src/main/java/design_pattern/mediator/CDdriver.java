package design_pattern.mediator;

/**
 * 光驱，用去读取cd上的数据
 */
public class CDdriver extends Colleague{

    //存储光驱从cd上读取到的数据
    private String CDData = "";

    public CDdriver(Mediator mediator) {
        super(mediator);
    }

    public String getCDData() {
        return CDData;
    }

    public void setCDData(String CDData) {
        this.CDData = CDData;
    }

    /**
     * 读取cd数据的方法
     */
    public void readCD() {
        this.CDData = "汉语，中介者设计模式从入门到入魔。";
        Mediator mediator = getMediator();
        System.out.println("光驱读取CD成功！");
        mediator.changed(this);
    }

}
