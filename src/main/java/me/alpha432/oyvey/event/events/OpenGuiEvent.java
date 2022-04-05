// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event.events;

import net.minecraft.client.gui.GuiScreen;

public class OpenGuiEvent extends CancelableEvent
{
    public GuiScreen Field1152;
    
    public void Method1160(final GuiScreen guiScreen) {
        this.Field1152 = guiScreen;
    }
    
    public OpenGuiEvent(final GuiScreen guiScreen) {
        this.Field1152 = guiScreen;
    }
    
    public GuiScreen Method1161() {
        return this.Field1152;
    }
}
