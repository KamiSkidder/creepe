//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.util.song;

import net.minecraft.util.SoundCategory;
import net.minecraft.client.audio.Sound;
import javax.annotation.Nullable;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.audio.ISound;
import me.alpha432.oyvey.features.gui.custom.Globals;

public class DontStop implements Globals
{
    public static final ISound sound;
    private static final String song = "dontstop";
    private static final ResourceLocation loc;
    
    static {
        loc = new ResourceLocation("sounds/dontstop.ogg");
        sound = (ISound)new ISound() {
            private final int pitch = 1;
            private final int volume = 1;
            
            public ResourceLocation getSoundLocation() {
                return DontStop.loc;
            }
            
            @Nullable
            public SoundEventAccessor createAccessor(final SoundHandler soundHandler) {
                return new SoundEventAccessor(DontStop.loc, "Pitbull");
            }
            
            public Sound getSound() {
                return new Sound("dontstop", 1.0f, 1.0f, 1, Sound.Type.SOUND_EVENT, false);
            }
            
            public SoundCategory getCategory() {
                return SoundCategory.VOICE;
            }
            
            public boolean canRepeat() {
                return true;
            }
            
            public int getRepeatDelay() {
                return 2;
            }
            
            public float getVolume() {
                return 1.0f;
            }
            
            public float getPitch() {
                return 1.0f;
            }
            
            public float getXPosF() {
                return 1.0f;
            }
            
            public float getYPosF() {
                return 0.0f;
            }
            
            public float getZPosF() {
                return 0.0f;
            }
            
            public ISound.AttenuationType getAttenuationType() {
                return ISound.AttenuationType.LINEAR;
            }
        };
    }
}
