// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class RPC
{
    private static final DiscordRichPresence discordRichPresence;
    private static final DiscordRPC discordRPC;
    
    public static void startRPC() {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        final String discordID = "926165595144196117";
        RPC.discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.details = "\u1d04\u0280\u1d07\u1d18\u1d07 \u1d04\u029f\u026a\u1d07\u0274\u1d1b Buy here https://discord.gg/YZ9ghVWw";
        RPC.discordRichPresence.largeImageKey = "main";
        RPC.discordRichPresence.largeImageText = "";
        RPC.discordRichPresence.largeImageText = "https://discord.gg/YZ9ghVWw";
        RPC.discordRichPresence.smallImageText = "discord server https://discord.gg/YZ9ghVWw";
        RPC.discordRichPresence.state = null;
        RPC.discordRPC.Discord_UpdatePresence(RPC.discordRichPresence);
    }
    
    public static void stopRPC() {
        RPC.discordRPC.Discord_Shutdown();
        RPC.discordRPC.Discord_ClearPresence();
    }
    
    static {
        discordRichPresence = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
    }
}
