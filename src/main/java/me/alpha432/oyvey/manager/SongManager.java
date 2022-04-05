//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.manager;

import java.util.Collections;
import java.util.Arrays;
import me.alpha432.oyvey.util.song.HotelRoom;
import me.alpha432.oyvey.util.song.FireBall;
import me.alpha432.oyvey.util.song.DontStop;
import net.minecraft.client.audio.ISound;
import java.util.List;
import me.alpha432.oyvey.util.Globals;

public class SongManager implements Globals
{
    private final List<ISound> songs;
    private final ISound menuSong;
    private ISound currentSong;
    
    public SongManager() {
        this.songs = Arrays.asList(DontStop.sound, FireBall.sound, HotelRoom.sound);
        this.menuSong = this.getRandomSong();
        this.currentSong = this.getRandomSong();
    }
    
    public ISound getMenuSong() {
        return this.menuSong;
    }
    
    public void skip() {
        final boolean flag = this.isCurrentSongPlaying();
        if (flag) {
            this.stop();
        }
        this.currentSong = this.songs.get((this.songs.indexOf(this.currentSong) + 1) % this.songs.size());
        if (flag) {
            this.play();
        }
    }
    
    public void play() {
        if (!this.isCurrentSongPlaying()) {
            SongManager.mc.soundHandler.playSound(this.currentSong);
        }
    }
    
    public void stop() {
        if (this.isCurrentSongPlaying()) {
            SongManager.mc.soundHandler.stopSound(this.currentSong);
        }
    }
    
    private boolean isCurrentSongPlaying() {
        return SongManager.mc.soundHandler.isSoundPlaying(this.currentSong);
    }
    
    public void shuffle() {
        this.stop();
        Collections.shuffle(this.songs);
    }
    
    private ISound getRandomSong() {
        return this.songs.get(SongManager.random.nextInt(this.songs.size()));
    }
}
