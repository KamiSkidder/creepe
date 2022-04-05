// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class SmallShield extends Module
{
    private static SmallShield INSTANCE;
    public Setting<Float> offX;
    public Setting<Float> offY;
    public Setting<Float> mainX;
    public Setting<Float> mainY;
    
    public SmallShield() {
        super("SmallShield", "Makes you offhand lower.", Category.RENDER, false, false, false);
        this.offX = (Setting<Float>)this.register(new Setting("OffHandX", 0.0f, (-1.0f), 1.0f));
        this.offY = (Setting<Float>)this.register(new Setting("OffHandY", 0.0f, (-1.0f), 1.0f));
        this.mainX = (Setting<Float>)this.register(new Setting("MainHandX", 0.0f, (-1.0f), 1.0f));
        this.mainY = (Setting<Float>)this.register(new Setting("MainHandY", 0.0f, (-1.0f), 1.0f));
        this.setInstance();
    }
    
    public static SmallShield getINSTANCE() {
        if (SmallShield.INSTANCE == null) {
            SmallShield.INSTANCE = new SmallShield();
        }
        return SmallShield.INSTANCE;
    }
    
    private void setInstance() {
        SmallShield.INSTANCE = this;
    }
    
    static {
        SmallShield.INSTANCE = new SmallShield();
    }
}
