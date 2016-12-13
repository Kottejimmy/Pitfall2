package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Kottejimmy on 2016-12-13.
 */
public class MovingObstacles extends Obstacle {
    static Animation walkAnimation; // Allows us to create animated figure.
    Texture walkSheet;          //The Texture which will contain the whole sheet as a single image (texture).
    TextureRegion[] walkFrames; //Declare walkFrames as an array, the array holds each frame of the animation.
    float stateTime;   // The stateTime is the number of seconds elapsed from the start of the animation.
    private float y;
    private float x;
    private static final int FRAME_COLS = 1; //defines constants representing how many sprites are laid out horizontally and vertically
    private static final int FRAME_ROWS = 4;

    public MovingObstacles(String textureFilepath, float x_position, float y_position, int width, int height) {
        super(textureFilepath, x_position, y_position, width, height);
        x = x_position;
        y = y_position;
        animateObstacle();
    }


    public void animateObstacle() {

        walkSheet = new Texture(Gdx.files.internal("Plattform/Watersheet.gif"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }

            walkAnimation = new Animation(0.080f, walkFrames); //3 This is where the Animation is created. The first parameter tells the animation, how much time is allocated for each frame.
            stateTime = 0f; // Resets the stateTime to 0. It will start accumulating the time each render call.

        }

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(MyGdxGame.currentFrame, x, y);
    }
}
