//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.alpha432.oyvey.event.ClientEvent;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.util.TextUtil;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class Management extends Module
{
    private static Management INSTANCE;
    public Setting<Boolean> betterFrames;
    public Setting<String> commandBracket;
    public Setting<String> commandBracket2;
    public Setting<String> command;
    public Setting<Boolean> rainbowPrefix;
    public Setting<TextUtil.Color> bracketColor;
    public Setting<TextUtil.Color> commandColor;
    public Setting<Integer> betterFPS;
    public Setting<Boolean> potions;
    public Setting<Integer> textRadarUpdates;
    public Setting<Integer> respondTime;
    public Setting<Integer> moduleListUpdates;
    public Setting<Float> holeRange;
    public Setting<Integer> holeUpdates;
    public Setting<Integer> holeSync;
    public Setting<Boolean> safety;
    public Setting<Integer> safetyCheck;
    public Setting<ThreadMode> holeThread;
    public Setting<Boolean> speed;
    public Setting<Boolean> oneDot15;
    public Setting<Boolean> tRadarInv;
    public Setting<Boolean> unfocusedCpu;
    public Setting<Integer> cpuFPS;
    public Setting<Integer> baritoneTimeOut;
    public Setting<Boolean> oneChunk;
    
    public Management() {
        super("Management", "ClientManagement", Category.CLIENT, false, false, true);
        this.betterFrames = (Setting<Boolean>)this.register(new Setting("BetterMaxFPS", false));
        this.commandBracket = (Setting<String>)this.register(new Setting("Bracket", "<"));
        this.commandBracket2 = (Setting<String>)this.register(new Setting("Bracket2", ">"));
        this.command = (Setting<String>)this.register(new Setting("Command", "Phobos.eu"));
        this.rainbowPrefix = (Setting<Boolean>)this.register(new Setting("RainbowPrefix", false));
        this.bracketColor = (Setting<TextUtil.Color>)this.register(new Setting("BColor", TextUtil.Color.BLUE));
        this.commandColor = (Setting<TextUtil.Color>)this.register(new Setting("CColor", TextUtil.Color.BLUE));
        this.betterFPS = (Setting<Integer>)this.register(new Setting("MaxFPS", 300, 30, 1000, v -> this.betterFrames.getValue()));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", true));
        this.textRadarUpdates = (Setting<Integer>)this.register(new Setting("TRUpdates", 500, 0, 1000));
        this.respondTime = (Setting<Integer>)this.register(new Setting("SeverTime", 500, 0, 1000));
        this.moduleListUpdates = (Setting<Integer>)this.register(new Setting("ALUpdates", 1000, 0, 1000));
        this.holeRange = (Setting<Float>)this.register(new Setting("HoleRange", 6.0f, 1.0f, 256.0f));
        this.holeUpdates = (Setting<Integer>)this.register(new Setting("HoleUpdates", 100, 0, 1000));
        this.holeSync = (Setting<Integer>)this.register(new Setting("HoleSync", 10000, 1, 10000));
        this.safety = (Setting<Boolean>)this.register(new Setting("SafetyPlayer", false));
        this.safetyCheck = (Setting<Integer>)this.register(new Setting("SafetyCheck", 50, 1, 150));
        this.holeThread = (Setting<ThreadMode>)this.register(new Setting("HoleThread", ThreadMode.WHILE));
        this.speed = (Setting<Boolean>)this.register(new Setting("Speed", true));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.15", false));
        this.tRadarInv = (Setting<Boolean>)this.register(new Setting("TRadarInv", true));
        this.unfocusedCpu = (Setting<Boolean>)this.register(new Setting("UnfocusedCPU", false));
        this.cpuFPS = (Setting<Integer>)this.register(new Setting("UnfocusedFPS", 60, 1, 60, v -> this.unfocusedCpu.getValue()));
        this.baritoneTimeOut = (Setting<Integer>)this.register(new Setting("Baritone", 5, 1, 20));
        this.oneChunk = (Setting<Boolean>)this.register(new Setting("OneChunk", false));
        this.setInstance();
    }
    
    public static Management getInstance() {
        if (Management.INSTANCE == null) {
            Management.INSTANCE = new Management();
        }
        return Management.INSTANCE;
    }
    
    private void setInstance() {
        Management.INSTANCE = this;
    }
    
    @Override
    public void onLoad() {
        OyVey.commandManager.setClientMessage(this.getCommandMessage());
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2) {
            if (this.oneChunk.getPlannedValue()) {
                Management.mc.gameSettings.renderDistanceChunks = 1;
            }
            if (event.getSetting() != null && this.equals(event.getSetting().getFeature())) {
                if (event.getSetting().equals(this.holeThread)) {}
                OyVey.commandManager.setClientMessage(this.getCommandMessage());
            }
        }
    }
    
    public String getCommandMessage() {
        if (this.rainbowPrefix.getPlannedValue()) {
            final StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
            stringBuilder.insert(0, "§+");
            stringBuilder.append("§r");
            return stringBuilder.toString();
        }
        return TextUtil.coloredString(this.commandBracket.getPlannedValue(), this.bracketColor.getPlannedValue()) + TextUtil.coloredString(this.command.getPlannedValue(), this.commandColor.getPlannedValue()) + TextUtil.coloredString(this.commandBracket2.getPlannedValue(), this.bracketColor.getPlannedValue());
    }
    
    public String getRainbowCommandMessage() {
        final StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
        stringBuilder.insert(0, "§+");
        stringBuilder.append("§r");
        return stringBuilder.toString();
    }
    
    public String getRawCommandMessage() {
        return this.commandBracket.getValue() + this.command.getValue() + this.commandBracket2.getValue();
    }
    
    static {
        Management.INSTANCE = new Management();
    }
    
    public enum ThreadMode
    {
        POOL, 
        WHILE, 
        NONE;
    }
}
