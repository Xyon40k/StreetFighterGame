package com.streetfightergame.game;

public class Settings {
    /**
     * Chiamata Settings sennò rossani piange
     */
    public static final String golemPath = "Golem1";
    public static final String wraithPath = "Wraith1";
    public static final String walkPath = "Walk";
    public static final String attackPath = "Attack";
    public static final String dyingPath = "Dying";
    public static final String hurtPath = "Hurt";
    public static final String idlePath = "Idle";
    public static final String tauntPath = "Taunt";
    public static final String superAttackPath = "Kick";
    public static final boolean finisciSchermo  = true; //True: alla fine dello schermo il Player spunta dall'altra parte
    public static final int runValue = 9; //Quanti pixel corre
    public static final int accelerazioneCorsa = 5; //Quante caselle fa in più se sta correndo
    public static final int iFramePerAttack = 30;

    public static final int golemAttackRange = 50;
    public static final int golemSpecialAttackRange = 100;
    public static final int wraithAttackRange = 50;

    public static final int gravity = -1;
    public static final int floorY = 10;
}
