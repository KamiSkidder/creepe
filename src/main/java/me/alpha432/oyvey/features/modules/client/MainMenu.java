// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class MainMenu extends Module
{
    public static MainMenu INSTANCE;
    public Setting<Boolean> mainScreen;
    
    public MainMenu() {
        super("Menu", "Controls custom screens used by the client", Category.CLIENT, true, false, false);
        this.mainScreen = (Setting<Boolean>)this.register(new Setting("MainScreen", true));
        MainMenu.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
    }
}
