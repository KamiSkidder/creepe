// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class Chams extends Module
{
    private static Chams INSTANCE;
    public Setting<Boolean> colorSync;
    public Setting<Boolean> colored;
    public Setting<Boolean> textured;
    public Setting<Boolean> rainbow;
    public Setting<Integer> saturation;
    public Setting<Integer> brightness;
    public Setting<Integer> speed;
    public Setting<Boolean> xqz;
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> alpha;
    public Setting<Integer> hiddenRed;
    public Setting<Integer> hiddenGreen;
    public Setting<Integer> hiddenBlue;
    public Setting<Integer> hiddenAlpha;
    
    public Chams() {
        super("Chams", "Renders players through walls.", Category.RENDER, false, false, false);
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", false));
        this.colored = (Setting<Boolean>)this.register(new Setting("Colored", false));
        this.textured = (Setting<Boolean>)this.register(new Setting("Textured", false));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", Boolean.FALSE, v -> this.colored.getValue()));
        this.saturation = (Setting<Integer>)this.register(new Setting("Saturation", 50, 0, 100, v -> this.colored.getValue() && this.rainbow.getValue()));
        this.brightness = (Setting<Integer>)this.register(new Setting("Brightness", 100, 0, 100, v -> this.colored.getValue() && this.rainbow.getValue()));
        this.speed = (Setting<Integer>)this.register(new Setting("Speed", 40, 1, 100, v -> this.colored.getValue() && this.rainbow.getValue()));
        this.xqz = (Setting<Boolean>)this.register(new Setting("XQZ", Boolean.FALSE, v -> this.colored.getValue() && !this.rainbow.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", 0, 0, 255, v -> this.colored.getValue() && !this.rainbow.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", 255, 0, 255, v -> this.colored.getValue() && !this.rainbow.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", 0, 0, 255, v -> this.colored.getValue() && !this.rainbow.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", 255, 0, 255, v -> this.colored.getValue()));
        this.hiddenRed = (Setting<Integer>)this.register(new Setting("Hidden Red", 255, 0, 255, v -> this.colored.getValue() && this.xqz.getValue() && !this.rainbow.getValue()));
        this.hiddenGreen = (Setting<Integer>)this.register(new Setting("Hidden Green", 0, 0, 255, v -> this.colored.getValue() && this.xqz.getValue() && !this.rainbow.getValue()));
        this.hiddenBlue = (Setting<Integer>)this.register(new Setting("Hidden Blue", 255, 0, 255, v -> this.colored.getValue() && this.xqz.getValue() && !this.rainbow.getValue()));
        this.hiddenAlpha = (Setting<Integer>)this.register(new Setting("Hidden Alpha", 255, 0, 255, v -> this.colored.getValue() && this.xqz.getValue() && !this.rainbow.getValue()));
        this.setInstance();
    }
    
    public static Chams getInstance() {
        if (Chams.INSTANCE == null) {
            Chams.INSTANCE = new Chams();
        }
        return Chams.INSTANCE;
    }
    
    private void setInstance() {
        Chams.INSTANCE = this;
    }
    
    static {
        Chams.INSTANCE = new Chams();
    }
}
