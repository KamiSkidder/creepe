//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class Wireframe extends Module
{
    private static Wireframe INSTANCE;
    public final Setting<Float> alpha;
    public final Setting<Float> cAlpha;
    public final Setting<Float> lineWidth;
    public final Setting<Float> crystalLineWidth;
    public Setting<RenderMode> mode;
    public Setting<RenderMode> cMode;
    public Setting<Boolean> players;
    public Setting<Boolean> playerModel;
    public Setting<Boolean> crystals;
    public Setting<Boolean> crystalModel;
    
    public Wireframe() {
        super("Wireframe", "Draws a wireframe esp around other players.", Category.RENDER, false, false, false);
        this.alpha = (Setting<Float>)this.register(new Setting("PAlpha", 255.0f, 0.1f, 255.0f));
        this.cAlpha = (Setting<Float>)this.register(new Setting("CAlpha", 255.0f, 0.1f, 255.0f));
        this.lineWidth = (Setting<Float>)this.register(new Setting("PLineWidth", 1.0f, 0.1f, 3.0f));
        this.crystalLineWidth = (Setting<Float>)this.register(new Setting("CLineWidth", 1.0f, 0.1f, 3.0f));
        this.mode = (Setting<RenderMode>)this.register(new Setting("PMode", RenderMode.SOLID));
        this.cMode = (Setting<RenderMode>)this.register(new Setting("CMode", RenderMode.SOLID));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", Boolean.FALSE));
        this.playerModel = (Setting<Boolean>)this.register(new Setting("PlayerModel", Boolean.FALSE));
        this.crystals = (Setting<Boolean>)this.register(new Setting("Crystals", Boolean.FALSE));
        this.crystalModel = (Setting<Boolean>)this.register(new Setting("CrystalModel", Boolean.FALSE));
        this.setInstance();
    }
    
    public static Wireframe getINSTANCE() {
        if (Wireframe.INSTANCE == null) {
            Wireframe.INSTANCE = new Wireframe();
        }
        return Wireframe.INSTANCE;
    }
    
    private void setInstance() {
        Wireframe.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onRenderPlayerEvent(final RenderPlayerEvent.Pre event) {
        event.getEntityPlayer().hurtTime = 0;
    }
    
    static {
        Wireframe.INSTANCE = new Wireframe();
    }
    
    public enum RenderMode
    {
        SOLID, 
        WIREFRAME;
    }
}
