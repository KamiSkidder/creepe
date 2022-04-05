// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.event.events2;

import com.jcraft.jogg.Packet;
import me.alpha432.oyvey.event.EventStage;

public class PacketEventG extends EventStage
{
    private final Packet packet;
    
    public PacketEventG(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public static class Receive extends PacketEvent
    {
        public Receive(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
    
    public static class Send extends PacketEvent
    {
        public Send(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
    
    public static class PostReceive extends PacketEvent
    {
        public PostReceive(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
    
    public static class PostSend extends PacketEvent
    {
        public PostSend(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
}
