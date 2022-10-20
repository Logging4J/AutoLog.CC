package cc.l4j.autolog;

import cc.l4j.autolog.graphics.click.CliclGui;
import cc.l4j.autolog.hack.Hack;
import cc.l4j.autolog.hack.HackManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ServerList;
import net.minecraft.network.Packet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class AutoLog implements ModInitializer {

    public static final String MOD_NAME = "AutoLog-CC";
    public static AutoLog getInstance = new AutoLog();
    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        log("AutoLog.CC Initializing");
    }

    public void onKeyPress(int key, int action){
        if(action == GLFW.GLFW_PRESS){
            for(Hack hack : HackManager.getInstance.getHacks()){
                if (key == hack.getKey()) {
                    hack.toggle();
                }
                if(key == GLFW.GLFW_KEY_RIGHT_SHIFT){
                    mc.setScreen(CliclGui.getInstance);
                }
            }
        }
    }
    public void onTick(){
        if(nullCheck()){return;}
        for(Hack hack: HackManager.getInstance.getEnabledHacks()){
            hack.onTick();
        }
    }

    public void onPacketSend(Packet<?> packet){
        if(nullCheck()){return;}
        for(Hack hack: HackManager.getInstance.getEnabledHacks()){
            hack.onPacketSend(packet);
        }
    }


    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }
    public void log(String message){
        LOGGER.info( "["+MOD_NAME+"] " + message);
    }
}
