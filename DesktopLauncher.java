package com.streetfightergame.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration(); //Creo una nuova configurazione
		config.setMaximized(true); //Mette il gioco in schermo intero / finestra.
		//config.setWindowedMode(1000,562); //per decidere la size della finestra
		config.setForegroundFPS(60);
		config.setTitle("StreetFighterGame"); //Decido il titolo della finestra.
		new Lwjgl3Application(new StreetFighterGame(), config); //Lancio una nuova Lwjgl3Application e gli passo un nuovo Oggetto StreetFighterGame, e la rispettiva configurazione (config).
	}
}
