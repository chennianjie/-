package design_pattern.mediator;

/**
 * 场景：电脑通过CD观看电影，首先光驱读取到CD上的数据，然后通知主板，主板把光驱传过来的数据发送给CPU，CPU对数据
 * 进行分析处理成声音数据和视频数据，分别发送给声卡和显卡
 * 涉及的具体事物：光驱,CPU,声卡,显卡,主板
 * 中介者接口，控制Colleague之间的交互
 */
public interface Mediator {
    void changed(Colleague colleague);
}
