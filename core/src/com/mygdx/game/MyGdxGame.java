package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {


	SpriteBatch batch;
	Texture backGroundImg;
	ArrayList<Obstacle> obstacles;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		backGroundImg = new Texture("Backgrounds/alienparkfloor.jpg");
		Obstacle brickplattform = new Obstacle("Plattform/brickPlattform.png",100,450,100,100);
		obstacles.add(brickplattform);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(backGroundImg, 0, 0);
		for (Obstacle obstacle: obstacles){
			obstacle.draw(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		backGroundImg.dispose();
	}
}
