//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.gui.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.ColorUtil;
import java.net.URI;
import java.awt.Desktop;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.renderer.GlStateManager;
import me.alpha432.oyvey.OyVey;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.GuiScreen;

public class GuiCustomMainScreen extends GuiScreen
{
    private final ResourceLocation resourceLocation;
    private int y;
    private int x;
    private int singleplayerWidth;
    private int multiplayerWidth;
    private int settingsWidth;
    private int discordWidth;
    private int exitWidth;
    private int textHeight;
    private float xOffset;
    private float yOffset;
    private boolean particleSystem;
    
    public GuiCustomMainScreen() {
        this.resourceLocation = new ResourceLocation("textures/back.png");
    }
    
    public static void drawCompleteImage(final float posX, final float posY, final float width, final float height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(width, height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public static boolean isHovered(final int x, final int y, final int width, final int height, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY < y + height;
    }
    
    private void playMusic() {
        if (!this.mc.soundHandler.isSoundPlaying(OyVey.SONG_MANAGER.getMenuSong())) {
            this.mc.soundHandler.playSound(OyVey.SONG_MANAGER.getMenuSong());
        }
    }
    
    public void initGui() {
        this.x = this.width / 14;
        this.y = this.height / 14 + 62;
        this.playMusic();
        this.buttonList.add(new TextButton(0, this.x, this.y + 20, "Single"));
        this.buttonList.add(new TextButton(0, this.x, this.y + 44, "Online"));
        this.buttonList.add(new TextButton(0, this.x, this.y + 66, "Settings"));
        this.buttonList.add(new TextButton(0, this.x, this.y + 88, "Discord"));
        this.buttonList.add(new TextButton(0, this.x, this.y + 132, "Log"));
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public void updateScreen() {
        super.updateScreen();
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (isHovered(this.x, this.y + 20, OyVey.textManager.getStringWidth("Single") / 4, OyVey.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.displayGuiScreen((GuiScreen)new GuiWorldSelection((GuiScreen)this));
        }
        else if (isHovered(this.x, this.y + 44, OyVey.textManager.getStringWidth("Online") / 4, OyVey.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
        }
        else if (isHovered(this.x, this.y + 66, OyVey.textManager.getStringWidth("Settings") / 4, OyVey.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, this.mc.gameSettings));
        }
        else if (isHovered(this.x, this.y + 132, OyVey.textManager.getStringWidth("Log") / 4, OyVey.textManager.getFontHeight(), mouseX, mouseY)) {
            this.mc.shutdown();
        }
        else if (isHovered(this.x, this.y + 88, OyVey.textManager.getStringWidth("Discord") / 4, OyVey.textManager.getFontHeight(), mouseX, mouseY)) {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("https://discord.gg/ZxjwX4FaDh"));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.x = this.width / 16;
        this.y = this.height / 16 + 64;
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        this.mc.getTextureManager().bindTexture(this.resourceLocation);
        drawCompleteImage(-16.0f + this.xOffset, -9.0f + this.yOffset, (float)(this.width + 32), (float)(this.height + 18));
        RenderUtil.drawRect(this.xOffset, this.yOffset, 70.0f, 1000.0f, ColorUtil.toRGBA(20, 20, 20, 70));
        super.drawScreen(970, 540, partialTicks);
        OyVey.textManager.drawStringBig("KuroHack ", (float)this.x, this.y - 10.0f, Color.WHITE.getRGB(), true);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public BufferedImage parseBackground(final BufferedImage background) {
        int width;
        int srcWidth;
        int srcHeight;
        int height;
        for (width = 1920, srcWidth = background.getWidth(), srcHeight = background.getHeight(), height = 1080; width < srcWidth || height < srcHeight; width *= 2, height *= 2) {}
        final BufferedImage imgNew = new BufferedImage(width, height, 2);
        final Graphics g = imgNew.getGraphics();
        g.drawImage(background, 0, 0, null);
        g.dispose();
        return imgNew;
    }
    
    private static class TextButton extends GuiButton
    {
        public TextButton(final int buttonId, final int x, final int y, final String buttonText) {
            super(buttonId, x, y, OyVey.textManager.getStringWidth(buttonText), OyVey.textManager.getFontHeight(), buttonText);
        }
        
        public void drawButton(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
            if (this.visible) {
                this.enabled = true;
                this.hovered = (mouseX >= this.x - OyVey.textManager.getStringWidth(this.displayString) / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
                OyVey.textManager.drawStringWithShadow(this.displayString, this.x - OyVey.textManager.getStringWidth(this.displayString) / 2.0f, (float)this.y, Color.WHITE.getRGB());
                if (this.hovered) {
                    RenderUtil.drawLine(this.x - 2.0f - OyVey.textManager.getStringWidth(this.displayString) / 2.0f, (float)(this.y + 2 + OyVey.textManager.getFontHeight()), this.x + OyVey.textManager.getStringWidth(this.displayString) / 2.0f + 1.0f, (float)(this.y + 2 + OyVey.textManager.getFontHeight()), 1.0f, Color.WHITE.getRGB());
                    OyVey.textManager.drawStringSmall("Click me!", (float)this.x, this.y - 10.0f, Color.WHITE.getRGB(), true);
                }
            }
        }
        
        public boolean mousePressed(final Minecraft mc, final int mouseX, final int mouseY) {
            return this.enabled && this.visible && mouseX >= this.x - OyVey.textManager.getStringWidth(this.displayString) / 2.0f && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        }
    }
}
