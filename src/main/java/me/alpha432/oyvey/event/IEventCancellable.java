// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event;

public interface IEventCancellable extends IEvent
{
    void setCancelled();
    
    void setCancelled(final boolean p0);
    
    boolean isCancelled();
}
