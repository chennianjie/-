package design_pattern.command;

/**
 * 最终执行命令的具体对象
 */
public class Light {
    private String name;
    public Light(String name) {
        this.name = name;
    }

    /**
     * 电灯打开
     */
    public void on() {
        System.out.println(name + "打开啦！");
    }

    /**
     * 电灯关闭
     */
    public void off() {
        System.out.println(name + "关闭啦！");
    }

}
