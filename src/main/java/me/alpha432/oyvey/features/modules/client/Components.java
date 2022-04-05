//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.client;

import net.minecraft.inventory.Slot;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import me.alpha432.oyvey.util.EntityUtil;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.init.Blocks;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.OpenGlHelper;
import me.alpha432.oyvey.util.MathUtil;
import net.minecraft.client.gui.ScaledResolution;
import me.alpha432.oyvey.util.RenderUtil;
import java.awt.Color;
import me.alpha432.oyvey.event.events.Render2DEvent;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.math.MathHelper;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import me.alpha432.oyvey.OyVey;
import net.minecraft.entity.player.EntityPlayer;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.util.ResourceLocation;
import me.alpha432.oyvey.features.modules.Module;

public class Components extends Module
{
    private static final ResourceLocation box;
    public static ResourceLocation logo;
    public static ResourceLocation logo2;
    public Setting<Boolean> inventory;
    public Setting<Integer> invX;
    public Setting<Integer> invY;
    public Setting<Integer> fineinvX;
    public Setting<Integer> fineinvY;
    public Setting<Boolean> renderXCarry;
    public Setting<Integer> invH;
    public Setting<Boolean> holeHud;
    public Setting<Integer> holeX;
    public Setting<Integer> holeY;
    public Setting<Compass> compass;
    public Setting<Integer> compassX;
    public Setting<Integer> compassY;
    public Setting<Integer> scale;
    public Setting<Boolean> playerViewer;
    public Setting<Integer> playerViewerX;
    public Setting<Integer> playerViewerY;
    public Setting<Float> playerScale;
    public Setting<Boolean> imageLogo;
    public Setting<Integer> imageX;
    public Setting<Integer> imageY;
    public Setting<Integer> imageWidth;
    public Setting<Integer> imageHeight;
    public Setting<Boolean> imageLogo2;
    public Setting<Integer> imageX2;
    public Setting<Integer> imageY2;
    public Setting<Integer> imageWidth2;
    public Setting<Integer> imageHeight2;
    public Setting<Boolean> clock;
    public Setting<Boolean> clockFill;
    public Setting<Float> clockX;
    public Setting<Float> clockY;
    public Setting<Float> clockRadius;
    public Setting<Float> clockLineWidth;
    public Setting<Integer> clockSlices;
    public Setting<Integer> clockLoops;
    
