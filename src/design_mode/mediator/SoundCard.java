package design_mode.mediator;

public class SoundCard extends Colleague {

    public SoundCard(Mediator mediator) {
        super(mediator);
    }

    public void playSound(String voiceData) {
        System.out.println("您收听的语言是：" + voiceData);
    }
}
