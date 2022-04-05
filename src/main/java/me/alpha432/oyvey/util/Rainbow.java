// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.util;

import me.alpha432.oyvey.util.util2.Colour;
import java.awt.Color;

public class Rainbow
{
    public static Color getColour() {
        return Colour.fromHSB(System.currentTimeMillis() % 11520L / 11520.0f, 1.0f, 1.0f);
    }
}
