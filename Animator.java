package com.streetfightergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    private Animation<TextureRegion> animationType; //Regione di animazione della Animazione attuale.
    private float stateTime; //Tempo passato dall'inizio della animazione.
    private float timing; //Tempo di aggiornamento della animazione.
    private boolean isLoop; //Booleano per vedere se la animazione deve andare in loop (tipo Idle o Running) oppure si deve fermare alla fine (tipo Attack, Hurt, Dying).
    private int animationLength; //Intero per capire se si è all'ultimo frame della animazione.

    private ObjectStatus name;
    /**
     * Classe Animator. Serve per le animazioni di ogni personaggio / oggetto
     * @param textureRegions textureRegions - È un array di TextureRegions. Contiene tutte le animazioni dell'oggetto.
     * @param timing Tempo di aggiornamento della animazione. Esempio: 0.5 = Ogni mezzo secondo la animazione cambia
     * @param isLoop È una variabile booleana per vedere se la animazione si deve ripetere, oppure si ferma alla fine del ciclo di immagini
     */
    public Animator(TextureRegion[] textureRegions, float timing, boolean isLoop, ObjectStatus name){
        this.name = name;
        this.timing = timing;
        this.isLoop = isLoop;
        animationType = new Animation<TextureRegion>(timing, textureRegions);
        this.animationLength = textureRegions.length;
    }

    /**
     * Ritorna lo stato della Animazione attuale.
     * Esempio: Idle, Attack, Hurt, Dying, etc. etc.
     * @return ObjectStatus
     */
    public ObjectStatus getStatus(){return this.name;}

    /**
     * Booleano per capire se la Animazione attuale è terminata o meno.
     */
    private boolean animationEnded = false;

    /**
     * Ritorna il frame della attuale animazione.
     * @return
     */
    public TextureRegion getFrame(){
        stateTime += Gdx.graphics.getDeltaTime(); // Accumula il tempo passato della animazione.
        // Serve per vedere se l'animazione è finita e per caricare quella precedente.
        if(animationType.getKeyFrameIndex(stateTime) == animationLength-1 && isLoop == false){ //Qua controlla se la animazione è terminata.
            animationEnded = true; //Se è terminata aggiorna animationEnded a True.
            this.stateTime = 0.0f; //Azzera lo stateTime della animazione per permettere in futuro di utilizzarla ancora, molto importante (chiedere a Stefano per capire meglio)
        }
        TextureRegion currentFrame = animationType.getKeyFrame(stateTime, isLoop); //ritorna il frame attuale della Animazione attuale.
        return currentFrame;
    }

    /**
     * Metodo per capire se la Animazione è terminata.
     * @return Booleano animationEnded.
     */
    public boolean isAnimationEnded(){return animationEnded;}

    /**
     * Setta il valore di animationEnded. Si utilizza quando la animazione termina.
     * @param newState stato della animazione.
     */
    public void setEndedStatus(boolean newState){this.animationEnded = newState;}

    /**
     * Ritorna un valore booleano che indica se la animazione è un loop o meno.
     * Esempio: Idle o Running saranno infinite, mentre Attack o Hurt no.
     * Ancora non utilizzato (era per provare delle cose)
     * @return booleano isLoop.
     */
    public boolean isLoop(){return isLoop;}
}
