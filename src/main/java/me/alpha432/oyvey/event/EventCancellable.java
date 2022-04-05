// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event;

public abstract class EventCancellable extends Event<Event> implements IEventCancellable
{
    private boolean isCancelled;
    
    public EventCancellable() {
        this.isCancelled = false;
    }
    
    @Override
    public void setCancelled() {
        this.isCancelled = true;
    }
    
    @Override
    public void setCancelled(final boolean cancelled) {
        this.isCancelled = cancelled;
    }
    
    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }
    
    public abstract void setCanceled(final boolean p0);
}
