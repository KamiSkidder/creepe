//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.movement;

import java.util.Comparator;
import me.alpha432.oyvey.OyVey;
import net.minecraft.util.math.BlockPos;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class HoleTP extends Module
{
    private final Setting<Float> range;
    
    public HoleTP() {
        super("HoleTP", "like TP", Category.MOVEMENT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", 0.5f, 0.1f, 10.0f));
    }
    
    @Override
    public void onUpdate() {
        final BlockPos hole = OyVey.holeManager.calcHoles().stream().min(Comparator.comparing(p -> HoleTP.mc.player.getDistance((double)p.getX(), (double)p.getY(), (double)p.getZ()))).orElse(null);
        if (hole != null && HoleTP.mc.player.getDistance((double)hole.getX(), (double)hole.getY(), (double)hole.getZ()) < this.range.getValue() + 1.5) {
            HoleTP.mc.player.setPosition(hole.getX() + 0.5, (double)hole.getY(), hole.getZ() + 0.5);
            HoleTP.mc.player.setPosition(hole.getX() + 0.5, (double)hole.getY(), hole.getZ() + 0.5);
            this.disable();
        }
    }
}
