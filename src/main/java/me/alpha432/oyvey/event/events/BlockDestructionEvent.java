// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event.events;

import net.minecraft.util.math.BlockPos;
import me.alpha432.oyvey.event.EventStage;

public class BlockDestructionEvent extends EventStage
{
    BlockPos nigger;
    
    public BlockDestructionEvent(final BlockPos nigger) {
        this.nigger = nigger;
    }
    
    public BlockPos getBlockPos() {
        return this.nigger;
    }
}
