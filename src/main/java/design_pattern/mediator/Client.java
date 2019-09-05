package design_pattern.mediator;

public class Client {
    public static void main(String[] args) {
        MainBoard mainBoard = new MainBoard();
        CPU cpu = new CPU(mainBoard);
        CDdriver cDdriver = new CDdriver(mainBoard);
        SoundCard soundCard = new SoundCard(mainBoard);
        VedioCard vedioCard = new VedioCard(mainBoard);

        mainBoard.setcDdriver(cDdriver);
        mainBoard.setCpu(cpu);
        mainBoard.setSoundCard(soundCard);
        mainBoard.setVedioCard(vedioCard);

        cDdriver.readCD();
    }
}
