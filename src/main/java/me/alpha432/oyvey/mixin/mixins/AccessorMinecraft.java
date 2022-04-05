// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.mixin.mixins;

import net.minecraft.util.Session;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.util.Timer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public interface AccessorMinecraft
{
    @Accessor("timer")
    Timer getTimer();
    
    @Accessor("session")
    void setSession(final Session p0);
    
    @Accessor("renderPartialTicksPaused")
    float getRenderPartialTicksPaused();
}
