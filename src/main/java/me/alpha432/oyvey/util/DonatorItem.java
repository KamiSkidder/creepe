// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.util;

import java.awt.Color;
import me.alpha432.oyvey.features.gui.custom.Globals;

public class DonatorItem implements Globals
{
    private final String name;
    private final int size;
    private double x;
    private double y;
    private final double width;
    private final double height;
    private final int canvasWidth;
    private final int canvasHeight;
    private final Color colour;
    private double xSpeed;
    private double ySpeed;
    
    public DonatorItem(final String name, final int size, final int width, final int height, final int canvasWidth, final int canvasHeight) {
        this.name = name;
        this.size = size;
        this.x = MathsUtil.random(20, canvasWidth - 20);
        this.y = MathsUtil.random(20, canvasHeight - 20);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.width = width;
        this.height = height;
        this.colour = new Color((int)(Math.random() * 1.6777216E7));
        this.xSpeed = this.offsetStart(0.1);
        this.ySpeed = this.offsetStart(0.1);
    }
    
    public void updatePos() {
        if (this.x + this.width >= this.canvasWidth || this.x <= 0.0) {
            this.xSpeed *= -1.0;
        }
        if (this.y + this.height >= this.canvasHeight || this.y <= 0.0) {
            this.ySpeed *= -1.0;
        }
        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getRgb() {
        return this.colour.getRGB();
    }
    
    private double offsetStart(final double i) {
        if (DonatorItem.random.nextInt(2) == 0) {
            return -i;
        }
        return i;
    }
}
