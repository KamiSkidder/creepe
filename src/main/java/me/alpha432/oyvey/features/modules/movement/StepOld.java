//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.movement;

import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockAir;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class StepOld extends Module
{
    private static StepOld instance;
    final double[] twoFiveOffset;
    private final double[] oneblockPositions;
    private final double[] twoblockPositions;
    private final double[] futurePositions;
    private final double[] fourBlockPositions;
    public Setting<Boolean> vanilla;
    public Setting<Float> stepHeightVanilla;
    public Setting<Integer> stepHeight;
    public Setting<Boolean> spoof;
    public Setting<Integer> ticks;
    public Setting<Boolean> turnOff;
    public Setting<Boolean> check;
    public Setting<Boolean> small;
    private double[] selectedPositions;
    private int packets;
    
    public StepOld() {
        super("Step", "Allows you to step up blocks", Category.MOVEMENT, true, false, false);
        this.twoFiveOffset = new double[] { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
        this.oneblockPositions = new double[] { 0.42, 0.75 };
        this.twoblockPositions = new double[] { 0.4, 0.75, 0.5, 0.41, 0.83, 1.16, 1.41, 1.57, 1.58, 1.42 };
        this.futurePositions = new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
        this.fourBlockPositions = new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43, 1.78, 1.63, 1.51, 1.9, 2.21, 2.45, 2.43, 2.78, 2.63, 2.51, 2.9, 3.21, 3.45, 3.43 };
        this.vanilla = (Setting<Boolean>)this.register(new Setting("Vanilla", false));
        this.stepHeightVanilla = (Setting<Float>)this.register(new Setting("VHeight", 2.0f, 0.1f, 5.0f, v -> this.vanilla.getValue()));
        this.stepHeight = (Setting<Integer>)this.register(new Setting("Height", 2, 1, 5, v -> !this.vanilla.getValue()));
        this.spoof = (Setting<Boolean>)this.register(new Setting("Spoof", Boolean.TRUE, v -> !this.vanilla.getValue()));
        this.ticks = (Setting<Integer>)this.register(new Setting("Delay", 3, 0, 25, v -> this.spoof.getValue() && !this.vanilla.getValue()));
        this.turnOff = (Setting<Boolean>)this.register(new Setting("Disable", Boolean.FALSE, v -> !this.vanilla.getValue()));
        this.check = (Setting<Boolean>)this.register(new Setting("Check", Boolean.TRUE, v -> !this.vanilla.getValue()));
        this.small = (Setting<Boolean>)this.register(new Setting("Offset", Boolean.FALSE, v -> this.stepHeight.getValue() > 1 && !this.vanilla.getValue()));
        this.selectedPositions = new double[0];
        StepOld.instance = this;
    }
    
    public static StepOld getInstance() {
        if (StepOld.instance == null) {
            StepOld.instance = new StepOld();
        }
        return StepOld.instance;
    }
    
    @Override
    public void onToggle() {
        StepOld.mc.player.stepHeight = 0.6f;
    }
    
    @Override
    public void onUpdate() {
        if (this.vanilla.getValue()) {
            StepOld.mc.player.stepHeight = this.stepHeightVanilla.getValue();
            return;
        }
        switch (this.stepHeight.getValue()) {
            case 1: {
                this.selectedPositions = this.oneblockPositions;
                break;
            }
            case 2: {
                this.selectedPositions = (this.small.getValue() ? this.twoblockPositions : this.futurePositions);
                break;
            }
            case 3: {
                this.selectedPositions = this.twoFiveOffset;
            }
            case 4: {
                this.selectedPositions = this.fourBlockPositions;
                break;
            }
        }
        if (StepOld.mc.player.collidedHorizontally && StepOld.mc.player.onGround) {
            ++this.packets;
        }
        final AxisAlignedBB bb = StepOld.mc.player.getEntityBoundingBox();
        if (this.check.getValue()) {
            for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX + 1.0); ++x) {
                for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.maxZ + 1.0); ++z) {
                    final Block block = StepOld.mc.world.getBlockState(new BlockPos((double)x, bb.maxY + 1.0, (double)z)).getBlock();
                    if (!(block instanceof BlockAir)) {
                        return;
                    }
                }
            }
        }
        if (StepOld.mc.player.onGround && !StepOld.mc.player.isInsideOfMaterial(Material.WATER) && !StepOld.mc.player.isInsideOfMaterial(Material.LAVA) && StepOld.mc.player.collidedVertically && StepOld.mc.player.fallDistance == 0.0f && !StepOld.mc.gameSettings.keyBindJump.pressed && StepOld.mc.player.collidedHorizontally && !StepOld.mc.player.isOnLadder() && (this.packets > this.selectedPositions.length - 2 || (this.spoof.getValue() && this.packets > this.ticks.getValue()))) {
            for (final double position : this.selectedPositions) {
                StepOld.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(StepOld.mc.player.posX, StepOld.mc.player.posY + position, StepOld.mc.player.posZ, true));
            }
            StepOld.mc.player.setPosition(StepOld.mc.player.posX, StepOld.mc.player.posY + this.selectedPositions[this.selectedPositions.length - 1], StepOld.mc.player.posZ);
            this.packets = 0;
            if (this.turnOff.getValue()) {
                this.disable();
            }
        }
    }
}
