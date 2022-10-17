package cc.l4j.autolog.hack;

import cc.l4j.autolog.hack.options.Option;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.Packet;

import java.util.ArrayList;
import java.util.List;

public class Hack {

    protected MinecraftClient mc = MinecraftClient.getInstance();
    private List<Option> options = new ArrayList<>();
    private String name, desc, displayName;
    private Category category;
    private boolean enabled;
    private int key;

    public Hack(String name, String desc, Category category) {
        this.name = name;
        this.displayName = name;
        this.desc = desc;
        this.category = category;
    }

    public List<Option> getOptions(){
        return options;
    }

    public void addSetting(Option option){
        options.add(option);
    }

    public void addSettings(Option... options){
        for(Option option : options){
            addSetting(option);
        }
    }

    public void toggle(){
        enabled = !enabled;
        if (enabled){
            onEnable();
        }else{
            onDisable();
        }
    }

    public void onEnable(){}
    public void onDisable(){}
    public void onTick(){}
    public void onPacketSend(Packet<?> packet){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public void sendPacket(Packet<?> packet){
        mc.player.networkHandler.sendPacket(packet);
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled){
            onEnable();
        }else {
            onDisable();
        }
    }

    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        VISUAL("Visual"),
        EXPLOITS("Exploits"),
        MISC("Misc");

        public String name;

        private Category(String name){
            this.name = name;
        }
    }
}
