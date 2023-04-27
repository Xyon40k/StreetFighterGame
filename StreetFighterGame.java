package com.streetfightergame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;

public class StreetFighterGame extends ApplicationAdapter {
	public SpriteBatch batch; //batch iniziale
	private Texture image; //immagine di sfondo
	private Golem player1; //player1
	private Wraith player2; //player2

	@Override
	public void create () {
		batch = new SpriteBatch(); //Creo un nuovo SpriteBatch
		image = new Texture("Background\\Background.png"); // Creo lo sfondo.
		player1 = new Golem(10, 10, "Golem1\\Golem1.png", this.batch); //Creo un oggetto Player (parte sinistra dello schermo) e lo chiamo player1
		player2 = new Wraith(1300, 10, "Wraith1\\Wraith1.png", this.batch); // Creo un secondo oggetto Player (parte destra dello schermo e lo chiamo player2
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(image, 0, 0); //Disegno lo sfondo
		handleInput(); // Gestico l'input della tastiera
		//Player2 lo aggiorno prima così player1 sarà in primo piano rispetto a player2
		if(player2.getStatus() != ObjectStatus.Dead){ //controllo che il giocatore non sia morto
			player2.update(); //Aggiorno player2
		}
		if(player1.getStatus() != ObjectStatus.Dead){
			player1.update(); //Aggiorno player1
		}
		batch.end();
	}

	/**
	 * Gestice l'input della tastiera.
	 */
	private void handleInput() {
		/**
		 * Serie di booleani per controllare quali tasti sono stati premuti (e quindi quali azioni / metodi eseguire)
		 */
		boolean a = Gdx.input.isKeyPressed(Keys.A);
		boolean d = Gdx.input.isKeyPressed(Keys.D);
		boolean w = Gdx.input.isKeyPressed(Keys.W);
		//boolean s = Gdx.input.isKeyPressed(Keys.S);
		boolean corsa1 = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);

		boolean l = Gdx.input.isKeyPressed(Keys.L);
		boolean j = Gdx.input.isKeyPressed(Keys.J);
		boolean i = Gdx.input.isKeyPressed(Keys.I);
		//boolean k = Gdx.input.isKeyPressed(Keys.K); //"socco"
		boolean corsa2 = Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
		
		boolean attack1 = Gdx.input.isKeyPressed(Keys.SPACE) && player1.getStatus() != ObjectStatus.Attack;
		boolean attack2 = Gdx.input.isKeyPressed(Keys.NUMPAD_0) && player2.getStatus() != ObjectStatus.Attack;
		boolean specialAttack1 = Gdx.input.isKeyPressed(Keys.R) && player1.getStatus() != ObjectStatus.Attack;
		
		if(d){
			player1.setPosition(player1.getX()+Settings.runValue, player1.getY()); //player1 va a destra
			if(corsa1){
				player1.setPosition(player1.getX()+Settings.accelerazioneCorsa, player1.getY()); //player1 sta correndo
				if(player1.getStatus() != ObjectStatus.Running){
					player1.setStatus(ObjectStatus.Running); //Setto lo status a running
				}
			}
			else if(player1.getStatus() != ObjectStatus.Walking){
				player1.setStatus(ObjectStatus.Walking); //Sennò setto lo status a walking
			}
		}
		if(a){
			player1.setPosition(player1.getX()-Settings.runValue, player1.getY()); //player1 va a sinistra
			if(corsa1){
				player1.setPosition(player1.getX()-Settings.accelerazioneCorsa, player1.getY()); //player1 sta correndo
				if(player1.getStatus() != ObjectStatus.Running){
					player1.setStatus(ObjectStatus.Running); //Setto lo status a running
				}
			}
			else if(player1.getStatus() != ObjectStatus.Walking){
				player1.setStatus(ObjectStatus.Walking); //Sennò setto lo status a walking
			}
		}
		if(w){
			player1.jump();
		}
		if(l){
			player2.setPosition(player2.getX()+Settings.runValue, player2.getY()); //player2 va a destra (il tasto è il 6 del tastierno numerico)
			if(corsa2){
				player2.setPosition(player2.getX()+Settings.accelerazioneCorsa, player2.getY()); //player1 sta correndo
				if(player2.getStatus() != ObjectStatus.Running){
					player2.setStatus(ObjectStatus.Running); //Setto lo status a running
				}
			}
			else if(player2.getStatus() != ObjectStatus.Walking){
				player2.setStatus(ObjectStatus.Walking); //Sennò setto lo status a walking
			}
		}
		if(j){
			player2.setPosition(player2.getX()-Settings.runValue, player2.getY());//player2 va a sinistra (il tasto è il 4 del tastierno numerico)
			if(corsa2){
				player2.setPosition(player2.getX()-Settings.accelerazioneCorsa, player2.getY()); //player1 sta correndo
				if(player2.getStatus() != ObjectStatus.Running){
					player2.setStatus(ObjectStatus.Running); //Setto lo status a running
				}
			}
			else if(player2.getStatus() != ObjectStatus.Walking){
				player2.setStatus(ObjectStatus.Walking); //Sennò setto lo status a walking
			}
		}
		if(i){
			player2.jump();
		}
		if(attack1){ // Il player1 attacca con la barra spaziatrice
			player1.attack(player2); //player1 attacca player2
		}
		if(specialAttack1){ // Il player1 usa lo specialAttack con il tasto R
			player1.specialAttack(player2); //player1 usa l'attacco speciale contro player2 (specialAttack disponbile solo per il Golem al momento - 25/04/2023 16:30)
		}
		if(attack2){ // Il player2 attacca con il tasto 0 del tastierino numerico
			player2.attack(player1); //player2 attacca player1
		}
	}

	/**
	 * Dispose del Batch.
	 */
	@Override
	public void dispose () {
		this.batch.dispose();
		//img.dispose();
	}
}
