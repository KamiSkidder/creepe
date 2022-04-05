//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.Feature;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumHand;
import me.alpha432.oyvey.util.BlockUtil;
import me.alpha432.oyvey.util.InventoryUtil;
import net.minecraft.block.BlockObsidian;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class SelfAnvil extends Module
{
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> onlyHole;
    private final Setting<Boolean> helpingBlocks;
    private final Setting<Boolean> chat;
    private final Setting<Boolean> packet;
    private final Setting<Integer> blocksPerTick;
    private BlockPos placePos;
    private BlockPos playerPos;
    private int blockSlot;
    private int obbySlot;
    private int lastBlock;
    private int blocksThisTick;
    
    public SelfAnvil() {
        super("SelfAnvil", "funne falling block", Category.COMBAT, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", true));
        this.onlyHole = (Setting<Boolean>)this.register(new Setting("HoleOnly", false));
        this.helpingBlocks = (Setting<Boolean>)this.register(new Setting("HelpingBlocks", true));
        this.chat = (Setting<Boolean>)this.register(new Setting("Chat Msgs", true));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", false));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("Blocks/Tick", 2, 1, 8));
    }
    
    @Override
    public void onEnable() {
        this.playerPos = new BlockPos(SelfAnvil.mc.player.posX, SelfAnvil.mc.player.posY, SelfAnvil.mc.player.posZ);
        this.placePos = this.playerPos.offset(EnumFacing.UP, 2);
        this.blockSlot = this.findBlockSlot();
        this.obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        this.lastBlock = SelfAnvil.mc.player.inventory.currentItem;
        if (!this.doFirstChecks()) {
            this.disable();
        }
    }
    
    @Override
    public void onTick() {
        this.blocksThisTick = 0;
        this.doSelfAnvil();
    }
    
    private void doSelfAnvil() {
        if (this.helpingBlocks.getValue() && BlockUtil.isPositionPlaceable(this.placePos, false, true) == 2) {
            InventoryUtil.switchToHotbarSlot(this.obbySlot, false);
            this.doHelpBlocks();
        }
        if (this.blocksThisTick < this.blocksPerTick.getValue() && BlockUtil.isPositionPlaceable(this.placePos, false, true) == 3) {
            InventoryUtil.switchToHotbarSlot(this.blockSlot, false);
            BlockUtil.placeBlock(this.placePos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
            InventoryUtil.switchToHotbarSlot(this.lastBlock, false);
            SelfAnvil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfAnvil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.disable();
        }
    }
    
    private void doHelpBlocks() {
        if (this.blocksThisTick >= this.blocksPerTick.getValue()) {
            return;
        }
        for (final EnumFacing side1 : EnumFacing.values()) {
            if (side1 != EnumFacing.DOWN) {
                if (BlockUtil.isPositionPlaceable(this.placePos.offset(side1), false, true) == 3) {
                    BlockUtil.placeBlock(this.placePos.offset(side1), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
                    ++this.blocksThisTick;
                    return;
                }
            }
        }
        for (final EnumFacing side1 : EnumFacing.values()) {
            if (side1 != EnumFacing.DOWN) {
                for (final EnumFacing side2 : EnumFacing.values()) {
                    if (BlockUtil.isPositionPlaceable(this.placePos.offset(side1).offset(side2), false, true) == 3) {
                        BlockUtil.placeBlock(this.placePos.offset(side1).offset(side2), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
                        ++this.blocksThisTick;
                        return;
                    }
                }
            }
        }
        for (final EnumFacing side1 : EnumFacing.values()) {
            for (final EnumFacing side2 : EnumFacing.values()) {
                for (final EnumFacing side3 : EnumFacing.values()) {
                    if (BlockUtil.isPositionPlaceable(this.placePos.offset(side1).offset(side2).offset(side3), false, true) == 3) {
                        BlockUtil.placeBlock(this.placePos.offset(side1).offset(side2).offset(side3), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
                        ++this.blocksThisTick;
                        return;
                    }
                }
            }
        }
    }
    
    private int findBlockSlot() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = SelfAnvil.mc.player.inventory.getStackInSlot(i);
            if (item.getItem() instanceof ItemBlock) {
                final Block block = Block.getBlockFromItem(SelfAnvil.mc.player.inventory.getStackInSlot(i).getItem());
                if (block instanceof BlockFalling) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private boolean doFirstChecks() {
        final int canPlace = BlockUtil.isPositionPlaceable(this.placePos, false, true);
        if (Feature.fullNullCheck() || !SelfAnvil.mc.world.isAirBlock(this.playerPos)) {
            return false;
        }
        if (!BlockUtil.isBothHole(this.playerPos) && this.onlyHole.getValue()) {
            return false;
        }
        if (this.blockSlot == -1) {
            if (this.chat.getValue()) {
                Command.sendMessage("<" + this.getDisplayName() + "> §cNo Anvils in hotbar.");
            }
            return false;
        }
        if (canPlace == 2) {
            if (!this.helpingBlocks.getValue()) {
                if (this.chat.getValue()) {
                    Command.sendMessage("<" + this.getDisplayName() + "> §cNowhere to place.");
                }
                return false;
            }
            if (this.obbySlot == -1) {
                if (this.chat.getValue()) {
                    Command.sendMessage("<" + this.getDisplayName() + "> §cNo Obsidian in hotbar.");
                }
                return false;
            }
        }
        else if (canPlace != 3) {
            if (this.chat.getValue()) {
                Command.sendMessage("<" + this.getDisplayName() + "> §cNot enough room.");
            }
            return false;
        }
        return true;
    }
}
