//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;

public class AirJump extends Module
{
    public AirJump() {
        super("AirJump", "Makes it possible to jump while ur in the air.", Category.MOVEMENT, false, false, false);
    }
    
    @Override
    public void onUpdate() {
        AirJump.mc.player.onGround = true;
    }
}
