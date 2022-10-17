package cc.l4j.autolog.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public abstract class Command {

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
}
