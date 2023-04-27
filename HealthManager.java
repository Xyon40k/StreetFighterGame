package com.streetfightergame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HealthManager {

    private Texture[] texture; //Array di Texture (I cuori)
    private int currentHeart; //Intero per controllare il numero di cuori attuali.
    private int totalHeart; //Ho creato la variabile totalHeart per prevenire che ci si curasse all'infinito.
    private Batch batch; // Batch
    private float defaultX; //Coordinata inziale X dove si mostrano i Cuori
    private float defaultY; //Coordinata inziale Y dove si mostrano i Cuori

    /**
     * Utilizzo l'HealtManager per gestire la vita di ogni giocatore. La mostro sullo schermo, tolgo cuori, etc. etc.
     * @param pathToAsset Path per l'asset del Cuore
     * @param totalHeart Cuori totali (Esempio: un Golem può averen 10, e uno Wraith 5)
     * @param batch Batch
     * @param defaultX Posizione X dove mostro il 1 cuore
     * @param defaultY Posizione Y dove mostro il primo cuore
     */
    public HealthManager(String pathToAsset, int totalHeart, Batch batch, float defaultX, float defaultY){
        texture = new Texture[totalHeart];
        this.defaultY = defaultY;
        //Creo un array di Texture che sarà la "striscia" di cuori
        for (int i = 0; i < texture.length; i++) {
            this.texture[i] = new Texture(pathToAsset); //Carico una nuova Texture nell'Array di Texture
        }
        // Assegno tutte le variabili:
        this.defaultX = defaultX;
        this.currentHeart = totalHeart;
        this.totalHeart = totalHeart;
        this.batch = batch;
    }

    /**
     * Mostro i cuori sullo schermo.
     */
    public void displayHeart(){
        float currentX = this.defaultX;
        for (int i = 0; i < currentHeart; i++) { // For che va da 0 ai cuori totali, non da 0 alla lunghezza dell'array di Texture
            this.batch.draw(texture[i], currentX, defaultY); // Disegno il cuore sullo schermo.
            currentX += 120; //Aumento la coordinata X
        }
    }

    /**
     * Il Player è stato colpito quindi rimuovo un Cuore dai Cuori totali
     */
    public void removeHeart(){
        currentHeart--;
    }

    /**
     * Aggiungo un cuore. Per il momento (25/04/2023 11:42) ancora nessun personaggio è in grado di curarsi.
     */
    public  void addHeart(){
        if(this.currentHeart < this.totalHeart){
            currentHeart++;
        }
    }

    /**
     * Ritorna il totale dei cuori che si ha al momento
     * @return totale cuori
     */
    public int getCurrentHeart(){
        return currentHeart;
    }
}
