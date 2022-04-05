//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import me.alpha432.oyvey.features.modules.Module;

public class ReverseStep extends Module
{
    public ReverseStep() {
        super("ReverseStep", "Screams chinese words and teleports you", Category.MOVEMENT, true, false, false);
    }
    
    @Override
    public void onUpdate() {
        if (ReverseStep.mc.player.onGround) {
            final EntityPlayerSP player = ReverseStep.mc.player;
            --player.motionY;
        }
    }
}
