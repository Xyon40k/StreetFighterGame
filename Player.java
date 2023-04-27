package com.streetfightergame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe Player che estende ed utilizza la classe astratta SpriteObject
 */
public abstract class Player extends SpriteObject {

    /**
     * Queste sono tutte le animazioni del Player. In futuro ne verranno aggiunte altre.
     */
    private Animator animator;
    
    private ObjectStatus status;
    private Animator idle;
    private Animator running;
    private Animator attack;
    protected Animator specialAttack;
    private Animator dying;
    private Animator hurt;
    protected Animator taunt;
    protected HealthManager healt;
    private float oldx;
    /**
     * Costruttore della classe Player.
     * @param defaultX Posizione inziale X
     * @param defaultY Posizione inziale Y
     * @param pathToAsset Path dell'asset
     * @param batch Batch
     */
    public Player(int defaultX, int defaultY, String pathTdoAsset, SpriteBatch batch, String folder){
        super(defaultX, defaultY, pathTdoAsset, batch); //Chiamo la superclasse SpriteObject
        this.status = ObjectStatus.Idle; //Imposto l'ObjectStatus attuale a Idle.
        String className = ""; //Dichiaro la variabile className (mi servirà per capire che assets devo caricare in base a che personaaggio è).
        /**
         * Qua creo tutte le animazioni che servono al Player "generico", quindi:
         * -Idle / Run / Attack / Hurt / Dying
         */
        this.idle = new Animator(AnimatorLoader.loadListofImages(folder + "\\Idle"), 0.08f, true, ObjectStatus.Idle);
        this.running = new Animator(AnimatorLoader.loadListofImages(folder + "\\Walk"), 0.08f, true, ObjectStatus.Running);
        this.attack = new Animator(AnimatorLoader.loadListofImages(folder + "\\Attack"), 0.1f, false, ObjectStatus.Attack);
        this.dying = new Animator(AnimatorLoader.loadListofImages(folder + "\\Dying"), 0.1f, false, ObjectStatus.Dying);
        this.hurt = new Animator(AnimatorLoader.loadListofImages(folder + "\\Hurt"), 0.05f, false, ObjectStatus.Hurt);
        this.animator = this.idle; // Assegno la animazione attuale / iniziale a Idle.
        this.oldx = defaultX; //Aggiorno la coordinata oldx alla coordinata X attuale.
    }

    /**
     * Variabile booleana per capire se il player è morto o meno.
     */
    private boolean dead = false;

    /**
     * Creo il metodo update per aggiornare il Player.
     */
    @Override
    public void update(){
        if(dead || healt == null){
            return; //Se il personaggio è morto oppure la salute è nulla, ritorno.
        }
        this.healt.displayHeart(); //Aggiorno il valore e counter dei cuori sullo schermo.
        if(healt.getCurrentHeart() <= 0){ //Se i cuori sono uguali o minori a 0, il player è morto.
            updateAnimator(this.dying); //Quindi carico la animazione dying.
        }
        checkScreenPosition();
        if(this.animator == null){ //Se l'animazione è nulla vuol dire che il personaggio non si deve animare. Mostrerà solo la texture.
            batch.draw(texture, getX(), getY()); //Disegno il Player.
        }
        else if(oldx != getX()){
            if(status != ObjectStatus.Running){ //Sta correndo
                updateAnimator(running); //carico l'animazione di running
            }
        }
        else if(status == ObjectStatus.Running){ //Se sta correndo ma le coordinate getX() e oldx sono uguali, vuole dire che si è fermato, quindi cambio animazione
            updateAnimator(idle);
        }
        batch.draw(this.animator.getFrame(), getX(), getY()); //Disegno il player sullo schermo
        this.oldx = getX(); //Aggiorno la coordinata oldx

        // Serve per vedere se l'animazione è finita e per caricare quella precedente.
        if(this.animator != null && this.animator.isAnimationEnded() == true){
            if(this.animator.getStatus() == ObjectStatus.Dying){ //Se la animazione (della morte) è terminata e l'ObjectStatus attuale è Dying, vuol dire che il player è morto. Quindi:
                this.dead = true; //La variabile booleana dead diventa True.
                this.status = ObjectStatus.Dead; //L'ObjectStatus passa da Dying a Dead.
                return; //Ritorno.
            }
            //this.animator.resetTime(); //Non serve resettare il tempo, ora lo fa in automatico.
            this.animator.setEndedStatus(false); //Dichiaro che la animazione attuale non è terminata (chiedere a Stefano se non si capisce perchè)
            this.animator = this.idle; //La animazione è terminata, quindi posso tornare allo stato di Idle
            this.status = this.animator.getStatus(); //Aggiorno l'ObjectStatus
        }
    }

