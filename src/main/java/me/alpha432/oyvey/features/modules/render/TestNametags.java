//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.OyVey;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import me.alpha432.oyvey.util.EntityUtil;
import net.minecraft.enchantment.Enchantment;
import me.alpha432.oyvey.util.DamageUtil;
import net.minecraft.nbt.NBTTagList;
import java.awt.Color;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Iterator;
import me.alpha432.oyvey.util.RotationUtil;
import net.minecraft.entity.player.EntityPlayer;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class TestNametags extends Module
{
    public static TestNametags INSTANCE;
    private final Setting<Boolean> health;
    private final Setting<Boolean> armor;
    private final Setting<Boolean> reverseArmor;
    private final Setting<Boolean> topEnchant;
    private final Setting<Float> scaling;
    private final Setting<Boolean> invisibles;
    private final Setting<Boolean> ping;
    private final Setting<Boolean> gamemode;
    private final Setting<Boolean> entityID;
    private final Setting<Boolean> rect;
    private final Setting<Boolean> outline;
    private final Setting<Integer> redSetting;
    private final Setting<Integer> greenSetting;
    private final Setting<Integer> blueSetting;
    private final Setting<Integer> alphaSetting;
    private final Setting<Float> lineWidth;
    private final Setting<Boolean> sneak;
    private final Setting<Boolean> heldStackName;
    private final Setting<Boolean> whiter;
    private final Setting<Boolean> onlyFov;
    private final Setting<Boolean> scaleing;
    private final Setting<Float> factor;
    private final Setting<Boolean> smartScale;
    
    public TestNametags() {
        super("Nametag", "Let's try to fix nametags!", Category.RENDER, true, false, true);
        this.health = (Setting<Boolean>)this.register(new Setting("Health", Boolean.TRUE));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", Boolean.TRUE));
        this.reverseArmor = (Setting<Boolean>)this.register(new Setting("ReverseArmor", Boolean.FALSE));
        this.topEnchant = (Setting<Boolean>)this.register(new Setting("TopEnchant", Boolean.FALSE));
        this.scaling = (Setting<Float>)this.register(new Setting("Size", 0.3f, 0.1f, 20.0f));
        this.invisibles = (Setting<Boolean>)this.register(new Setting("Invisibles", Boolean.FALSE));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", Boolean.TRUE));
        this.gamemode = (Setting<Boolean>)this.register(new Setting("Gamemode", Boolean.FALSE));
        this.entityID = (Setting<Boolean>)this.register(new Setting("ID", Boolean.FALSE));
        this.rect = (Setting<Boolean>)this.register(new Setting("Rectangle", Boolean.TRUE));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", Boolean.FALSE, v -> this.rect.getValue()));
        this.redSetting = (Setting<Integer>)this.register(new Setting("Red", 255, 0, 255, v -> this.outline.getValue()));
        this.greenSetting = (Setting<Integer>)this.register(new Setting("Green", 255, 0, 255, v -> this.outline.getValue()));
        this.blueSetting = (Setting<Integer>)this.register(new Setting("Blue", 255, 0, 255, v -> this.outline.getValue()));
        this.alphaSetting = (Setting<Integer>)this.register(new Setting("Alpha", 255, 0, 255, v -> this.outline.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", 1.5f, 0.1f, 5.0f, v -> this.outline.getValue()));
        this.sneak = (Setting<Boolean>)this.register(new Setting("SneakColor", Boolean.FALSE));
        this.heldStackName = (Setting<Boolean>)this.register(new Setting("StackName", Boolean.FALSE));
        this.whiter = (Setting<Boolean>)this.register(new Setting("White", Boolean.FALSE));
        this.onlyFov = (Setting<Boolean>)this.register(new Setting("OnlyFov", Boolean.FALSE));
        this.scaleing = (Setting<Boolean>)this.register(new Setting("Scale", Boolean.FALSE));
        this.factor = (Setting<Float>)this.register(new Setting("Factor", 0.3f, 0.1f, 1.0f, v -> this.scaleing.getValue()));
        this.smartScale = (Setting<Boolean>)this.register(new Setting("SmartScale", Boolean.FALSE, v -> this.scaleing.getValue()));
        TestNametags.INSTANCE = this;
    }
    
    public static TestNametags getInstance() {
        if (TestNametags.INSTANCE == null) {
            TestNametags.INSTANCE = new TestNametags();
        }
        return TestNametags.INSTANCE;
    }
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        if (!Feature.fullNullCheck()) {
            for (final EntityPlayer player : TestNametags.mc.world.playerEntities) {
                if (player != null && !player.equals((Object)TestNametags.mc.player) && player.isEntityAlive() && (!player.isInvisible() || this.invisibles.getValue())) {
                    if (this.onlyFov.getValue() && !RotationUtil.isInFov(player)) {
                        continue;
                    }
                    final double x = this.interpolate(player.lastTickPosX, player.posX, event.getPartialTicks()) - TestNametags.mc.getRenderManager().renderPosX;
                    final double y = this.interpolate(player.lastTickPosY, player.posY, event.getPartialTicks()) - TestNametags.mc.getRenderManager().renderPosY;
                    final double z = this.interpolate(player.lastTickPosZ, player.posZ, event.getPartialTicks()) - TestNametags.mc.getRenderManager().renderPosZ;
                    this.renderProperNameTag(player, x, y, z, event.getPartialTicks());
                }
            }
        }
    }
    
    private void renderProperNameTag(final EntityPlayer player, final double x, final double y, final double z, final float delta) {
        double tempY = y;
        tempY += (player.isSneaking() ? 0.5 : 0.7);
        final Entity camera = TestNametags.mc.getRenderViewEntity();
        assert camera != null;
        final double originalPositionX = camera.posX;
        final double originalPositionY = camera.posY;
        final double originalPositionZ = camera.posZ;
        camera.posX = this.interpolate(camera.prevPosX, camera.posX, delta);
        camera.posY = this.interpolate(camera.prevPosY, camera.posY, delta);
        camera.posZ = this.interpolate(camera.prevPosZ, camera.posZ, delta);
        final String displayTag = this.getDisplayTag(player);
        final double distance = camera.getDistance(x + TestNametags.mc.getRenderManager().viewerPosX, y + TestNametags.mc.getRenderManager().viewerPosY, z + TestNametags.mc.getRenderManager().viewerPosZ);
        final int width = this.renderer.getStringWidth(displayTag) / 2;
        double scale = (0.0018 + this.scaling.getValue() * (distance * this.factor.getValue())) / 1000.0;
        if (distance <= 8.0 && this.smartScale.getValue()) {
            scale = 0.0245;
        }
        if (!this.scaleing.getValue()) {
            scale = this.scaling.getValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)tempY + 1.4f, (float)z);
        GlStateManager.rotate(-TestNametags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(TestNametags.mc.getRenderManager().playerViewX, (TestNametags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        if (this.rect.getValue()) {
            this.drawRect((float)(-width - 2), (float)(-(TestNametags.mc.fontRenderer.FONT_HEIGHT + 1)), width + 2.0f, 1.5f, 1426063360);
            if (this.outline.getValue()) {}
        }
        GlStateManager.enableAlpha();
        final ItemStack renderMainHand = player.getHeldItemMainhand().copy();
        if (!renderMainHand.hasEffect() || renderMainHand.getItem() instanceof ItemTool || renderMainHand.getItem() instanceof ItemArmor) {}
        if (this.heldStackName.getValue() && !renderMainHand.isEmpty && renderMainHand.getItem() != Items.AIR) {
            final String stackName = renderMainHand.getDisplayName();
            final int stackNameWidth = this.renderer.getStringWidth(stackName) / 2;
            GL11.glPushMatrix();
            GL11.glScalef(0.75f, 0.75f, 0.0f);
            this.renderer.drawStringWithShadow(stackName, (float)(-stackNameWidth), -(this.getBiggestArmorTag(player) + 20.0f), -1);
            GL11.glScalef(1.5f, 1.5f, 1.0f);
            GL11.glPopMatrix();
        }
        final ArrayList<ItemStack> armorInventory = new ArrayList<ItemStack>((Collection<? extends ItemStack>)player.inventory.armorInventory);
        if (this.reverseArmor.getValue()) {
            Collections.reverse(armorInventory);
        }
        GlStateManager.pushMatrix();
        int xOffset = -8;
        for (final ItemStack stack : armorInventory) {
            if (stack != null) {
                xOffset -= 8;
            }
        }
        xOffset -= 8;
        final ItemStack renderOffhand = player.getHeldItemOffhand().copy();
        if (!renderOffhand.hasEffect() || renderOffhand.getItem() instanceof ItemTool || renderOffhand.getItem() instanceof ItemArmor) {}
        this.renderItemStack(player, renderOffhand, xOffset, -26, this.armor.getValue());
        xOffset += 16;
        for (final ItemStack stack2 : armorInventory) {
            if (stack2 == null) {
                continue;
            }
            final ItemStack armourStack = stack2.copy();
            if (!armourStack.hasEffect() || armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor) {}
            this.renderItemStack(player, armourStack, xOffset, -26, this.armor.getValue());
            xOffset += 16;
        }
        this.renderItemStack(player, renderMainHand, xOffset, -26, this.armor.getValue());
        GlStateManager.popMatrix();
        this.renderer.drawStringWithShadow(displayTag, (float)(-width), (float)(-(this.renderer.getFontHeight() - 1)), this.getDisplayColour(player));
        camera.posX = originalPositionX;
        camera.posY = originalPositionY;
        camera.posZ = originalPositionZ;
        GlStateManager.enableDepth();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    private void renderNameTag(final EntityPlayer player, final double x, final double y, final double z, final float partialTicks) {
        final double tempY = y + (player.isSneaking() ? 0.5 : 0.7);
        final Entity camera = TestNametags.mc.getRenderViewEntity();
        assert camera != null;
        final double originalPositionX = camera.posX;
        final double originalPositionY = camera.posY;
        final double originalPositionZ = camera.posZ;
        camera.posX = this.interpolate(camera.prevPosX, camera.posX, partialTicks);
        camera.posY = this.interpolate(camera.prevPosY, camera.posY, partialTicks);
        camera.posZ = this.interpolate(camera.prevPosZ, camera.posZ, partialTicks);
        final double distance = camera.getDistance(x + TestNametags.mc.getRenderManager().viewerPosX, y + TestNametags.mc.getRenderManager().viewerPosY, z + TestNametags.mc.getRenderManager().viewerPosZ);
        final int width = TestNametags.mc.fontRenderer.getStringWidth(this.getDisplayTag(player)) / 2;
        final double scale = (0.0018 + this.scaling.getValue() * distance) / 50.0;
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)tempY + 1.4f, (float)z);
        GlStateManager.rotate(-TestNametags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        final float thirdPersonOffset = (TestNametags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f;
        GlStateManager.rotate(TestNametags.mc.getRenderManager().playerViewX, thirdPersonOffset, 0.0f, 0.0f);
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        this.drawRect((float)(-width - 2), (float)(-(TestNametags.mc.fontRenderer.FONT_HEIGHT + 1)), width + 2.0f, 1.5f, 1426063360);
        GlStateManager.enableAlpha();
        TestNametags.mc.fontRenderer.drawStringWithShadow(this.getDisplayTag(player), (float)(-width), (float)(-(TestNametags.mc.fontRenderer.FONT_HEIGHT - 1)), this.getNameColor((Entity)player).getRGB());
        if (this.armor.getValue()) {
            GlStateManager.pushMatrix();
            final double changeValue = 16.0;
            int xOffset = 0;
            xOffset -= (int)(changeValue / 2.0 * player.inventory.armorInventory.size());
            xOffset -= (int)(changeValue / 2.0);
            xOffset -= (int)(changeValue / 2.0);
            if (!player.getHeldItemMainhand().isEmpty()) {}
            xOffset += (int)changeValue;
            for (final ItemStack stack : player.inventory.armorInventory) {
                if (!stack.isEmpty()) {}
                xOffset += (int)changeValue;
            }
            if (!player.getHeldItemOffhand().isEmpty()) {}
            GlStateManager.popMatrix();
        }
        camera.posX = originalPositionX;
        camera.posY = originalPositionY;
        camera.posZ = originalPositionZ;
        GlStateManager.enableDepth();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    public void drawRect(final float x, final float y, final float w, final float h, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.lineWidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)x, (double)h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)w, (double)h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)w, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public void drawOutlineRect(final float x, final float y, final float w, final float h, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.lineWidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        bufferbuilder.begin(2, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)x, (double)h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)w, (double)h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)w, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    private Color getNameColor(final Entity entity) {
        return Color.WHITE;
    }
    
    private void renderItemStack(final EntityPlayer player, final ItemStack stack, final int x, final int y, final boolean item) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        TestNametags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        if (item) {
            TestNametags.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
            TestNametags.mc.getRenderItem().renderItemOverlays(TestNametags.mc.fontRenderer, stack, x, y);
        }
        TestNametags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        this.renderEnchantmentText(player, stack, x, y);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
    }
    
    private boolean shouldMoveArmor(final EntityPlayer player) {
        for (final ItemStack stack : player.inventory.armorInventory) {
            final NBTTagList enchants = stack.getEnchantmentTagList();
            if (enchants.tagCount() == 0) {
                continue;
            }
            return true;
        }
        final ItemStack renderMainHand = player.getHeldItemMainhand().copy();
        if (renderMainHand.hasEffect()) {
            return true;
        }
        final ItemStack renderOffHand = player.getHeldItemOffhand().copy();
        return renderMainHand.hasEffect();
    }
    
    private void renderEnchantmentText(final EntityPlayer player, final ItemStack stack, final int x, final int y) {
        int enchantmentY = (int)(y - 8 - (this.topEnchant.getValue() ? (this.getBiggestArmorTag(player) - this.getEnchantHeight(stack)) : 0.0f));
        if (stack.getItem() == Items.GOLDEN_APPLE && stack.hasEffect()) {
            this.renderer.drawStringWithShadow("god", (float)(x * 2), (float)enchantmentY, -3977919);
            enchantmentY -= 8;
        }
        if (DamageUtil.hasDurability(stack)) {
            final int percent = DamageUtil.getRoundedDamage(stack);
            final String color = (percent >= 60) ? "§a" : ((percent >= 25) ? "§e" : "§c");
            this.renderer.drawStringWithShadow(color + percent + "%", (float)(x * 2), (float)enchantmentY, -1);
        }
    }
    
    private float getEnchantHeight(final ItemStack stack) {
        float enchantHeight = 0.0f;
        final NBTTagList enchants = stack.getEnchantmentTagList();
        for (int index = 0; index < enchants.tagCount(); ++index) {
            final short id = enchants.getCompoundTagAt(index).getShort("id");
            final Enchantment enc = Enchantment.getEnchantmentByID((int)id);
            if (enc != null) {
                enchantHeight += 8.0f;
            }
        }
        return enchantHeight;
    }
    
    private float getBiggestArmorTag(final EntityPlayer player) {
        float enchantmentY = 0.0f;
        boolean arm = false;
        for (final ItemStack stack : player.inventory.armorInventory) {
            float encY = 0.0f;
            if (stack != null) {
                final NBTTagList enchants = stack.getEnchantmentTagList();
                for (int index = 0; index < enchants.tagCount(); ++index) {
                    final short id = enchants.getCompoundTagAt(index).getShort("id");
                    final Enchantment enc = Enchantment.getEnchantmentByID((int)id);
                    if (enc != null) {
                        encY += 8.0f;
                        arm = true;
                    }
                }
            }
            if (encY <= enchantmentY) {
                continue;
            }
            enchantmentY = encY;
        }
        final ItemStack renderMainHand = player.getHeldItemMainhand().copy();
        if (renderMainHand.hasEffect()) {
            float encY2 = 0.0f;
            final NBTTagList enchants2 = renderMainHand.getEnchantmentTagList();
            for (int index2 = 0; index2 < enchants2.tagCount(); ++index2) {
                final short id = enchants2.getCompoundTagAt(index2).getShort("id");
                final Enchantment enc2 = Enchantment.getEnchantmentByID((int)id);
                if (enc2 != null) {
                    encY2 += 8.0f;
                    arm = true;
                }
            }
            if (encY2 > enchantmentY) {
                enchantmentY = encY2;
            }
        }
        final ItemStack renderOffHand;
        if ((renderOffHand = player.getHeldItemOffhand().copy()).hasEffect()) {
            float encY2 = 0.0f;
            final NBTTagList enchants2 = renderOffHand.getEnchantmentTagList();
            for (int index = 0; index < enchants2.tagCount(); ++index) {
                final short id2 = enchants2.getCompoundTagAt(index).getShort("id");
                final Enchantment enc = Enchantment.getEnchantmentByID((int)id2);
                if (enc != null) {
                    encY2 += 8.0f;
                    arm = true;
                }
            }
            if (encY2 > enchantmentY) {
                enchantmentY = encY2;
            }
        }
        return (arm ? 0 : 20) + enchantmentY;
    }
    
    private String getDisplayTag(final EntityPlayer player) {
        String name = player.getDisplayName().getFormattedText();
        if (name.contains(TestNametags.mc.getSession().getUsername())) {
            name = "You";
        }
        if (!this.health.getValue()) {
            return name;
        }
        final float health = EntityUtil.getHealth((Entity)player);
        final String color = (health > 18.0f) ? "§a" : ((health > 16.0f) ? "§2" : ((health > 12.0f) ? "§e" : ((health > 8.0f) ? "§6" : ((health > 5.0f) ? "§c" : "§4"))));
        String pingStr = "";
        if (this.ping.getValue()) {
            try {
                final int responseTime = Objects.requireNonNull(TestNametags.mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime();
                pingStr = pingStr + responseTime + "ms ";
            }
            catch (Exception ex) {}
        }
        return name;
    }
    
    private int getDisplayColour(final EntityPlayer player) {
        int colour = -5592406;
        if (this.whiter.getValue()) {
            colour = -1;
        }
        if (OyVey.friendManager.isFriend(player)) {
            return -11157267;
        }
        if (player.isInvisible()) {
            colour = -1113785;
        }
        else if (player.isSneaking() && this.sneak.getValue()) {
            colour = -6481515;
        }
        return colour;
    }
    
    private double interpolate(final double previous, final double current, final float partialTicks) {
        return previous + (current - previous) * partialTicks;
    }
}
