// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.manager;

import java.awt.Font;
import me.alpha432.oyvey.features.gui.CustomFont;
import me.alpha432.oyvey.util.Globals;

public class MenuFont implements Globals
{
    private final CustomFont menuFont;
    private final CustomFont headerFont;
    
    public MenuFont() {
        this.menuFont = new CustomFont(new Font("Tahoma", 1, 21), true, false);
        this.headerFont = new CustomFont(new Font("Tahoma", 1, 41), true, false);
    }
    
    public void drawStringWithShadow(final String string, final float x, final float y, final int colour) {
        this.drawString(string, x, y, colour, true);
    }
    
    public float drawString(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.menuFont.drawStringWithShadow(string, x, y, colour);
        }
        return this.menuFont.drawString(string, x, y, colour);
    }
    
    public float drawStringBig(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.headerFont.drawStringWithShadow(string, x, y, colour);
        }
        return this.headerFont.drawString(string, x, y, colour);
    }
    
    public int getTextHeight() {
        return this.menuFont.getStringHeight();
    }
    
    public int getTextWidth(final String string) {
        return this.menuFont.getStringWidth(string);
    }
}
