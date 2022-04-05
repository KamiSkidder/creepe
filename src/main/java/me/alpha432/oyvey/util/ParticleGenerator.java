//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.util;

import org.lwjgl.opengl.GL11;
import net.minecraft.util.math.MathHelper;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

public class ParticleGenerator
{
    private final int count;
    private final int width;
    private final int height;
    private final ArrayList<Particle> particles;
    private final Random random;
    int state;
    int a;
    int r;
    int g;
    int b;
    
    public ParticleGenerator(final int count, final int width, final int height) {
        this.particles = new ArrayList<Particle>();
        this.random = new Random();
        this.state = 0;
        this.a = 255;
        this.r = 255;
        this.g = 0;
        this.b = 0;
        this.count = count;
        this.width = width;
        this.height = height;
        for (int i = 0; i < count; ++i) {
            this.particles.add(new Particle(this.random.nextInt(width), this.random.nextInt(height)));
        }
    }
    
    public void drawParticles(final int mouseX, final int mouseY) {
        for (final Particle p : this.particles) {
            if (p.reset) {
                p.resetPosSize();
                p.reset = false;
            }
            p.draw(mouseX, mouseY);
        }
    }
    
    public class Particle
    {
        private final Random random;
        private int x;
        private int y;
        private int k;
        private float size;
        private boolean reset;
        
        public Particle(final int x, final int y) {
            this.random = new Random();
            this.x = x;
            this.y = y;
            this.size = this.genRandom(1.0f, 4.0f);
        }
        
        public void draw(final int mouseX, final int mouseY) {
            if (this.size <= 0.0f) {
                this.reset = true;
            }
            this.size -= 0.05f;
            ++this.k;
            final int xx = (int)(MathHelper.cos(0.1f * (this.x + this.k)) * 10.0f);
            final int yy = (int)(MathHelper.cos(0.1f * (this.y + this.k)) * 10.0f);
            Utils.drawBorderedCircle(this.x + xx, this.y + yy, this.size, 0, 553648127);
            final float distance = (float)Utils.distance((float)(this.x + xx), (float)(this.y + yy), (float)mouseX, (float)mouseY);
            if (distance < 70.0f) {
                final float alpha1 = Math.min(1.0f, Math.min(1.0f, 1.0f - distance / 70.0f));
                GL11.glEnable(2848);
                GL11.glDisable(2929);
                GL11.glColor4f(255.0f, 255.0f, 255.0f, 255.0f);
                GL11.glDisable(3553);
                GL11.glDepthMask(false);
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(3042);
                GL11.glLineWidth(0.5f);
                GL11.glBegin(1);
                GL11.glVertex2f((float)(this.x + xx), (float)(this.y + yy));
                GL11.glVertex2f((float)mouseX, (float)mouseY);
                GL11.glEnd();
            }
        }
        
        public void resetPosSize() {
            this.x = this.random.nextInt(ParticleGenerator.this.width);
            this.y = this.random.nextInt(ParticleGenerator.this.height);
            this.size = this.genRandom(1.0f, 4.0f);
        }
        
        public float genRandom(final float min, final float max) {
            return (float)(min + Math.random() * (max - min + 1.0f));
        }
    }
}
