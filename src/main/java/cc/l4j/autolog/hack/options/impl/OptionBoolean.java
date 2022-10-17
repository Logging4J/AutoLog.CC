package cc.l4j.autolog.hack.options.impl;

import cc.l4j.autolog.hack.options.Option;

public class OptionBoolean extends Option {

    private boolean enabled;

    public OptionBoolean(String name, boolean defVal){
        super(name);
        enabled = defVal;
    }

    public void toggle(){
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
