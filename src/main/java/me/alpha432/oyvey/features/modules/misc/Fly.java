// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.misc;

import java.util.ArrayList;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.util.List;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "crepehwid", name = "CrepeHWID", version = "1.0")
public class Fly
{
    public static final String MODID = "crepehwid";
    public static final String NAME = "CrepeHWID";
    public static final String VERSION = "1.0";
    public static List<String> hwidList;
    public static final String KEY = "verify";
    public static final String HWID_URL = "https://pastebin.com/raw/rtD4fBmG";
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        this.Verify();
    }
    
    public void Verify() {
    }
    
    static {
        Fly.hwidList = new ArrayList<String>();
    }
}
