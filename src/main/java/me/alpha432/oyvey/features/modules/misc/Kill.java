//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.Module;

public class Kill extends Module
{
    public Kill() {
        super("/Kill", "Ez Spam if you have bariton", Category.MISC, true, false, false);
    }
    
    @Override
    public void onEnable() {
        Kill.mc.player.sendChatMessage("/kill");
        Kill.mc.player.sendChatMessage("kill");
        Command.sendMessage("Kill");
        this.disable();
    }
}
