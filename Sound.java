package com.streetfightergame.game;


import com.badlogic.gdx.*;

public class Sound {

    private String assetPath;
    private float volume;
    private boolean isLoop;
    private long soundId;
    private SoundStatus status;
    private com.badlogic.gdx.audio.Sound sound;

    public Sound(String assetPath, float volume, boolean isLoop){
        this.assetPath = assetPath;
        this.volume = volume;
        this.isLoop = isLoop;
        this.sound = Gdx.audio.newSound(Gdx.files.internal(this.assetPath));
        this.status = SoundStatus.Stopped;
    }

    public void play(){
        if(this.status == SoundStatus.Stopped){
            this.soundId = this.sound.play();
            this.sound.setLooping(this.soundId, this.isLoop);
            this.status = SoundStatus.Playing;
        }
    }

    public void stop(){
        if(this.status == SoundStatus.Playing){
            this.sound.stop();
            this.status = SoundStatus.Stopped;
        }
    }

    public void toggle(){
        if(this.status == SoundStatus.Playing){
            this.sound.pause();
            this.status = SoundStatus.Paused;
        }
        else if(this.status == SoundStatus.Paused){
            this.sound.resume();
            this.status = SoundStatus.Playing;
        }
    }

    public void setLoop(boolean newValue){
        this.isLoop = newValue;
        this.sound.setLooping(this.soundId, this.isLoop);
    }

    public long getSoundId(){
        return this.soundId;
    }

    public float getVolume(){
        return this.volume;
    }
    
    public void setVolume(float newValue){
        this.volume = newValue;
    }
}