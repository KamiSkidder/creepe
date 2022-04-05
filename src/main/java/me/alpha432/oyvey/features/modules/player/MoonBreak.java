//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.player;

import net.minecraft.util.EnumHand;
import me.alpha432.oyvey.util.BlockUtil;
import me.alpha432.oyvey.event.events.PlayerDamageBlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.alpha432.oyvey.event.events2.PacketEventM;
import me.alpha432.oyvey.util.RenderUtil;
import java.awt.Color;
import me.alpha432.oyvey.event.events.Render3DEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import me.alpha432.oyvey.util.InventoryUtil;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import me.alpha432.oyvey.features.Feature;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class MoonBreak extends Module
{
    private static MoonBreak INSTANCE;
    private Setting<Boolean> creativeMode;
    private Setting<Boolean> ghostHand;
    private Setting<Boolean> fastUpdate;
    private Setting<Boolean> render;
    private boolean $Cancel;
    private BlockPos breakPos;
    private EnumFacing $Facing;
    
    public MoonBreak() {
        super("BREAK", "MoonBreak", Category.PLAYER, true, false, false);
        this.creativeMode = (Setting<Boolean>)this.register(new Setting("CreativeMode", true));
        this.ghostHand = (Setting<Boolean>)this.register(new Setting("GhostHand", true, v -> this.creativeMode.getValue()));
        this.fastUpdate = (Setting<Boolean>)this.register(new Setting("FastUpDate", true, v -> this.creativeMode.getValue() && !this.ghostHand.getValue()));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", true));
        this.$Cancel = false;
        this.setInstance();
    }
    
    public static MoonBreak getInstance() {
        if (MoonBreak.INSTANCE == null) {
            MoonBreak.INSTANCE = new MoonBreak();
        }
        return MoonBreak.INSTANCE;
    }
    
    private void setInstance() {
        MoonBreak.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (!Feature.fullNullCheck()) {
            if (this.breakPos != null && this.creativeMode.getValue() && MoonBreak.mc.world.getBlockState(this.breakPos).getBlock() != Blocks.AIR) {
                if (this.ghostHand.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1) {
                    final int slotMain = MoonBreak.mc.player.inventory.currentItem;
                    MoonBreak.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                    MoonBreak.mc.playerController.updateController();
                    MoonBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, this.$Facing));
                    MoonBreak.mc.player.inventory.currentItem = slotMain;
                    MoonBreak.mc.playerController.updateController();
                }
                else {
                    MoonBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, this.$Facing));
                    if (this.fastUpdate.getValue()) {
                        MoonBreak.mc.world.setBlockToAir(this.breakPos);
                    }
                }
            }
            MoonBreak.mc.playerController.blockHitDelay = 0;
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        if (!Feature.fullNullCheck() && this.breakPos != null && this.render.getValue()) {
            if (MoonBreak.mc.world.getBlockState(this.breakPos).getBlock() != Blocks.AIR) {
                RenderUtil.drawBoxESP(this.breakPos, new Color(255, 0, 0, 255), false, new Color(255, 0, 0, 255), 1.0f, true, true, 84, false);
            }
            else {
                RenderUtil.drawBoxESP(this.breakPos, new Color(0, 255, 0, 255), false, new Color(0, 255, 0, 255), 1.0f, true, true, 84, false);
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEventM.Send event) {
        if (!Feature.fullNullCheck() && event.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging packet = (CPacketPlayerDigging)event.getPacket();
            if (packet.getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK && this.$Cancel) {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockEvent(final PlayerDamageBlockEvent event) {
        if (!Feature.fullNullCheck()) {
            if (event.getStage() == 1 && MoonBreak.mc.playerController.curBlockDamageMP > 0.1f) {
                MoonBreak.mc.playerController.isHittingBlock = true;
            }
            if (event.getStage() == 0 && BlockUtil.canBreak(event.pos)) {
                MoonBreak.mc.playerController.isHittingBlock = false;
                if (this.breakPos != null && new BlockPos(event.pos.getX(), event.pos.getY(), event.pos.getZ()) == new BlockPos(this.breakPos.getX(), this.breakPos.getY(), this.breakPos.getZ())) {
                    this.$Cancel = true;
                }
                else {
                    this.$Cancel = false;
                    MoonBreak.mc.player.swingArm(EnumHand.MAIN_HAND);
                    MoonBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.pos, event.facing));
                }
                MoonBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.pos, event.facing));
                this.breakPos = event.pos;
                this.$Facing = event.facing;
                event.setCanceled(true);
            }
        }
    }
    
    @Override
    public String getDisplayInfo() {
        return this.ghostHand.getValue() ? "Ghost" : "Normal";
    }
    
    static {
        MoonBreak.INSTANCE = new MoonBreak();
    }
}
