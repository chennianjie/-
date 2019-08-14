package design_pattern.command;

public class Client {

    public static void main(String[] args) {
        Light light = new Light("客厅电灯");
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        Invoker invoker = new Invoker();
        Command[] onButtons = new Command[2];
        Command[] offButtons = new Command[2];
        onButtons[0] = lightOnCommand;
        offButtons[0] = lightOffCommand;
        invoker.setOnSwitch(onButtons);
        invoker.setOffSwitch(offButtons);
        invoker.onButtonWasPush(0);
        invoker.offButtonWasPush(0);
    }
}
