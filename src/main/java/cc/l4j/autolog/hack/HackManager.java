package cc.l4j.autolog.hack;

import cc.l4j.autolog.hack.impl.*;

import java.util.ArrayList;
import java.util.List;

public class HackManager {

    public static HackManager getInstance = new HackManager();
    public List<Hack> hacks = new ArrayList<>();

    public HackManager(){
        add(new Disabler());
        add(new BowBomb());
        add(new Dupe());
    }

    public List<Hack> getHacks() {
        return hacks;
    }

    public List<Hack> getEnabledHacks(){
        List<Hack> enabled = new ArrayList<>();
        for(Hack hack : hacks){
            if(hack.isEnabled()){enabled.add(hack);}
        }
        return enabled;
    }

    public Hack getHack(String name){
        Hack h = null;
        for(Hack hack : hacks){
            if(name.equalsIgnoreCase(hack.getName())){
                h = hack;
            }
        }
        return h;
    }

    public List<Hack> getHacksInCategory(Hack.Category category){
        List<Hack> cathacks = new ArrayList<>();
        for(Hack hack : hacks){
            if(hack.getCategory() == category){
                cathacks.add(hack);
            }
        }
        return cathacks;
    }

    private void add(Hack hack){
        hacks.add(hack);
    }
}
