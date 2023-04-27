package com.streetfightergame.game;

/**
 * Questi enum servono per definire lo stato attuale del Player, e della sua animazione Animator.
 */
public enum ObjectStatus {
    Idle,
    Running,
    Attack,
    Hurt,
    Taunt, // "scherno"
    Dying,
    Dead
}
