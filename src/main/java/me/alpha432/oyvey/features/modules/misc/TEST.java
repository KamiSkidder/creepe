//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Timerm;
import me.alpha432.oyvey.features.modules.Module;

public class TEST extends Module
{
    private final Timerm timer;
    private Setting<String> custom;
    
    public TEST() {
        super("AWA", "testmodule", Category.MISC, true, false, false);
        this.timer = new Timerm();
        this.custom = (Setting<String>)this.register(new Setting("Custom", "Crepe Client On Top!!!"));
    }
    
    @Override
    public void onEnable() {
        TEST.mc.player.sendChatMessage((String)this.custom.getValue());
        this.disable();
    }
}
