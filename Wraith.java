package com.streetfightergame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wraith extends Player{
    /**
     * Classe Wraith. Eredita da Player tutti i metodi.
     * @param defaultX Coordinata inziale X
     * @param defaultY Coordinata iniziale Y
     * @param pathToAsset path per la sua Texture
     * @param batch Batch
     */
    public Wraith(int defaultX, int defaultY, String pathToAsset, SpriteBatch batch){
        super(defaultX, defaultY, pathToAsset, batch, "Wraith1"); //Chiamo la super classe
        this.healt = new HealthManager("health.png", 10, batch, defaultX, 800); //Creo un determinato tipo di HealtManager
        //Creo l'Animator Taunt (Questo tipo di Animator è disponibile solo per il Wraith (25/04/2023 16:24) dato che è un assets disponibile solo nel pacchetto dei Wraith
        this.taunt =new Animator(AnimatorLoader.loadListofImages("Wraith1\\Taunt"), 0.06f, false, ObjectStatus.Taunt);
    }

    /**
     * Metodo damage:
     * Rimuove un cuore al Player.
     */
    @Override
    public void damage(){
        getHealtManager().removeHeart();
    }

    /**
     * Attacca un Player p utilizzando il suo attacco normale (Il Wraith non ha altri attacchi per il momento - 25/04/2023 16:41).
     * @param p Player da attaccare
     */
    public void attack(Player p){
        int range = 50;
        updateAnimator(getAttackAnimation()); //aggiorno l'animatore e carico la animazione dell'Attacco
        if((getX() + getWidth()/2 + range >= p.getX() - p.getWidth()/2) && getX() < p.getX()){ // Se il nemico è in un certo range allora:
            p.updateAnimator(p.getHurtAnimation()); // Carico la animazione Hurt per il Player p
            p.damage(); // Danneggio il Player p
        }
    }
}
