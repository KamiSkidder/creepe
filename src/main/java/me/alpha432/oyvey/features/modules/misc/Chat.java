//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.misc;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.Vec3i;
import java.util.Date;
import java.text.SimpleDateFormat;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.client.CPacketChatMessage;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.features.command.Command;
import net.minecraft.entity.player.EntityPlayer;
import me.alpha432.oyvey.util.TextUtil;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.Timer;
import me.alpha432.oyvey.features.modules.Module;

public class Chat extends Module
{
    private static Chat INSTANCE;
    private final Timer timer;
    private Setting<String> custom;
    public Setting<Suffix> suffix;
    public Setting<Boolean> clean;
    public Setting<Boolean> infinite;
    public Setting<Boolean> autoQMain;
    public Setting<Boolean> qNotification;
    public Setting<Integer> qDelay;
    public Setting<TextUtil.Color> timeStamps;
    public Setting<Boolean> rainbowTimeStamps;
    public Setting<TextUtil.Color> bracket;
    public Setting<Boolean> space;
    public Setting<Boolean> all;
    public Setting<Boolean> shrug;
    public Setting<Boolean> disability;
    
    public Chat() {
        super("Chat", "Modifies your chat", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.custom = (Setting<String>)this.register(new Setting("Custom", "custom suffix"));
        this.suffix = (Setting<Suffix>)this.register(new Setting("Suffix", Suffix.ONE, "Your Suffix."));
        this.clean = (Setting<Boolean>)this.register(new Setting("CleanChat", false, "Cleans your chat"));
        this.infinite = (Setting<Boolean>)this.register(new Setting("Infinite", false, "Makes your chat infinite."));
        this.autoQMain = (Setting<Boolean>)this.register(new Setting("AutoQMain", false, "Spams AutoQMain"));
        this.qNotification = (Setting<Boolean>)this.register(new Setting("QNotification", false, v -> this.autoQMain.getValue()));
        this.qDelay = (Setting<Integer>)this.register(new Setting("QDelay", 9, 1, 90, v -> this.autoQMain.getValue()));
        this.timeStamps = (Setting<TextUtil.Color>)this.register(new Setting("Time", TextUtil.Color.NONE));
        this.rainbowTimeStamps = (Setting<Boolean>)this.register(new Setting("RainbowTimeStamps", false, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.bracket = (Setting<TextUtil.Color>)this.register(new Setting("Bracket", TextUtil.Color.WHITE, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.space = (Setting<Boolean>)this.register(new Setting("Space", true, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.all = (Setting<Boolean>)this.register(new Setting("All", false, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.shrug = (Setting<Boolean>)this.register(new Setting("Shrug", false));
        this.disability = (Setting<Boolean>)this.register(new Setting("Disability", false));
        this.setInstance();
    }
    
    public static Chat getInstance() {
        if (Chat.INSTANCE == null) {
            Chat.INSTANCE = new Chat();
        }
        return Chat.INSTANCE;
    }
    
    private void setInstance() {
        Chat.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (this.shrug.getValue()) {
            Chat.mc.player.sendChatMessage(TextUtil.shrug);
            this.shrug.setValue(false);
        }
        if (this.autoQMain.getValue()) {
            if (!this.shouldSendMessage((EntityPlayer)Chat.mc.player)) {
                return;
            }
            if (this.qNotification.getValue()) {
                Command.sendMessage("awa");
            }
            Chat.mc.player.sendChatMessage("waw");
            this.timer.reset();
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getStage() == 0 && event.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage packet = event.getPacket();
            String s = packet.getMessage();
            if (s.startsWith("/")) {
                return;
            }
            switch (this.suffix.getValue()) {
                case ONE: {
                    s += " : \u1d04\u0280\u1d07\u1d18\u1d07 \u1d04\u029f\u026a\u1d07\u0274\u1d1b";
                    break;
                }
            }
            if (s.length() >= 256) {
                s = s.substring(0, 256);
            }
            packet.message = s;
        }
    }
    
    @SubscribeEvent
    public void onChatPacketReceive(final PacketEvent.Receive event) {
        if (event.getStage() != 0 || event.getPacket() instanceof SPacketChat) {}
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getStage() == 0 && this.timeStamps.getValue() != TextUtil.Color.NONE && event.getPacket() instanceof SPacketChat) {
            SPacketChat packet = event.getPacket();
            if (!packet.isSystem()) {
                return;
            }
            final String originalMessage = packet.chatComponent.getFormattedText();
            final String message = this.getTimeString(originalMessage) + originalMessage;
            packet.chatComponent = (ITextComponent)new TextComponentString(message);
        }
    }
    
    public String getTimeString(final String message) {
        final String date = new SimpleDateFormat("k:mm").format(new Date());
        if (this.rainbowTimeStamps.getValue()) {
            final String timeString = "<" + date + ">" + (this.space.getValue() ? " " : "");
            final StringBuilder builder = new StringBuilder(timeString);
            builder.insert(0, "§+");
            return builder.toString();
        }
        return ((this.bracket.getValue() == TextUtil.Color.NONE) ? "" : TextUtil.coloredString("<", this.bracket.getValue())) + TextUtil.coloredString(date, this.timeStamps.getValue()) + ((this.bracket.getValue() == TextUtil.Color.NONE) ? "" : TextUtil.coloredString(">", this.bracket.getValue())) + (this.space.getValue() ? " " : "") + "§r";
    }
    
    private boolean shouldSendMessage(final EntityPlayer player) {
        return player.dimension == 1 && this.timer.passedS(this.qDelay.getValue()) && player.getPosition().equals((Object)new Vec3i(0, 240, 0));
    }
    
    static {
        Chat.INSTANCE = new Chat();
    }
    
    public enum Suffix
    {
        NONE, 
        ONE, 
        TWO, 
        THREE, 
        CLAW;
    }
}
