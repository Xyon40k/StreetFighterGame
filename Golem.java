package com.streetfightergame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Golem extends Player{
    /**
     * Classe Golem. Eredita da Player tutti i metodi.
     * @param defaultX Coordinata inziale X
     * @param defaultY Coordinata iniziale Y
     * @param pathToAsset path per la sua Texture
     * @param batch Batch
     */
    public Golem(int defaultX, int defaultY, String pathToAsset, SpriteBatch batch){
        super(defaultX, defaultY, pathToAsset, batch, "Golem1"); // Chiamo la superclasse
        this.healt = new HealthManager("health.png", 4, batch, defaultX, 800); //Creo un determinato tipo di HealtManager (meno cuori rispetto al Wraith e coordinate diverse ovviamente)
        //Creo l'Animator Kick (Questo tipo di Animator è disponibile solo per il Golem (25/04/2023 16:14) dato che è un assets disponibile solo nel pacchetto dei Golem
        this.specialAttack = new Animator(AnimatorLoader.loadListofImages("Golem1\\Kick"), 0.06f, false, ObjectStatus.Attack); //Creo l'Animator e dichiaro il suo ObjectStatus in Attack

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
     * Attacca un Player p utilizzando il suo attacco normale.
     * @param p Player da attaccare
     */
    public void attack(Player p){
        int range = 50;
        updateAnimator(getAttackAnimation()); //aggiorno l'animatore
        if((getX() + getWidth()/2 + range >= p.getX() - p.getWidth()/2) && getX() < p.getX()){ // Se il nemico è in un certo range allora:
            p.updateAnimator(p.getHurtAnimation());// Carico la animazione Hurt per il Player p
            p.damage(); // Danneggio il Player P
        }
        else{
            p.updateAnimator(p.getTauntAnimation()); // Se invece manca il bersaglio, il Player P fa una animazione di "scherno" = Taunt
        }
    }

    /**
     * Attacca un Player p utilizzando il suo attacco speciale.
     * @param p Player da attaccare
     */
    public void specialAttack(Player p){
        int range = 100;
        updateAnimator(getSpecialAttackAnimation()); //aggiorno l'animatore
        if((getX() + getWidth()/2 + range >= p.getX() - p.getWidth()/2) && getX() < p.getX()){ // Se il nemico è in un certo range allora:
            p.updateAnimator(p.getHurtAnimation()); // Carico la animazione Hurt per il Player p
            p.damage(); // Danneggia due volte il Player p dato che è un attacco speciale
            p.damage();
        }
        else{
            p.updateAnimator(p.getTauntAnimation()); // Se invece manca il bersaglio, il Player P fa una animazione di "scherno" = Taunt
        }
    }
}
