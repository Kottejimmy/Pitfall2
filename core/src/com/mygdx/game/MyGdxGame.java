package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.ApplicationAdapter;


import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {


	private static final int  FRAME_COLS = 10; //defines constants representing how many sprites are laid out horizontally and vertically
	private static final int  FRAME_ROWS = 1;

	BitmapFont font; //Declared to use text.

	Texture backGroundImg;
	ArrayList<Obstacle> obstacles;
	ArrayList<Figure> figures;

	Hero hero; // //an extra reference to Hero, for convenience.

	Animation  walkAnimation; // Allows us to create animated figure.
	Texture walkSheet;		  //The Texture which will contain the whole sheet as a single image (texture).
	TextureRegion[]  walkFrames; //Declare walkFrames as an array, the array holds each frame of the animation.
	SpriteBatch  spriteBatch;    //he SpriteBatch is used to draw the texture onto the screen.
	TextureRegion   currentFrame; //This variable will hold the current frame and this is the region which is drawn on each render call.

	float stateTime;   // The stateTime is the number of seconds elapsed from the start of the animation.


	private enum GameState {
		START_SCREEN,
		CONTROLS,
		GAME_OVER,
		LEVEL_ONE,
		LEVEL_TWO,
		LEVEL_THREE,
		LEVEL_FOUR,
		LEVEL_FIVE,
		LEVEL_SIX,
		LEVEL_SEVEN,
		LEVEL_EIGHT,
		LEVEL_NINE,
		LEVEL_TEN;

	}
	Texture start;
	Texture controls;
	Texture gameover;
	GameState state = GameState.START_SCREEN;



	@Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.RED);
		createObstacles();
		createfigures();
		createEnemy();
		createTreasure();
		start = new Texture("gamescenarios/pitfall2_startscreen.png");
		controls = new Texture("gamescenarios/pitfall2_controls.png");
		gameover = new Texture("gamescenarios/Game_Over_Screen.png");
		Gdx.input.setInputProcessor(this);


	}


	@Override
			public void render () {


		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			System.exit(0);
		}


		switch (state){
			case START_SCREEN:
				showStartScreen();
				break;
			case CONTROLS:
				showControls();
				break;
			case LEVEL_ONE:
				renderLevelOne();
				break;
			case GAME_OVER:
				gameOver();
				break;
		}




	}

	// free up resources when the game exits.
			@Override
			public void dispose () {
				spriteBatch.dispose();
				backGroundImg.dispose();

			}
		public void createObstacles () {
			obstacles = new ArrayList<Obstacle>();

			backGroundImg = new Texture("Backgrounds/castle.jpg");
			Obstacle sandfloor = new Obstacle("Plattform/brickPlattform.png", 0, 0, 1300, 40);
			Obstacle brickplattform1 = new Obstacle("Plattform/brickPlattform.png", 20, 350, 321, 40);
			Obstacle brickplattform5 = new Obstacle("Hero/ladder.png", 920, 160, 50, 280);
			Obstacle brickplattform4 = new Obstacle("Plattform/brickPlattform.png", 300, 80, 400, 40);
			Obstacle brickplattform2 = new Obstacle("Plattform/brickPlattform.png", Gdx.graphics.getWidth() - 421, 130, 321, 40);
			Obstacle brickplattform3 = new Obstacle("Plattform/brickPlattform.png", Gdx.graphics.getWidth() - 421, 400, 400, 40);
			obstacles.add(brickplattform1);
			obstacles.add(sandfloor);
			obstacles.add(brickplattform3);
			obstacles.add(brickplattform2);
			obstacles.add(brickplattform4);
			obstacles.add(brickplattform5);
		}
		public void createfigures () {
			figures = new ArrayList<Figure>();
			hero = new Hero("Hero/Cng-Hiro.png", 200, 500, 120);
			figures.add(hero);


		}
		public void createEnemy() {
			Bats bats;
			bats = new Bats("Plattform/bat12.png", Gdx.graphics.getWidth() / 2-200, Gdx.graphics.getHeight() / 2+20, 50);
			bats.setSpeedX(1);
			figures.add(bats);

			bats = new Bats("Plattform/bat12.png", Gdx.graphics.getWidth() / 2+600, Gdx.graphics.getHeight() / 2-200, 50);
			bats.setSpeedX(1);
			figures.add(bats);


		}

		public void createTreasure() {
			//1 Creates a texture from animation_sheet.png which is placed in the walksheet directory of the project
			//2 Using the split convenience method on the texture, we obtain a two dimensional array of the frames from the texture,  the walkFrames array is populated. This is necessary, as the Animation works with one dimensional arrays only.

			walkSheet = new Texture(Gdx.files.internal("Plattform/coiiins.png"));
			TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);              // #10
			walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
			int index = 0;
			for (int i = 0; i < FRAME_ROWS; i++) {
				for (int j = 0; j < FRAME_COLS; j++) {
					walkFrames[index++] = tmp[i][j];
				}

				walkAnimation = new Animation(0.080f, walkFrames); //3 his is where the Animation is created. The first parameter tells the animation, how much time is allocated for each frame.
				spriteBatch = new SpriteBatch(); //4 Initialises the SpriteBatch which will draw the frame.
				stateTime = 0f; // Resets the stateTime to 0. It will start accumulating the time each render call.


			}

		}
		public void checkObstacleCollision() {


		for (int i =0;i<figures.size(); i++ ) {
			for (Obstacle obstacle : obstacles) {
				if (figures.get(i) instanceof Hero) {
					if (figures.get(i).collidesWith(obstacle.getCollisionRectangle())) {

						if (hero.getSpeedY()<1) {
							hero.heroStateWalking();
							figures.get(i).setSpeedY(0);
							break;
						}
					}
					if (!figures.get(i).collidesWith(obstacle.getCollisionRectangle())) {
						//max speedY = -5 for each render speedY becomes faster (increase fallspeed)

						if (figures.get(i).getSpeedY()>-5){
							hero.heroStateFlying();
							figures.get(i).setSpeedY(figures.get(i).getSpeedY()-0.02f);
						}
					}
				}

			}
		}

	}

	 	public void checkEnemyCollision() {
			for(int i = 0; i < figures.size(); i++) {
				if(figures.get(i) instanceof Bats) {
					if(hero.collidesWith(figures.get(i).getCollisionRectangle())) {
						state = GameState.GAME_OVER;
						createObstacles ();
						createfigures();
						createEnemy();
					}
				}
			}
		}

		public void updatePositions (){
		for (Figure figure: figures) {
			figure.updatePosition();
		}
	}


		public void checkInput () {
			if(hero.getState()== Hero.HeroState.FLYING){

			}

		}


	public void showStartScreen(){
		spriteBatch.begin();
		spriteBatch.draw(start, 411, 144);
		spriteBatch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
			state  = GameState.LEVEL_ONE;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
			state = GameState.CONTROLS;
		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
			System.exit(0);
		}  else if (Gdx.input.isKeyPressed(Input.Keys.NUM_5)){
			state = GameState.GAME_OVER;
		}
	}

	public void gameOver() {
		spriteBatch.begin();
		spriteBatch.draw(gameover, 480, 210);
		spriteBatch.end();

	}


	public void showControls(){
		spriteBatch.begin();
		spriteBatch.draw(controls, 411, 144);
		spriteBatch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)){
			state = GameState.START_SCREEN;
		}
	}

	public void renderLevelOne(){
		checkInput();


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); //Clears the screen each frame.
		stateTime += Gdx.graphics.getDeltaTime(); // Adds the time elapsed since the last render to the stateTime.

		currentFrame = walkAnimation.getKeyFrame(stateTime, true); //Obtains the current frame

		checkObstacleCollision();
		checkEnemyCollision();
		updatePositions();



		for (Figure figure : figures) {
			figure.updatePosition();
		}
		spriteBatch.begin();



		spriteBatch.draw(backGroundImg, 0, 20);
		spriteBatch.draw(currentFrame, 980, 440); // Renders the current frame onto the screen using the super awesome SpriteBatch at 50,50.
		spriteBatch.draw(currentFrame, 300, 120);
		font.draw(spriteBatch,"Number of lives:", 20,700);




		//Update all game figures' positions based on their speeds
		for (Figure figure : figures) {
			figure.draw(spriteBatch);
		}


		for (Obstacle obstacle : obstacles) {
			obstacle.draw(spriteBatch);
		}


		spriteBatch.end();
		if (hero.getState()== Hero.HeroState.WALKING && !(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))){
			hero.setSpeedX(0);
		}

	}


	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.SPACE && state == GameState.LEVEL_ONE){
			hero.jump();
		}
		if (keycode == Input.Keys.RIGHT && state == GameState.LEVEL_ONE){
			hero.goRight();
		}
		if (keycode == Input.Keys.LEFT && state == GameState.LEVEL_ONE){
			hero.goLeft();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.NUM_1 && state == GameState.GAME_OVER){
				state = GameState.START_SCREEN;
			}

		if (keycode == Input.Keys.RIGHT&&state== GameState.LEVEL_ONE&& hero.getSpeedY()==0){
			hero.stop();
		}
		if (keycode == Input.Keys.LEFT&&state== GameState.LEVEL_ONE&& hero.getSpeedY()==0){
			hero.stop();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}

