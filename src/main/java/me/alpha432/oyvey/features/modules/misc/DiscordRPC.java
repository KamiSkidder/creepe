// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.misc;

import me.alpha432.oyvey.RPC;
import me.alpha432.oyvey.features.modules.Module;

public class DiscordRPC extends Module
{
    public DiscordRPC() {
        super("Discord RPC", "RPC", Category.CLIENT, true, false, false);
    }
    
    @Override
    public void onEnable() {
        RPC.startRPC();
    }
    
    @Override
    public void onDisable() {
        RPC.stopRPC();
    }
}
