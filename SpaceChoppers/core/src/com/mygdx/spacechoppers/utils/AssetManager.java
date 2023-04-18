package com.mygdx.spacechoppers.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class AssetManager {
    private static AssetManager instance;
    private Music backgroundMusic;

    private AssetManager() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background_music.mp3"));
        backgroundMusic.setLooping(true);
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public void playBackgroundMusic() {
        backgroundMusic.play();
        System.out.println("IS THE SHIT PLAYING????");
        System.out.println(backgroundMusic.isPlaying());
    }

    public void dispose() {
        backgroundMusic.dispose();
    }
}