    public Components() {
        super("HUD MAIN", "HudComponents", Category.CLIENT, false, false, true);
        this.inventory = (Setting<Boolean>)this.register(new Setting("Inventory", false));
        this.invX = (Setting<Integer>)this.register(new Setting("InvX", 564, 0, 1000, v -> this.inventory.getValue()));
        this.invY = (Setting<Integer>)this.register(new Setting("InvY", 467, 0, 1000, v -> this.inventory.getValue()));
        this.fineinvX = (Setting<Integer>)this.register(new Setting("InvFineX", 0, v -> this.inventory.getValue()));
        this.fineinvY = (Setting<Integer>)this.register(new Setting("InvFineY", 0, v -> this.inventory.getValue()));
        this.renderXCarry = (Setting<Boolean>)this.register(new Setting("RenderXCarry", Boolean.FALSE, v -> this.inventory.getValue()));
        this.invH = (Setting<Integer>)this.register(new Setting("InvH", 3, v -> this.inventory.getValue()));
        this.holeHud = (Setting<Boolean>)this.register(new Setting("HoleHUD", false));
        this.holeX = (Setting<Integer>)this.register(new Setting("HoleX", 279, 0, 1000, v -> this.holeHud.getValue()));
        this.holeY = (Setting<Integer>)this.register(new Setting("HoleY", 485, 0, 1000, v -> this.holeHud.getValue()));
        this.compass = (Setting<Compass>)this.register(new Setting("Compass", Compass.NONE));
        this.compassX = (Setting<Integer>)this.register(new Setting("CompX", 472, 0, 1000, v -> this.compass.getValue() != Compass.NONE));
        this.compassY = (Setting<Integer>)this.register(new Setting("CompY", 424, 0, 1000, v -> this.compass.getValue() != Compass.NONE));
        this.scale = (Setting<Integer>)this.register(new Setting("Scale", 3, 0, 10, v -> this.compass.getValue() != Compass.NONE));
        this.playerViewer = (Setting<Boolean>)this.register(new Setting("PlayerViewer", false));
        this.playerViewerX = (Setting<Integer>)this.register(new Setting("PlayerX", 752, 0, 1000, v -> this.playerViewer.getValue()));
        this.playerViewerY = (Setting<Integer>)this.register(new Setting("PlayerY", 497, 0, 1000, v -> this.playerViewer.getValue()));
        this.playerScale = (Setting<Float>)this.register(new Setting("PlayerScale", 1.0f, 0.1f, 2.0f, v -> this.playerViewer.getValue()));
        this.imageLogo = (Setting<Boolean>)this.register(new Setting("ImageLogo", true));
        this.imageX = (Setting<Integer>)this.register(new Setting("ImageX", (-35), (-100), 1000, v -> this.imageLogo.getValue()));
        this.imageY = (Setting<Integer>)this.register(new Setting("ImageY", (-48), (-100), 1000, v -> this.imageLogo.getValue()));
        this.imageWidth = (Setting<Integer>)this.register(new Setting("ImageWidth", 177, 0, 1000, v -> this.imageLogo.getValue()));
        this.imageHeight = (Setting<Integer>)this.register(new Setting("ImageHeight", 142, 0, 1000, v -> this.imageLogo.getValue()));
        this.imageLogo2 = (Setting<Boolean>)this.register(new Setting("ImageLogo", false));
        this.imageX2 = (Setting<Integer>)this.register(new Setting("ImageX", 497, 0, 1000, v -> this.imageLogo.getValue()));
        this.imageY2 = (Setting<Integer>)this.register(new Setting("ImageY", 230, 0, 1000, v -> this.imageLogo.getValue()));
        this.imageWidth2 = (Setting<Integer>)this.register(new Setting("ImageWidth", 194, 0, 1000, v -> this.imageLogo.getValue()));
        this.imageHeight2 = (Setting<Integer>)this.register(new Setting("ImageHeight", 182, 0, 1000, v -> this.imageLogo.getValue()));
        this.clock = (Setting<Boolean>)this.register(new Setting("Clock", true));
        this.clockFill = (Setting<Boolean>)this.register(new Setting("ClockFill", true));
        this.clockX = (Setting<Float>)this.register(new Setting("ClockX", 2.0f, 0.0f, 1000.0f, v -> this.clock.getValue()));
        this.clockY = (Setting<Float>)this.register(new Setting("ClockY", 2.0f, 0.0f, 1000.0f, v -> this.clock.getValue()));
        this.clockRadius = (Setting<Float>)this.register(new Setting("ClockRadius", 6.0f, 0.0f, 100.0f, v -> this.clock.getValue()));
        this.clockLineWidth = (Setting<Float>)this.register(new Setting("ClockLineWidth", 1.0f, 0.0f, 5.0f, v -> this.clock.getValue()));
        this.clockSlices = (Setting<Integer>)this.register(new Setting("ClockSlices", 360, 1, 720, v -> this.clock.getValue()));
        this.clockLoops = (Setting<Integer>)this.register(new Setting("ClockLoops", 1, 1, 720, v -> this.clock.getValue()));
    }
    
    public static EntityPlayer getClosestEnemy() {
        EntityPlayer closestPlayer = null;
        for (final EntityPlayer player : Components.mc.world.playerEntities) {
            if (player != Components.mc.player) {
                if (OyVey.friendManager.isFriend(player)) {
                    continue;
                }
                if (closestPlayer == null) {
                    closestPlayer = player;
                }
                else {
                    if (Components.mc.player.getDistanceSq((Entity)player) >= Components.mc.player.getDistanceSq((Entity)closestPlayer)) {
                        continue;
                    }
                    closestPlayer = player;
                }
            }
        }
        return closestPlayer;
    }
    
    private static double getPosOnCompass(final Direction dir) {
        final double yaw = Math.toRadians(MathHelper.wrapDegrees(Components.mc.player.rotationYaw));
        final int index = dir.ordinal();
        return yaw + index * 1.5707963267948966;
    }
    
