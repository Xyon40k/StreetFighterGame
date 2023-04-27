package com.streetfightergame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class SpriteObject {
    /**
     * Sprite
     */
    final Sprite player;
    /**
     * Texture dello Sprite
     */
    final Texture texture;

    final Batch batch;

    /**
     * Creo un nuovo Sprite Object. Serve per creare un nuovo oggetto, tipo un personaggio oppure un semplice oggetto.
     * @param defaultX Posizione X iniziale
     * @param defaultY Posizione Y iniziale
     * @param pathToAsset Path per l'Asset
     */
    public SpriteObject(int defaultX, int defaultY, String pathToAsset, Batch batch){
        this.batch = batch;
        this.texture = new Texture(pathToAsset);
        this.player = new Sprite(texture);
        this.player.setPosition(defaultX, defaultY);
    }

    /**
     * Ritorna la Coordinata X dello Sprite Object
     * @return Coordinata X - Float
     */
    public float getX(){return player.getX();}
    /**
     * Ritorna la Coordinata Y dello Sprite Object
     * @return Coordinata Y - Float
     */
    public float getY(){return player.getY();}

    /**
     * Ritorna la altezza dello Sprite Object - Non è la altezza effettiva dell'oggetto, ma dell'immagine stessa.
     * @return Altezza - Float
     */
    public float getHeight(){return player.getHeight();}
    /**
     * Ritorna la larghezza dello Sprite Object - Non è la larghezza effettiva dell'oggetto, ma dell'immagine stessa.
     * @return Larghezza - Float
     */
    public float getWidth(){return player.getWidth();}

    /**
     * Muove lo Sprite in una certa posizione, determinata dalle due coordinate X e Y (entrambe Float)
     * @param x
     * @param y
     */
    abstract void setPosition(float x, float y);

    /**
     * Questo è un metodo astratto. Ogni classe lo deve implementare in base a come si deve muovere.
     * Il metodo serve ad aggiornare lo stato, posizione, animazione, etc. etc. dello Sprite.
     * È diverso per ogni classe dato che ogni Sprite ha dei movimenti diversi e animazioni diverse.
     */
    //WIP
    abstract void update();
}
