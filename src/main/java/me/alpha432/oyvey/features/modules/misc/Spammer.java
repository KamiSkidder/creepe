//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.misc;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.apache.commons.lang3.RandomStringUtils;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Timerm;
import me.alpha432.oyvey.features.modules.Module;

public class Spammer extends Module
{
    private final Timerm timer;
    private Setting<String> custom;
    private Setting<Integer> random;
    private Setting<Integer> delay;
    
    public Spammer() {
        super("spammer", "Message", Category.MISC, true, false, false);
        this.timer = new Timerm();
        this.custom = (Setting<String>)this.register(new Setting("Custom", "Spammer"));
        this.random = (Setting<Integer>)this.register(new Setting("Random", 0, 0, 20));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", 100, 0, 5000));
    }
    
    @Override
    public void onTick() {
        if (!Feature.fullNullCheck() && this.timer.passedMs(this.delay.getValue())) {
            Spammer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(this.custom.getValue() + RandomStringUtils.randomAlphanumeric((int)this.random.getValue())));
            this.timer.reset();
        }
    }
}