    private static void preboxrender() {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        GlStateManager.enableBlend();
        GlStateManager.color(255.0f, 255.0f, 255.0f, 255.0f);
    }
    
    private static void postboxrender() {
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
    }
    
    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
    }
    
    private static void postitemrender() {
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawCompleteImage(final int posX, final int posY, final int width, final int height) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)posX, (float)posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float)height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float)width, (float)height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float)width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (fullNullCheck()) {
            return;
        }
        if (this.playerViewer.getValue()) {
            this.drawPlayer();
        }
        if (this.compass.getValue() != Compass.NONE) {
            this.drawCompass();
        }
        if (this.holeHud.getValue()) {
            this.drawOverlay(event.partialTicks);
        }
        if (this.inventory.getValue()) {
            this.renderInventory();
        }
        if (this.imageLogo.getValue()) {
            this.drawImageLogo();
        }
        if (this.imageLogo2.getValue()) {
            this.drawImageLogo();
        }
        if (this.clock.getValue()) {
            RenderUtil.drawClock(this.clockX.getValue(), this.clockY.getValue(), this.clockRadius.getValue(), this.clockSlices.getValue(), this.clockLoops.getValue(), this.clockLineWidth.getValue(), this.clockFill.getValue(), new Color(255, 0, 0, 255));
        }
    }
    
    public void drawImageLogo() {
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        Components.mc.getTextureManager().bindTexture(Components.logo);
        drawCompleteImage(this.imageX.getValue(), this.imageY.getValue(), this.imageWidth.getValue(), this.imageHeight.getValue());
        Components.mc.getTextureManager().deleteTexture(Components.logo);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
    }
    
    public void drawImageLogo2() {
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        Components.mc.getTextureManager().bindTexture(Components.logo2);
        drawCompleteImage(this.imageX2.getValue(), this.imageY2.getValue(), this.imageWidth2.getValue(), this.imageHeight2.getValue());
        Components.mc.getTextureManager().deleteTexture(Components.logo2);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
    }
    
    public void drawCompass() {
        final ScaledResolution sr = new ScaledResolution(Components.mc);
        if (this.compass.getValue() == Compass.LINE) {
            final float playerYaw = Components.mc.player.rotationYaw;
            final float rotationYaw = MathUtil.wrap(playerYaw);
            RenderUtil.drawRect(this.compassX.getValue(), this.compassY.getValue(), (float)(this.compassX.getValue() + 100), (float)(this.compassY.getValue() + this.renderer.getFontHeight()), 1963986960);
            RenderUtil.glScissor(this.compassX.getValue(), this.compassY.getValue(), (float)(this.compassX.getValue() + 100), (float)(this.compassY.getValue() + this.renderer.getFontHeight()), sr);
            GL11.glEnable(3089);
            final float zeroZeroYaw = MathUtil.wrap((float)(Math.atan2(0.0 - Components.mc.player.posZ, 0.0 - Components.mc.player.posX) * 180.0 / 3.141592653589793) - 90.0f);
            RenderUtil.drawLine(this.compassX.getValue() - rotationYaw + 50.0f + zeroZeroYaw, (float)(this.compassY.getValue() + 2), this.compassX.getValue() - rotationYaw + 50.0f + zeroZeroYaw, (float)(this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -61424);
            RenderUtil.drawLine(this.compassX.getValue() - rotationYaw + 50.0f + 45.0f, (float)(this.compassY.getValue() + 2), this.compassX.getValue() - rotationYaw + 50.0f + 45.0f, (float)(this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            RenderUtil.drawLine(this.compassX.getValue() - rotationYaw + 50.0f - 45.0f, (float)(this.compassY.getValue() + 2), this.compassX.getValue() - rotationYaw + 50.0f - 45.0f, (float)(this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            RenderUtil.drawLine(this.compassX.getValue() - rotationYaw + 50.0f + 135.0f, (float)(this.compassY.getValue() + 2), this.compassX.getValue() - rotationYaw + 50.0f + 135.0f, (float)(this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            RenderUtil.drawLine(this.compassX.getValue() - rotationYaw + 50.0f - 135.0f, (float)(this.compassY.getValue() + 2), this.compassX.getValue() - rotationYaw + 50.0f - 135.0f, (float)(this.compassY.getValue() + this.renderer.getFontHeight() - 2), 2.0f, -1);
            this.renderer.drawStringWithShadow("n", this.compassX.getValue() - rotationYaw + 50.0f + 180.0f - this.renderer.getStringWidth("n") / 2.0f, this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("n", this.compassX.getValue() - rotationYaw + 50.0f - 180.0f - this.renderer.getStringWidth("n") / 2.0f, this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("e", this.compassX.getValue() - rotationYaw + 50.0f - 90.0f - this.renderer.getStringWidth("e") / 2.0f, this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("s", this.compassX.getValue() - rotationYaw + 50.0f - this.renderer.getStringWidth("s") / 2.0f, this.compassY.getValue(), -1);
            this.renderer.drawStringWithShadow("w", this.compassX.getValue() - rotationYaw + 50.0f + 90.0f - this.renderer.getStringWidth("w") / 2.0f, this.compassY.getValue(), -1);
            RenderUtil.drawLine((float)(this.compassX.getValue() + 50), (float)(this.compassY.getValue() + 1), (float)(this.compassX.getValue() + 50), (float)(this.compassY.getValue() + this.renderer.getFontHeight() - 1), 2.0f, -7303024);
            GL11.glDisable(3089);
        }
        else {
            final double centerX = this.compassX.getValue();
            final double centerY = this.compassY.getValue();
            for (final Direction dir : Direction.values()) {
                final double rad = getPosOnCompass(dir);
                this.renderer.drawStringWithShadow(dir.name(), (float)(centerX + this.getX(rad)), (float)(centerY + this.getY(rad)), (dir == Direction.N) ? -65536 : -1);
            }
        }
    }
    
    public void drawPlayer(final EntityPlayer player) {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate(0.0f, 0.0f, 5.0f, 0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.playerViewerX.getValue() + 25), (float)(this.playerViewerY.getValue() + 25), 50.0f);
        GlStateManager.scale(-50.0f * this.playerScale.getValue(), 50.0f * this.playerScale.getValue(), 50.0f * this.playerScale.getValue());
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(this.playerViewerY.getValue() / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager rendermanager = Components.mc.getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        try {
            rendermanager.renderEntity((Entity)player, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception ex) {}
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc(515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }
    
    public void drawPlayer() {
        final EntityPlayerSP ent = Components.mc.player;
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate(0.0f, 0.0f, 5.0f, 0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.playerViewerX.getValue() + 25), (float)(this.playerViewerY.getValue() + 25), 50.0f);
        GlStateManager.scale(-50.0f * this.playerScale.getValue(), 50.0f * this.playerScale.getValue(), 50.0f * this.playerScale.getValue());
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(this.playerViewerY.getValue() / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager rendermanager = Components.mc.getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        try {
            rendermanager.renderEntity((Entity)ent, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception ex) {}
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc(515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }
    
    private double getX(final double rad) {
        return Math.sin(rad) * (this.scale.getValue() * 10);
    }
    
    private double getY(final double rad) {
        final double epicPitch = MathHelper.clamp(Components.mc.player.rotationPitch + 30.0f, -90.0f, 90.0f);
        final double pitchRadians = Math.toRadians(epicPitch);
        return Math.cos(rad) * Math.sin(pitchRadians) * (this.scale.getValue() * 10);
    }
    
    public void drawOverlay(final float partialTicks) {
        float yaw = 0.0f;
        final int dir = MathHelper.floor(Components.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
        switch (dir) {
            case 1: {
                yaw = 90.0f;
                break;
            }
            case 2: {
                yaw = -180.0f;
                break;
            }
            case 3: {
                yaw = -90.0f;
                break;
            }
        }
        final BlockPos northPos = this.traceToBlock(partialTicks, yaw);
        final Block north = this.getBlock(northPos);
        if (north != null && north != Blocks.AIR) {
            final int damage = this.getBlockDamage(northPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)(this.holeX.getValue() + 16), this.holeY.getValue(), (float)(this.holeX.getValue() + 32), (float)(this.holeY.getValue() + 16), 1627324416);
            }
            this.drawBlock(north, (float)(this.holeX.getValue() + 16), this.holeY.getValue());
        }
        final BlockPos southPos;
        final Block south;
        if ((south = this.getBlock(southPos = this.traceToBlock(partialTicks, yaw - 180.0f))) != null && south != Blocks.AIR) {
            final int damage = this.getBlockDamage(southPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)(this.holeX.getValue() + 16), (float)(this.holeY.getValue() + 32), (float)(this.holeX.getValue() + 32), (float)(this.holeY.getValue() + 48), 1627324416);
            }
            this.drawBlock(south, (float)(this.holeX.getValue() + 16), (float)(this.holeY.getValue() + 32));
        }
        final BlockPos eastPos;
        final Block east;
        if ((east = this.getBlock(eastPos = this.traceToBlock(partialTicks, yaw + 90.0f))) != null && east != Blocks.AIR) {
            final int damage = this.getBlockDamage(eastPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)(this.holeX.getValue() + 32), (float)(this.holeY.getValue() + 16), (float)(this.holeX.getValue() + 48), (float)(this.holeY.getValue() + 32), 1627324416);
            }
            this.drawBlock(east, (float)(this.holeX.getValue() + 32), (float)(this.holeY.getValue() + 16));
        }
        final BlockPos westPos;
        final Block west;
        if ((west = this.getBlock(westPos = this.traceToBlock(partialTicks, yaw - 90.0f))) != null && west != Blocks.AIR) {
            final int damage = this.getBlockDamage(westPos);
            if (damage != 0) {
                RenderUtil.drawRect(this.holeX.getValue(), (float)(this.holeY.getValue() + 16), (float)(this.holeX.getValue() + 16), (float)(this.holeY.getValue() + 32), 1627324416);
            }
            this.drawBlock(west, this.holeX.getValue(), (float)(this.holeY.getValue() + 16));
        }
    }
    
    public void drawOverlay(final float partialTicks, final Entity player, final int x, final int y) {
        float yaw = 0.0f;
        final int dir = MathHelper.floor(player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
        switch (dir) {
            case 1: {
                yaw = 90.0f;
                break;
            }
            case 2: {
                yaw = -180.0f;
                break;
            }
            case 3: {
                yaw = -90.0f;
                break;
            }
        }
        final BlockPos northPos = this.traceToBlock(partialTicks, yaw, player);
        final Block north = this.getBlock(northPos);
        if (north != null && north != Blocks.AIR) {
            final int damage = this.getBlockDamage(northPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)(x + 16), (float)y, (float)(x + 32), (float)(y + 16), 1627324416);
            }
            this.drawBlock(north, (float)(x + 16), (float)y);
        }
        final BlockPos southPos;
        final Block south;
        if ((south = this.getBlock(southPos = this.traceToBlock(partialTicks, yaw - 180.0f, player))) != null && south != Blocks.AIR) {
            final int damage = this.getBlockDamage(southPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)(x + 16), (float)(y + 32), (float)(x + 32), (float)(y + 48), 1627324416);
            }
            this.drawBlock(south, (float)(x + 16), (float)(y + 32));
        }
        final BlockPos eastPos;
        final Block east;
        if ((east = this.getBlock(eastPos = this.traceToBlock(partialTicks, yaw + 90.0f, player))) != null && east != Blocks.AIR) {
            final int damage = this.getBlockDamage(eastPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)(x + 32), (float)(y + 16), (float)(x + 48), (float)(y + 32), 1627324416);
            }
            this.drawBlock(east, (float)(x + 32), (float)(y + 16));
        }
        final BlockPos westPos;
        final Block west;
        if ((west = this.getBlock(westPos = this.traceToBlock(partialTicks, yaw - 90.0f, player))) != null && west != Blocks.AIR) {
            final int damage = this.getBlockDamage(westPos);
            if (damage != 0) {
                RenderUtil.drawRect((float)x, (float)(y + 16), (float)(x + 16), (float)(y + 32), 1627324416);
            }
            this.drawBlock(west, (float)x, (float)(y + 16));
        }
    }
    
    private int getBlockDamage(final BlockPos pos) {
        for (final DestroyBlockProgress destBlockProgress : Components.mc.renderGlobal.damagedBlocks.values()) {
            if (destBlockProgress.getPosition().getX() == pos.getX() && destBlockProgress.getPosition().getY() == pos.getY()) {
                if (destBlockProgress.getPosition().getZ() != pos.getZ()) {
                    continue;
                }
                return destBlockProgress.getPartialBlockDamage();
            }
        }
        return 0;
    }
    
    private BlockPos traceToBlock(final float partialTicks, final float yaw) {
        final Vec3d pos = EntityUtil.interpolateEntity((Entity)Components.mc.player, partialTicks);
        final Vec3d dir = MathUtil.direction(yaw);
        return new BlockPos(pos.x + dir.x, pos.y, pos.z + dir.z);
    }
    
    private BlockPos traceToBlock(final float partialTicks, final float yaw, final Entity player) {
        final Vec3d pos = EntityUtil.interpolateEntity(player, partialTicks);
        final Vec3d dir = MathUtil.direction(yaw);
        return new BlockPos(pos.x + dir.x, pos.y, pos.z + dir.z);
    }
    
    private Block getBlock(final BlockPos pos) {
        final Block block = Components.mc.world.getBlockState(pos).getBlock();
        if (block == Blocks.BEDROCK || block == Blocks.OBSIDIAN) {
            return block;
        }
        return Blocks.AIR;
    }
    
    private void drawBlock(final Block block, final float x, final float y) {
        final ItemStack stack = new ItemStack(block);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.translate(x, y, 0.0f);
        Components.mc.getRenderItem().zLevel = 501.0f;
        Components.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, 0, 0);
        Components.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }
    
    public void renderInventory() {
        this.boxrender(this.invX.getValue() + this.fineinvX.getValue(), this.invY.getValue() + this.fineinvY.getValue());
        this.itemrender((NonNullList<ItemStack>)Components.mc.player.inventory.mainInventory, this.invX.getValue() + this.fineinvX.getValue(), this.invY.getValue() + this.fineinvY.getValue());
    }
    
    private void boxrender(final int x, final int y) {
        preboxrender();
        Components.mc.renderEngine.bindTexture(Components.box);
        RenderUtil.drawTexturedRect(x, y, 0, 0, 176, 16, 500);
        RenderUtil.drawTexturedRect(x, y + 16, 0, 16, 176, 54 + this.invH.getValue(), 500);
        RenderUtil.drawTexturedRect(x, y + 16 + 54, 0, 160, 176, 8, 500);
        postboxrender();
    }
    
    private void itemrender(final NonNullList<ItemStack> items, final int x, final int y) {
        for (int i = 0; i < items.size() - 9; ++i) {
            final int iX = x + i % 9 * 18 + 8;
            final int iY = y + i / 9 * 18 + 18;
            final ItemStack itemStack = (ItemStack)items.get(i + 9);
            preitemrender();
            Components.mc.getRenderItem().zLevel = 501.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, iX, iY);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(Components.mc.fontRenderer, itemStack, iX, iY, (String)null);
            Components.mc.getRenderItem().zLevel = 0.0f;
            postitemrender();
        }
        if (this.renderXCarry.getValue()) {
            for (int i = 1; i < 5; ++i) {
                final int iX = x + (i + 4) % 9 * 18 + 8;
                final ItemStack itemStack2 = Components.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (!itemStack2.isEmpty) {
                    preitemrender();
                    Components.mc.getRenderItem().zLevel = 501.0f;
                    RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack2, iX, y + 1);
                    RenderUtil.itemRender.renderItemOverlayIntoGUI(Components.mc.fontRenderer, itemStack2, iX, y + 1, (String)null);
                    Components.mc.getRenderItem().zLevel = 0.0f;
                    postitemrender();
                }
            }
        }
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
        Components.logo = new ResourceLocation("textures/CrepeLogo1.png");
        Components.logo2 = new ResourceLocation("textures/back.png");
    }
    
    public enum TargetHudDesign
    {
        NORMAL, 
        COMPACT;
    }
    
    public enum Compass
    {
        NONE, 
        CIRCLE, 
        LINE;
    }
    
    private enum Direction
    {
        N, 
        W, 
        S, 
        E;
    }
}
