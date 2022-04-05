// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event.events;

public class CancelableEvent
{
    public boolean cancelled;
    
    public CancelableEvent() {
        this.cancelled = false;
    }
    
    public boolean isCanceled() {
        return this.cancelled;
    }
    
    public void Cancel() {
        this.cancelled = true;
    }
    
    public void setCanceled(final boolean bl) {
        this.cancelled = bl;
    }
}
