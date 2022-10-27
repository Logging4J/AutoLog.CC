package cc.l4j.autolog.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.Arrays;

public abstract class Command {

    MinecraftClient mc = MinecraftClient.getInstance();
    public static final String PREFIX = ";";
    public String name, desc;
    public String[] triggers;

    public Command(String name, String desc, String... triggers) {
        this.name = name;
        this.desc = desc;
        this.triggers = triggers;
    }

    public abstract void exc(String[] args);

    public static void sendMessage(String msg){
        MinecraftClient.getInstance().player.sendMessage(Text.of(msg));
    }

    public void onChat(String message){
        String trimmedMessage = message.substring(Command.PREFIX.length());
        if(!(trimmedMessage.isEmpty() || trimmedMessage.isBlank())){
            String[] messageSplit = trimmedMessage.split(" +");
            String command = messageSplit[0];
            String[] args = Arrays.copyOfRange(messageSplit, 1, messageSplit.length);
            Command run = CommandManager.getByAlias(command);
            if(run == null){Command.sendMessage("Command Not Found");}
            else{run.exc(args);}return;
        }
        return;
    }
}
