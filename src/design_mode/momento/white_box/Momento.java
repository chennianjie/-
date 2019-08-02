package design_mode.momento.white_box;


/**
 * 备忘录对象模式  ---白箱操作
 * 对于备忘录对象内部存储的发起者对象状态信息公开处理，相当于对外提供一个宽接口
 * 存储发起者对象的内部状态
 */
public class Momento {

    private String state;

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public Momento(String state){
        this.state = state;
    }
}
