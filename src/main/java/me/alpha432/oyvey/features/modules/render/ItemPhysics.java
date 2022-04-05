// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class ItemPhysics extends Module
{
    public static ItemPhysics INSTANCE;
    public final Setting<Float> Scaling;
    
    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items.", Category.RENDER, true, false, false);
        this.Scaling = (Setting<Float>)this.register(new Setting("Scaling", 0.5f, 0.0f, 10.0f));
        this.setInstance();
    }
    
    public static ItemPhysics getInstance() {
        if (ItemPhysics.INSTANCE == null) {
            ItemPhysics.INSTANCE = new ItemPhysics();
        }
        return ItemPhysics.INSTANCE;
    }
    
    private void setInstance() {
        ItemPhysics.INSTANCE = this;
    }
    
    static {
        ItemPhysics.INSTANCE = new ItemPhysics();
    }
}
