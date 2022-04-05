//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.event.events.Render2DEvent;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import me.alpha432.oyvey.util.Util;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.util.ResourceLocation;
import me.alpha432.oyvey.features.modules.Module;

public class Logo extends Module
{
    public static final ResourceLocation mark;
    public Setting<Integer> imageX;
    public Setting<Integer> imageY;
    public Setting<Integer> imageWidth;
    public Setting<Integer> imageHeight;
    private int color;
    
    public Logo() {
        super("Logo", "Puts a logo there (there)", Category.CLIENT, false, false, false);
        this.imageX = (Setting<Integer>)this.register(new Setting("logoX", 0, 0, 300));
        this.imageY = (Setting<Integer>)this.register(new Setting("logoY", 0, 0, 300));
        this.imageWidth = (Setting<Integer>)this.register(new Setting("logoWidth", 97, 0, 1000));
        this.imageHeight = (Setting<Integer>)this.register(new Setting("logoHeight", 97, 0, 1000));
    }
    
    public void renderLogo() {
        final int width = this.imageWidth.getValue();
        final int height = this.imageHeight.getValue();
        final int x = this.imageX.getValue();
        final int y = this.imageY.getValue();
        Util.mc.renderEngine.bindTexture(Logo.mark);
        GlStateManager.color(255.0f, 255.0f, 255.0f);
        Gui.drawScaledCustomSizeModalRect(x - 2, y - 36, 7.0f, 7.0f, width - 7, height - 7, width, height, (float)width, (float)height);
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (!Feature.fullNullCheck()) {
            final int width = this.renderer.scaledWidth;
            final int height = this.renderer.scaledHeight;
            this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
            if (this.enabled.getValue()) {
                this.renderLogo();
            }
        }
    }
    
    static {
        mark = new ResourceLocation("textures/CrepeLogo1.png");
    }
}
