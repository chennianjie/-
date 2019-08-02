package design_mode.momento.white_box;


/**
 * 发起者角色
 * 有表示自身状态的属性
 * 提供一个可以将自己状态存入备忘录的方法
 * 通过备忘录恢复原始状态的方法
 */
public class Originator {

    private String state;

    public Originator(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }


    /**
     * 创建一个备忘录并存储
     */
    public Momento createMomento(){
         return new Momento(this.state);
    }

    /**
     * 通过备忘录恢复原始状态
     * @param momento
     */
    public void retoreMomento(Momento momento){
        this.state = momento.getState();
    }
}
