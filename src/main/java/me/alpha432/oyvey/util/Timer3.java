// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.util;

public class Timer3
{
    private long time;
    private long current;
    
    public Timer3() {
        this.time = -1L;
        this.current = System.currentTimeMillis();
    }
    
    public boolean passedS(final double s) {
        return this.passedMs((long)s * 1000L);
    }
    
    public boolean passedDms(final double dms) {
        return this.passedMs((long)dms * 10L);
    }
    
    public boolean passedDs(final double ds) {
        return this.passedMs((long)ds * 100L);
    }
    
    public boolean passedMs(final long ms) {
        return this.passedNS(this.convertToNS(ms));
    }
    
    public void setMs(final long ms) {
        this.time = System.nanoTime() - this.convertToNS(ms);
    }
    
    public boolean passedNS(final long ns) {
        return System.nanoTime() - this.time >= ns;
    }
    
    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }
    
    public Timer3 reset() {
        this.time = System.nanoTime();
        return this;
    }
    
    public void reset2() {
        this.current = System.currentTimeMillis();
    }
    
    public long getMs(final long time) {
        return time / 1000000L;
    }
    
    public long convertToNS(final long time) {
        return time * 1000000L;
    }
    
    public boolean passed(final long delay) {
        return System.currentTimeMillis() - this.current >= delay;
    }
}
