package design_pattern.mediator;

public class CPU extends Colleague {

    //视频数据
    private String vedioData;
    //声音数据
    private String voiceData;

    public CPU(Mediator mediator) {
        super(mediator);
    }

    public String getVedioData() {
        return vedioData;
    }

    public void setVedioData(String vedioData) {
        this.vedioData = vedioData;
    }

    public String getVoiceData() {
        return voiceData;
    }

    public void setVoiceData(String voiceData) {
        this.voiceData = voiceData;
    }

    /**
     * 解析从光驱传过来的数据
     * @param metaData
     */
    public void analysisData(String metaData){
        this.voiceData = metaData.split("，")[0];
        this.vedioData = metaData.split("，")[1];
        System.out.println("CPU已成功处理好数据！");
        Mediator mediator = getMediator();
        mediator.changed(this);
    }
}