    /**
     * Modifico la posizione del Player attuale
     * @param x Nuova Coordinata X - Float
     * @param y Nuova Coordinata Y - Float
     */
    @Override
    public void setPosition(float x, float y){
        if(this.status == ObjectStatus.Idle || this.status == ObjectStatus.Running){
            this.player.setPosition(x, y);
        }
    }

    /**
     * Aggiorna la animazione attuale. Esempio: quando player viene colpito, la animazione viene aggiornata da XXX -> Hurt
     * @param newAnimator Nuova animazione da caricare.
     */
    public void updateAnimator(Animator newAnimator){
        this.status = newAnimator.getStatus(); //aggiorno lo status del player - Esempio: se carico una animazione Idle lo stato diventa Idle, animazione Attack lo status diventa Attack, etc. etc
        this.animator = newAnimator; //Aggiorno l'animator attuale.
    }

    /**
     * Metodo astratto damage.
     */
    abstract void damage();

    /**
     * Ritorna l'HealthManager della classe
     * @return HealtManager
     */
    public HealthManager getHealtManager(){return this.healt;}

    /**
     * Ritorna l'ObjectStatus attuale del player
     * @return ObjectStatus
     */
    public ObjectStatus getStatus(){return this.status;}

    /**
     * Ritorna la animazione dell'attacco
     * @return Animator
     */
    public Animator getAttackAnimation(){return this.attack;}

    /**
     * Ritorna la animazione dell'attacco speciale [al momento (25/04/2023 16:02) disponibile solo per il Golem, non per il Wraith].
     * @return Animator
     */
    public Animator getSpecialAttackAnimation(){
        if(this.specialAttack != null && this.getClass() == Golem.class){ //Controllo se è effettivamente un golem
            return this.specialAttack;
        }
        return null;
    }

    /**
     * Ritorno la animazione della ferita.
     * @return Animator
     */
    public Animator getHurtAnimation(){return this.hurt;}

    /**
     * Ritorna la animazione dell'attacco speciale [al momento (25/04/2023 16:03) disponibile solo per il Wraith, dato che il pacchetto Golem non lo includeva].
     * @return Animator
     */
    public Animator getTauntAnimation(){
        if(this.taunt != null && this.getClass() == Wraith.class){ // Controllo se ha un Animator taunt e se è un Wraith
            return this.taunt;
        }
        return null;
    }

    /**
     * Setto un nuovo ObjectStatus del Player. Al momento non utilizzato.
     * @param newStatus Nuovo Status del player
     */
    public void setStatus(ObjectStatus newStatus){this.status = newStatus;}

    static final boolean finisciSchemo  = true; //Chiedi a Stefano
    /**
     * Questo metodo serve per controllare se il Player è uscito dallo schermo.
     * Esempio:
     * Se il Player va troppo a destra, apparirà a sinistra, e viceversa. Si può controllare tramite la variabile booleana finisciSchermo.
     */
    private void checkScreenPosition(){
        if(this.finisciSchemo == true){ //Chiedi a Stefano
            if(getX() > +1640){
                setPosition(-640, getY());
            }
            else if(getX() < -640){
                setPosition(+1640, getY());
            }
        }
    }
}