//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.movement;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayer;
import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraft.client.gui.GuiChat;
import me.alpha432.oyvey.event.events.KeyEvent;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import org.lwjgl.input.Keyboard;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.client.settings.KeyBinding;
import me.alpha432.oyvey.features.modules.Module;

public class NoSlowDown extends Module
{
    private static final KeyBinding[] keys;
    private static NoSlowDown INSTANCE;
    public final Setting<Double> webHorizontalFactor;
    public final Setting<Double> webVerticalFactor;
    public Setting<Boolean> guiMove;
    public Setting<Boolean> noSlow;
    public Setting<Boolean> soulSand;
    public Setting<Boolean> strict;
    public Setting<Boolean> sneakPacket;
    public Setting<Boolean> endPortal;
    public Setting<Boolean> webs;
    private boolean sneaking;
    
    public NoSlowDown() {
        super("NoSlow", "Prevents you from getting slowed down.", Category.MOVEMENT, true, false, false);
        this.webHorizontalFactor = (Setting<Double>)this.register(new Setting("WebHSpeed", 2.0, 0.0, 100.0));
        this.webVerticalFactor = (Setting<Double>)this.register(new Setting("WebVSpeed", 2.0, 0.0, 100.0));
        this.guiMove = (Setting<Boolean>)this.register(new Setting("GuiMove", true));
        this.noSlow = (Setting<Boolean>)this.register(new Setting("NoSlow", true));
        this.soulSand = (Setting<Boolean>)this.register(new Setting("SoulSand", true));
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", false));
        this.sneakPacket = (Setting<Boolean>)this.register(new Setting("SneakPacket", false));
        this.endPortal = (Setting<Boolean>)this.register(new Setting("EndPortal", false));
        this.webs = (Setting<Boolean>)this.register(new Setting("Webs", false));
        this.setInstance();
    }
    
    public static NoSlowDown getInstance() {
        if (NoSlowDown.INSTANCE == null) {
            NoSlowDown.INSTANCE = new NoSlowDown();
        }
        return NoSlowDown.INSTANCE;
    }
    
    private void setInstance() {
        NoSlowDown.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (this.guiMove.getValue()) {
            for (final KeyBinding bind : NoSlowDown.keys) {
                KeyBinding.setKeyBindState(bind.getKeyCode(), Keyboard.isKeyDown(bind.getKeyCode()));
            }
            if (NoSlowDown.mc.currentScreen == null) {
                for (final KeyBinding bind : NoSlowDown.keys) {
                    if (!Keyboard.isKeyDown(bind.getKeyCode())) {
                        KeyBinding.setKeyBindState(bind.getKeyCode(), false);
                    }
                }
            }
        }
        NoSlowDown.mc.player.getActiveItemStack().getItem();
        if (this.sneaking && !NoSlowDown.mc.player.isHandActive() && this.sneakPacket.getValue()) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowDown.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneaking = false;
        }
    }
    
    @SubscribeEvent
    public void onUseItem(final PlayerInteractEvent.RightClickItem event) {
        final Item item = NoSlowDown.mc.player.getHeldItem(event.getHand()).getItem();
        if ((item instanceof ItemFood || item instanceof ItemBow || (item instanceof ItemPotion && this.sneakPacket.getValue())) && !this.sneaking) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowDown.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.sneaking = true;
        }
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent event) {
        if (this.noSlow.getValue() && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            final MovementInput movementInput = event.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = event.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
    
    @SubscribeEvent
    public void onKeyEvent(final KeyEvent event) {
        if (!this.guiMove.getValue() || event.getStage() != 0 || !(NoSlowDown.mc.currentScreen instanceof GuiChat)) {}
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayer && this.strict.getValue() && this.noSlow.getValue() && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, new BlockPos(Math.floor(NoSlowDown.mc.player.posX), Math.floor(NoSlowDown.mc.player.posY), Math.floor(NoSlowDown.mc.player.posZ)), EnumFacing.DOWN));
        }
    }
    
    static {
        keys = new KeyBinding[] { NoSlowDown.mc.gameSettings.keyBindForward, NoSlowDown.mc.gameSettings.keyBindBack, NoSlowDown.mc.gameSettings.keyBindLeft, NoSlowDown.mc.gameSettings.keyBindRight, NoSlowDown.mc.gameSettings.keyBindJump, NoSlowDown.mc.gameSettings.keyBindSprint };
        NoSlowDown.INSTANCE = new NoSlowDown();
    }
}
