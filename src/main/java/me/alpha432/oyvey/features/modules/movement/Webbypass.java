//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import org.lwjgl.input.Keyboard;
import me.alpha432.oyvey.OyVey;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import me.alpha432.oyvey.event.events.BlockCollisionBoundingBoxEvent;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class Webbypass extends Module
{
    public Setting<Boolean> disableBB;
    public Setting<Float> bbOffset;
    public Setting<Boolean> onGround;
    public Setting<Float> motionY;
    public Setting<Float> motionX;
    
    public Webbypass() {
        super("Webbypass", "aw", Category.MOVEMENT, true, false, false);
        this.disableBB = (Setting<Boolean>)this.register(new Setting("AddBB", true));
        this.bbOffset = (Setting<Float>)this.register(new Setting("BBOffset", 0.4f, (-2.0f), 2.0f));
        this.onGround = (Setting<Boolean>)this.register(new Setting("On Ground", true));
        this.motionY = (Setting<Float>)this.register(new Setting("Set MotionY", 0.0f, 0.0f, 20.0f));
        this.motionX = (Setting<Float>)this.register(new Setting("Set MotionX", 0.8f, (-1.0f), 5.0f));
    }
    
    @SubscribeEvent
    public void bbEvent(final BlockCollisionBoundingBoxEvent event) {
        if (nullCheck()) {
            return;
        }
        if (Webbypass.mc.world.getBlockState(event.getPos()).getBlock() instanceof BlockWeb && this.disableBB.getValue()) {
            event.setBoundingBox(Block.FULL_BLOCK_AABB.contract(0.0, (double)this.bbOffset.getValue(), 0.0));
        }
    }
    
    @Override
    public void onUpdate() {
        if ((Webbypass.mc.player.isInWeb && !OyVey.moduleManager.isModuleEnabled("Step")) || (Webbypass.mc.player.isInWeb && !OyVey.moduleManager.isModuleEnabled("StepTwo"))) {
            if (Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindSneak.keyCode)) {
                Webbypass.mc.player.isInWeb = true;
                final EntityPlayerSP player = Webbypass.mc.player;
                player.motionY *= this.motionY.getValue();
            }
            else if (this.onGround.getValue()) {
                Webbypass.mc.player.onGround = false;
            }
            if (Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindForward.keyCode) || Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindBack.keyCode) || Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindLeft.keyCode) || Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindRight.keyCode)) {
                Webbypass.mc.player.isInWeb = false;
                final EntityPlayerSP player2 = Webbypass.mc.player;
                player2.motionX *= this.motionX.getValue();
                final EntityPlayerSP player3 = Webbypass.mc.player;
                player3.motionZ *= this.motionX.getValue();
            }
        }
    }
}
