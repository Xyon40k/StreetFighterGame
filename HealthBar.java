package com.streetfightergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthBar {
    //Uk9TU0FOSSBET1dOISEh
    //Uk9TU0FOSSBET1dOISEh
    //Uk9TU0FOSSBET1dOISEh
    //Uk9TU0FOSSBET1dOISEh
    //Uk9TU0FOSSBET1dOISEh
    private Texture fullBar;
    private TextureRegion currBar;
    private float maxHealth;
    private float health; //Ho creato la variabile totalHeart per prevenire che ci si curasse all'infinito.
    private Batch batch; // Batch
    private float X; //Coordinata inziale X dove si mostrano i Cuori
    private float Y; //Coordinata inziale Y dove si mostrano i Cuori
    /**
     * Utilizzo l'HealtManager per gestire la vita di ogni giocatore. La mostro sullo schermo, tolgo cuori, etc. etc.
     * @param pathToAsset Path per l'asset del Cuore
     * @param totalHeart Cuori totali (Esempio: un Golem può averen 10, e uno Wraith 5)
     * @param batch Batch
     * @param defaultX Posizione X dove mostro il 1 cuore
     * @param defaultY Posizione Y dove mostro il primo cuore
     */
    public HealthBar(float maxHealth, Batch batch, float X, float Y){
        fullBar = new Texture(Gdx.files.internal("healthbar2.png"));
        currBar = new TextureRegion(fullBar, (int)(fullBar.getWidth()*(maxHealth/health)), fullBar.getHeight());
        this.Y = Y;
        this.X = X;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.batch = batch;
    }

    /**
     * Mostro i cuori sullo schermo.
     */
    public void display(){
        batch.draw(currBar, X, Y);
    }

    /**
     * Il Player è stato colpito quindi rimuovo un Cuore dai Cuori totali
     */
    public void changeHealth(float change){
        health += change;
        if(health > maxHealth) {
            health = maxHealth;
        }
        currBar.setRegionWidth((int)(fullBar.getWidth()*(maxHealth/health)));
    }

    /**
     * Ritorna il totale dei cuori che si ha al momento
     * @return totale cuori
     */
    public float getHealth(){
        return health;
    }
}
