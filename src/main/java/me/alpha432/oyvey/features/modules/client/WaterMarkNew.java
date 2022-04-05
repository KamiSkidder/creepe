//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.util.MathUtil;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import me.alpha432.oyvey.features.modules.Module;

public class WaterMarkNew extends Module
{
    public WaterMarkNew() {
        super("WaterMark2", "new troll", Category.CLIENT, true, false, false);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (nullCheck()) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            final String ping = this.getPing((EntityPlayer)WaterMarkNew.mc.player) + "PING";
            final String fpsText = Minecraft.debugFPS + "FPS ";
            final String server = Minecraft.getMinecraft().isSingleplayer() ? "SinglePlayer".toLowerCase() : WaterMarkNew.mc.getCurrentServerData().serverIP.toLowerCase();
            final String text = "Crepe || " + server + " || " + ping + " || " + fpsText;
            final float width = (float)(Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 6);
            final int height = 20;
            final int posX = 2;
            final int posY = 2;
            RenderUtil.drawRectangleCorrectly(posX - 4, posY - 4, (int)(width + 10.0f), height + 6, ColorUtil.toRGBA(0, 0, 0, 255));
            RenderUtil.drawRectangleCorrectly(posX - 4, posY - 4, (int)(width + 11.0f), height + 7, ColorUtil.toRGBA(25, 25, 25, 255));
            drawRect(posX, posY, posX + width + 2.0f, posY + height, new Color(0, 0, 0, 178).getRGB());
            drawRect(posX + 2.5, posY + 2.5, posX + width - 0.5, posY + 4.5, new Color(0, 0, 0, 165).getRGB());
            drawGradientSideways(4.0, posY + 3, 4.0f + width / 3.0f, posY + 4, new Color(255, 255, 255, 255).getRGB(), new Color(255, 255, 255, 255).getRGB());
            drawGradientSideways(4.0f + width / 3.0f, posY + 3, 4.0f + width / 3.0f * 2.0f, posY + 4, new Color(255, 255, 255, 255).getRGB(), new Color(255, 252, 252, 255).getRGB());
            drawGradientSideways(4.0f + width / 3.0f * 2.0f, posY + 3, width / 3.0f * 3.0f + 1.0f, posY + 4, new Color(255, 255, 255, 255).getRGB(), new Color(255, 255, 255, 255).getRGB());
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, (float)(4 + posX), (float)(8 + posY), -1);
        }
    }
    
    public static void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color >> 24 & 0xFF) / 255.0f;
        final float f4 = (color >> 16 & 0xFF) / 255.0f;
        final float f5 = (color >> 8 & 0xFF) / 255.0f;
        final float f6 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f4, f5, f6, f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawGradientSideways(final double left, final double top, final double right, final double bottom, final int col1, final int col2) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    private int getPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = (int)MathUtil.clamp((float)Objects.requireNonNull(WaterMarkNew.mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1.0f, 9999999.0f);
        }
        catch (NullPointerException ex) {}
        return ping;
    }
}
