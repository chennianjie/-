package design_pattern.mediator;


public class MainBoard implements Mediator {


    //主板上添加好所具有的对象
    private CPU cpu;
    private SoundCard soundCard;
    private CDdriver cDdriver;
    private VedioCard vedioCard;

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public SoundCard getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }

    public CDdriver getcDdriver() {
        return cDdriver;
    }

    public void setcDdriver(CDdriver cDdriver) {
        this.cDdriver = cDdriver;
    }

    public VedioCard getVedioCard() {
        return vedioCard;
    }

    public void setVedioCard(VedioCard vedioCard) {
        this.vedioCard = vedioCard;
    }

    /**
     * 通过college的类型和状态判断下一步需要执行的操作
     * eg:如果是驱动传过来的就需要将驱动的数据传递给CPU
     * @param colleague
     */
    @Override
    public void changed(Colleague colleague) {
        if (colleague == cDdriver) {
            System.out.println("主板已收到光驱读取成功的消息！");
            //将光驱数据传给CPU
            this.CDdriverToCPU((CDdriver) colleague);
        }else {
            //将CPU数据传递给声卡和显卡
            this.CPUToCard((CPU)colleague);
        }
    }

    private void CPUToCard(CPU cpu) {
        this.soundCard.playSound(cpu.getVoiceData());
        this.vedioCard.playVedio(cpu.getVedioData());
    }

    private void CDdriverToCPU(CDdriver cDdriver) {
        String cdData = cDdriver.getCDData();
        this.cpu.analysisData(cdData);
    }
}
