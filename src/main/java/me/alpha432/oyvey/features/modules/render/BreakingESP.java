//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.util.RenderUtil2;
import java.awt.Color;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class BreakingESP extends Module
{
    public final Setting<Float> lineWidth;
    public final Setting<Integer> boxAlpha;
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Boolean> box;
    public Setting<Boolean> outline;
    
    public BreakingESP() {
        super("BreakingESP", "Renders a box on blocks being broken", Category.RENDER, true, false, false);
        this.box = (Setting<Boolean>)this.register(new Setting("Box", true));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", 125, 0, 255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", 0, 0, 255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", 255, 0, 255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", 1.0f, 0.1f, 5.0f, object -> this.outline.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", 85, 0, 255, object -> this.box.getValue()));
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (BreakingESP.mc.playerController.currentBlock != null) {
            final Color color = new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.boxAlpha.getValue());
            RenderUtil2.boxESP(BreakingESP.mc.playerController.currentBlock, color, this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }
}
