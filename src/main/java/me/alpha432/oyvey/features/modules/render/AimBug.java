//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.setting.Setting;
import java.util.Random;
import me.alpha432.oyvey.features.modules.Module;

public class AimBug extends Module
{
    private final Random random;
    private final Setting<Boolean> turn;
    
    public AimBug() {
        super("AimBug", "Stop servers attempting to kick u for being AFK.", Category.PLAYER, true, false, false);
        this.turn = (Setting<Boolean>)this.register(new Setting("cocoaaaa", true));
        this.random = new Random();
    }
    
    @Override
    public void onUpdate() {
        if (AimBug.mc.player.ticksExisted % 1 == 0 && this.turn.getValue()) {
            AimBug.mc.player.rotationYaw = (float)(this.random.nextInt(360) - 31 - 90 - 10);
        }
    }
}
