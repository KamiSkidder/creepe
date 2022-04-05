// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event.events2;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import com.jcraft.jogg.Packet;
import me.alpha432.oyvey.event.EventStage;

public class PacketEventM extends EventStage
{
    private final Packet packet;
    
    public PacketEventM(final Packet packet) {
        this.packet = packet;
    }
    
    public <T extends Packet> T getPacket() {
        return (T)this.packet;
    }
    
    @Cancelable
    public static class Receive extends PacketEvent
    {
        public Receive(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
    
    @Cancelable
    public static class Send extends PacketEvent
    {
        public Send(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
}
