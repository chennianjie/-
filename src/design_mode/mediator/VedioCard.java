package design_mode.mediator;

public class VedioCard extends Colleague {
    public VedioCard(Mediator mediator) {
        super(mediator);
    }

    public void playVedio(String vedioData) {
        System.out.println("您正在观看的是：" + vedioData);
    }
}
