//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.util.RenderUtil;
import java.awt.Color;
import me.alpha432.oyvey.util.BlockUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class HoleESP extends Module
{
    private static HoleESP INSTANCE;
    private final Setting<Integer> range;
    private final Setting<Integer> rangeY;
    private final Setting<Integer> red;
    private final Setting<Integer> green;
    private final Setting<Integer> blue;
    private final Setting<Integer> alpha;
    private final Setting<Integer> boxAlpha;
    private final Setting<Float> lineWidth;
    private final Setting<Integer> safeRed;
    private final Setting<Integer> safeGreen;
    private final Setting<Integer> safeBlue;
    private final Setting<Integer> safeAlpha;
    public Setting<Boolean> future;
    public Setting<Boolean> fov;
    public Setting<Boolean> renderOwn;
    public Setting<Boolean> box;
    public Setting<Boolean> outline;
    private final Setting<Integer> cRed;
    private final Setting<Integer> cGreen;
    private final Setting<Integer> cBlue;
    private final Setting<Integer> cAlpha;
    private final Setting<Integer> safecRed;
    private final Setting<Integer> safecGreen;
    private final Setting<Integer> safecBlue;
    private final Setting<Integer> safecAlpha;
    
    public HoleESP() {
        super("HoleESP", "Shows safe spots.", Category.RENDER, false, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("RangeX", 0, 0, 10));
        this.rangeY = (Setting<Integer>)this.register(new Setting("RangeY", 0, 0, 10));
        this.red = (Setting<Integer>)this.register(new Setting("Red", 0, 0, 255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", 255, 0, 255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", 0, 0, 255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", 255, 0, 255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", 125, 0, 255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", 1.0f, 0.1f, 5.0f));
        this.safeRed = (Setting<Integer>)this.register(new Setting("BedrockRed", 0, 0, 255));
        this.safeGreen = (Setting<Integer>)this.register(new Setting("BedrockGreen", 255, 0, 255));
        this.safeBlue = (Setting<Integer>)this.register(new Setting("BedrockBlue", 0, 0, 255));
        this.safeAlpha = (Setting<Integer>)this.register(new Setting("BedrockAlpha", 255, 0, 255));
        this.future = (Setting<Boolean>)this.register(new Setting("FutureRender", true));
        this.fov = (Setting<Boolean>)this.register(new Setting("InFov", true));
        this.renderOwn = (Setting<Boolean>)this.register(new Setting("RenderOwn", true));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", true));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", true));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", 0, 0, 255, v -> this.outline.getValue()));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", 0, 0, 255, v -> this.outline.getValue()));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", 255, 0, 255, v -> this.outline.getValue()));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", 255, 0, 255, v -> this.outline.getValue()));
        this.safecRed = (Setting<Integer>)this.register(new Setting("OL-BedrockRed", 0, 0, 255, v -> this.outline.getValue()));
        this.safecGreen = (Setting<Integer>)this.register(new Setting("OL-BedrockGreen", 255, 0, 255, v -> this.outline.getValue()));
        this.safecBlue = (Setting<Integer>)this.register(new Setting("OL-BedrockBlue", 0, 0, 255, v -> this.outline.getValue()));
        this.safecAlpha = (Setting<Integer>)this.register(new Setting("OL-BedrockAlpha", 255, 0, 255, v -> this.outline.getValue()));
        this.setInstance();
    }
    
    public static HoleESP getInstance() {
        if (HoleESP.INSTANCE == null) {
            HoleESP.INSTANCE = new HoleESP();
        }
        return HoleESP.INSTANCE;
    }
    
    private void setInstance() {
        HoleESP.INSTANCE = this;
    }
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        assert HoleESP.mc.renderViewEntity != null;
        final Vec3i playerPos = new Vec3i(HoleESP.mc.renderViewEntity.posX, HoleESP.mc.renderViewEntity.posY, HoleESP.mc.renderViewEntity.posZ);
        for (int x = playerPos.getX() - this.range.getValue(); x < playerPos.getX() + this.range.getValue(); ++x) {
            for (int z = playerPos.getZ() - this.range.getValue(); z < playerPos.getZ() + this.range.getValue(); ++z) {
                for (int y = playerPos.getY() + this.rangeY.getValue(); y > playerPos.getY() - this.rangeY.getValue(); --y) {
                    final BlockPos pos = new BlockPos(x, y, z);
                    if (HoleESP.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) && (!pos.equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ)) || this.renderOwn.getValue())) {
                        if (BlockUtil.isPosInFov(pos) || !this.fov.getValue()) {
                            if (HoleESP.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK) {
                                RenderUtil.drawBoxESP(((boolean)this.future.getValue()) ? pos.down() : pos, new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), this.outline.getValue(), new Color(this.safecRed.getValue(), this.safecGreen.getValue(), this.safecBlue.getValue(), this.safecAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                            }
                            else if (BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.down()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.east()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.west()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.south()).getBlock())) {
                                if (BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(pos.north()).getBlock())) {
                                    RenderUtil.drawBoxESP(((boolean)this.future.getValue()) ? pos.down() : pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    static {
        HoleESP.INSTANCE = new HoleESP();
    }
}
