package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javafx.collections.ObservableArray;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {


	SpriteBatch batch;
	Texture backGroundImg;
	ArrayList<Obstacle> obstacles;
	ArrayList<Figure> figures;
	Hero hero;

	
	@Override
	public void create () {
		createObstacles();
		createfigures();

	}

	@Override
	public void render () {
		checkInput();


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (Figure figure: figures){
			figure.updatePosition();
		}

		batch.begin();
		batch.draw(backGroundImg, 0, 0);
		for (Figure figure: figures){
			figure.draw(batch);
		}
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
	public void createObstacles(){
		obstacles = new ArrayList<Obstacle>();
		batch = new SpriteBatch();
		backGroundImg = new Texture("Backgrounds/castle.jpg");
		Obstacle sandfloor = new Obstacle("Plattform/sand.jpg",0,0,1366,20);
		Obstacle brickwalllong = new Obstacle("Plattform/brickwall.png",0,0,20,768);
		Obstacle brickwallshort = new Obstacle("Plattform/brickwall.png",Gdx.graphics.getWidth()-20,0,20,650);
		Obstacle brickplattform1 = new Obstacle("Plattform/brickPlattform.png",20,150,321,34);
		Obstacle brickplattform2 = new Obstacle("Plattform/brickPlattform.png",Gdx.graphics.getWidth()-421,150,321,34);
		Obstacle brickplattform3 = new Obstacle("Plattform/brickPlattform.png",Gdx.graphics.getWidth()/2,350,321,34);
		obstacles.add(brickwallshort);
		obstacles.add(brickwalllong);
		obstacles.add(brickplattform1);
		obstacles.add(sandfloor);
		obstacles.add(brickplattform3);
		obstacles.add(brickplattform2);
	}
	public void createfigures(){
		figures = new ArrayList<Figure>();
		hero = new Hero("Hero/Random-Red-Hero.png",200,500,100);
		hero.setSpeedY(-3);
		figures.add(hero);

	}
	public void checkInput(){
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			hero.goRight();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			hero.goLeft();
		}
	}
}
