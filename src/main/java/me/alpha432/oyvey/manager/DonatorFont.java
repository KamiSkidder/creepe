// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.manager;

import java.awt.Font;
import me.alpha432.oyvey.features.gui.font.CustomFont;
import me.alpha432.oyvey.features.gui.custom.Globals;

public class DonatorFont implements Globals
{
    private final String fontName = "Tahoma";
    private final int smallSize = 15;
    private final int mediumSize = 19;
    private final int largeSize = 24;
    private final CustomFont smallFont;
    private final CustomFont mediumFont;
    private final CustomFont largeFont;
    
    public DonatorFont() {
        this.smallFont = new CustomFont(new Font("Tahoma", 0, 15), true, false);
        this.mediumFont = new CustomFont(new Font("Tahoma", 0, 19), true, false);
        this.largeFont = new CustomFont(new Font("Tahoma", 0, 24), true, false);
    }
    
    public void drawSmallStringRainbow(final String string, final float x, final float y, final int colour) {
        this.smallFont.drawStringWithShadow(string, x, y, colour);
    }
    
    public void drawMediumStringRainbow(final String string, final float x, final float y, final int colour) {
        this.mediumFont.drawStringWithShadow(string, x, y, colour);
    }
    
    public void drawLargeStringRainbow(final String string, final float x, final float y, final int colour) {
        this.largeFont.drawStringWithShadow(string, x, y, colour);
    }
}
