package liufeng.design.pattern.command;

import java.util.List;

/**
 * 命令模式
 * 经典场景：
 *     队列请求，如日程安排、线程池、工作队列
 *     日志请求
 *
 * @author liufeng
 */
public interface Command {
    /**
     * 执行命令
     */
    void execute();

//    /**
//     * 撤销命令
//     */
//    void undo();


}

/**
 * 宏命令
 */
class MicroCommand implements Command {
    List<Command> commands;

    @Override
    public void execute() {
        commands.forEach(Command::execute);
    }
}

/**
 * 空对象
 */
class NoCommand implements Command {

    @Override
    public void execute() {

    }

//    @Override
//    public void undo() {
//
//    }
}

/**
 * 灯对象，具有开启和关闭2个方法
 */
class Light {
    public void on() {
        System.out.println("开灯");
    }

    public void off() {
        System.out.println("关灯");
    }
}

/**
 * 音响对象
 */
class Stereo {
    public void on() {
        System.out.println("打开音响");
        System.out.println("播放音乐");
    }

    public void off() {
        System.out.println("关闭音响");
    }
}

/**
 * 开灯命令
 */
class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.on();
    }

}

/**
 * 关灯命令
 */
class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }


    @Override
    public void execute() {
        light.off();
    }

}

/**
 * 开音响命令
 */
class StereoOnCommand implements Command {
    Stereo stereo;

    public StereoOnCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
    }

}

/**
 * 关音响命令
 */
class StereoOffCommand implements Command {
    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

}

/**
 * 遥控器，有7个插槽，每个按钮有一个按钮，可开可关
 */
class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command command = new NoCommand();

        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = command;
            offCommands[i] = command;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public void onCommand(int slot) {
        onCommands[slot].execute();
    }

    public void offCommand(int slot) {
        offCommands[slot].execute();
    }
}

class Test {
    @org.junit.Test
    public void test() {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        Stereo stereo = new Stereo();
        StereoOnCommand stereoOnCommand = new StereoOnCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        remoteControl.setCommand(1, stereoOnCommand, stereoOffCommand);

        for (int i = 0; i < 7; i++) {
            remoteControl.onCommand(i);
            remoteControl.offCommand(i);
        }
    }
}
