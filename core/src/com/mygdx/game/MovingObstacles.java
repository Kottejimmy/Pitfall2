package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Kottejimmy on 2016-12-13.
 */
public class MovingObstacles extends Figure {
    Animation waterAnimation; // Allows us to create animated figure.
    float time;   // The time is the number of seconds elapsed from the start of the animation.
    float FRAME_DURATION = 0.080f;
    private static final int FRAME_ROWS = 4;

    public MovingObstacles(String animationSheet, float x, float y, int size) {
        super(x, y, size, size);

        //load spritesheet
        movingObstacleAnimationSheet(animationSheet);

        time = 0;

    }


    private void movingObstacleAnimationSheet(String animationSheet) {
        //holds our whole animation as one picture
        Texture obstacleAnimation = new Texture(Gdx.files.internal(animationSheet));

        //With multidemensial array it allows you to hold rows + columns
        TextureRegion[][] animationFrames = TextureRegion.split(obstacleAnimation, obstacleAnimation.getWidth(),obstacleAnimation.getHeight()/4);


        //walkAnimationDown represent the row where the figure is walking down....
        waterAnimation = new Animation(FRAME_DURATION,animationFrames[3]);


    }


    public TextureRegion getPathDirectory(){
        time +=Gdx.graphics.getDeltaTime();
        return waterAnimation.getKeyFrame(time,true);
    }



}
