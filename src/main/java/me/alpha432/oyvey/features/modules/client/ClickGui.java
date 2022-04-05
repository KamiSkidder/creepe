//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.alpha432.oyvey.features.command.Command;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.events.ClientEvent;
import net.minecraft.client.settings.GameSettings;
import me.alpha432.oyvey.features.gui.OyVeyGui;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class ClickGui extends Module
{
    private static ClickGui INSTANCE;
    public Setting<String> prefix;
    public Setting<Boolean> customFov;
    public Setting<Float> fov;
    public Setting<Boolean> dark;
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> hoverAlpha;
    public Setting<Integer> topRed;
    public Setting<Integer> topGreen;
    public Setting<Integer> topBlue;
    public Setting<Integer> alpha;
    public Setting<Boolean> rainbow;
    public Setting<rainbowMode> rainbowModeHud;
    public Setting<rainbowModeArray> rainbowModeA;
    public Setting<Integer> rainbowHue;
    public Setting<Float> rainbowBrightness;
    public Setting<Float> rainbowSaturation;
    public Setting<Boolean> particles;
    public Setting<Integer> particleLength;
    public Setting<Integer> particlered;
    public Setting<Integer> particlegreen;
    public Setting<Integer> particleblue;
    private OyVeyGui click;
    
    public ClickGui() {
        super("GUI", "Opens the ClickGui", Category.CLIENT, true, false, false);
        this.prefix = (Setting<String>)this.register(new Setting("Prefix", "."));
        this.customFov = (Setting<Boolean>)this.register(new Setting("CustomFov", false));
        this.fov = (Setting<Float>)this.register(new Setting("Fov", 150.0f, (-180.0f), 180.0f));
        this.dark = (Setting<Boolean>)this.register(new Setting("Darken", false));
        this.red = (Setting<Integer>)this.register(new Setting("Red", 255, 0, 255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", 0, 0, 255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", 247, 0, 255));
        this.hoverAlpha = (Setting<Integer>)this.register(new Setting("Alpha", 102, 0, 255));
        this.topRed = (Setting<Integer>)this.register(new Setting("SecondRed", 0, 0, 255));
        this.topGreen = (Setting<Integer>)this.register(new Setting("SecondGreen", 0, 0, 255));
        this.topBlue = (Setting<Integer>)this.register(new Setting("SecondBlue", 0, 0, 255));
        this.alpha = (Setting<Integer>)this.register(new Setting("HoverAlpha", 164, 0, 255));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", false));
        this.rainbowModeHud = (Setting<rainbowMode>)this.register(new Setting("HRainbowMode", rainbowMode.Static, v -> this.rainbow.getValue()));
        this.rainbowModeA = (Setting<rainbowModeArray>)this.register(new Setting("ARainbowMode", rainbowModeArray.Static, v -> this.rainbow.getValue()));
        this.rainbowHue = (Setting<Integer>)this.register(new Setting("Delay", 240, 0, 600, v -> this.rainbow.getValue()));
        this.rainbowBrightness = (Setting<Float>)this.register(new Setting("Brightness ", 150.0f, 1.0f, 255.0f, v -> this.rainbow.getValue()));
        this.rainbowSaturation = (Setting<Float>)this.register(new Setting("Saturation", 150.0f, 1.0f, 255.0f, v -> this.rainbow.getValue()));
        this.particles = (Setting<Boolean>)this.register(new Setting("Particles", false));
        this.particleLength = (Setting<Integer>)this.register(new Setting("ParticleLength", 80, 0, 300, v -> this.particles.getValue()));
        this.particlered = (Setting<Integer>)this.register(new Setting("ParticleRed", 255, 0, 255, v -> this.particles.getValue()));
        this.particlegreen = (Setting<Integer>)this.register(new Setting("ParticleGreen", 255, 0, 255, v -> this.particles.getValue()));
        this.particleblue = (Setting<Integer>)this.register(new Setting("ParticleBlue", 255, 0, 255, v -> this.particles.getValue()));
        this.setInstance();
    }
    
    public static ClickGui getInstance() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                OyVey.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + ChatFormatting.DARK_GRAY + OyVey.commandManager.getPrefix());
            }
            OyVey.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }
    
    @Override
    public void onEnable() {
        ClickGui.mc.displayGuiScreen(OyVeyGui.getClickGui());
    }
    
    @Override
    public void onLoad() {
        OyVey.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        OyVey.commandManager.setPrefix(this.prefix.getValue());
    }
    
    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof OyVeyGui)) {
            this.disable();
        }
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
    }
    
    public enum rainbowModeArray
    {
        Static, 
        Up;
    }
    
    public enum rainbowMode
    {
        Static, 
        Sideway;
    }
    
    public enum Gui
    {
        NEW, 
        OLD;
    }
}
