package cc.l4j.autolog.hack.options.impl;

import cc.l4j.autolog.hack.options.Option;

public class OptionSlider extends Option {

    private double min, max, increment;
    private double value;

    public OptionSlider(String name, double min, double max, double increment, double defVal) {
        super(name);
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.value = defVal;
    }

    public static double clamp(double value, double min, double max){
        value = Math.max(min,max);
        value = Math.min(min,max);
        return value;
    }

    public double getValue(){
        return value;
    }

    public float getFloatValue(){
        return (float) value;
    }

    public int getIntValue(){
        return (int) value;
    }

    public double getIncrement() {
        return increment;
    }

    public void setValue(double value){
        value = clamp(value, min, max);
        value = Math.round(value *(1/increment)) / (1 / increment);
        this.value = value;
    }

    public void increment(boolean positive){
        if(positive){
            setValue(getValue() + getIncrement());
        }else {
            setValue(getValue() - getIncrement());
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
