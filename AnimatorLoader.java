package com.streetfightergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.File;
public class AnimatorLoader {

    /**
     * Questo metodo serve per caricare una serie di animazioni contenute in una sola immagine. Vedi esempio: asset/idle.png oppure asset/walk.png
     * @param animationPath path per l'immagine
     * @param cols colonne della immagine
     * @param rows righe della immagine. Nel caso di asset/idle.png oppure asset/walk.png la riga è 1.
     * @return array di TextureRegion
     */
    public static TextureRegion[] loadSingleImage(String animationPath, int cols, int rows){
        Texture animation;
        animation = new Texture(animationPath);
        // Divido la immagine in varie regioni, costruendo una matrice di TextureRegion in base alle righe e colonne della immagine.
        TextureRegion[][] tmp = TextureRegion.split(animation,
                animation.getWidth() / cols,
                animation.getHeight() / rows);

        TextureRegion[] walkFrames = new TextureRegion[cols * rows]; //Creo un Array di TextureRegion.
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                walkFrames[index++] = tmp[i][j]; //Riempio l'array grazie alla Matrice.
            }
        }
        return walkFrames; //Ritorno l'Array di TextureRegion
    }

    /**
     * Variabile per trovare la cartella globale degli asset (bruh può variare da IDE a IDE. Fixate gli errori printando la cartella, linea 47):
     */
    private static String assetPath = Gdx.files.getLocalStoragePath() + "\\assets\\"; //Path se usi Visual Studio Code
    //private static String assetPath = Gdx.files.getLocalStoragePath(); //Path se usi Intellij IDEA Ultimate
    /**
     * Questo metodo serve per creare una animazione partendo da una lista di immagini. Vedi esempio: asset/Idle1
     * @param folderPath path per la directory contenuta nella cartella asset. Esempio: "idle1". Non serve mettere alcuno slash oppure backslash.
     * @return array di TextureRegions
     */
    public static TextureRegion[] loadListofImages(String folderPath){
        String path = assetPath + folderPath; //Ottento la directory dove sono contenute tutte le immagini
        File folder = new File(path); //Creo l'oggetto File
        //System.out.println(path); //Da fare in caso di alcuni errori.
        File[] listOfFiles = folder.listFiles(); //Ottengo un array di File con tutti i File contenuti in quella cartella
        TextureRegion[] walkFrames = new TextureRegion[listOfFiles.length]; //Creo un array di TextureRegion lungo tanto quanti sono i File nella cartella
        for (int i = 0; i < walkFrames.length; i++) {
            walkFrames[i] = new TextureRegion(new Texture(folderPath + "/" + listOfFiles[i].getName())); //Riempio l'array di TextureRegion con una Texture e la rispettiva immagine.
        }
        return walkFrames; //Ritrno l'Array di TextureRegion
    }
}
