// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.modules.render;

public class ESSP extends RuntimeException
{
    public ESSP(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }
    
    @Override
    public String toString() {
        return "Fuck off nigga!";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
