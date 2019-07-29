package design_mode.command;

/**
 * 遥控器
 */
public class Invoker {

    //开关数量
    private Integer soltNum = 7;
    private Command[] onSwitch;
    private Command[] offSwitch;

    public Invoker(){
        onSwitch = new Command[soltNum];
        offSwitch = new Command[soltNum];
    }

    public Integer getSoltNum() {
        return soltNum;
    }

    public void setSoltNum(Integer soltNum) {
        this.soltNum = soltNum;
    }

    public Command[] getOnSwitch() {
        return onSwitch;
    }

    public void setOnSwitch(Command[] onSwitch) {
        this.onSwitch = onSwitch;
    }

    public Command[] getOffSwitch() {
        return offSwitch;
    }

    public void setOffSwitch(Command[] offSwitch) {
        this.offSwitch = offSwitch;
    }

    public void onButtonWasPush(int soltNum) {
        onSwitch[soltNum].excute();
    }

    public void offButtonWasPush(int soltNum) {
        offSwitch[soltNum].excute();
    }
}
