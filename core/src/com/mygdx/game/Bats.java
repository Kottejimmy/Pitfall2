package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by TRAF on 2016-11-21.
 */
public class Bats extends Figure {

    Animation flyAnimationLeft, flyAnimationRight;
    float FRAME_DURATION = 0.040f;
    float time;
    private int COLUMNS = 3;
    private int ROWS = 4;


    public Bats(String animationSheet, float x, float y, int size) {

        super(x, y, size, size);

        EnemyAnimationSheet(animationSheet);

        time = 0;


    }

    public void EnemyAnimationSheet(String animationSheet) {

        Texture enemyAnimation = new Texture(Gdx.files.internal(animationSheet));

        TextureRegion[][] animationFrames = TextureRegion.split(enemyAnimation, enemyAnimation.getWidth() / COLUMNS, enemyAnimation.getHeight() / ROWS);

        flyAnimationLeft = new Animation(FRAME_DURATION, animationFrames[1]);
        flyAnimationRight = new Animation(FRAME_DURATION, animationFrames[3]);
    }


    public void updatePosition() {
        //First call the method "updatePositionFromSpeed" in the Figure super class
        super.updatePosition();

        bounceAtEdge();


        //Then do what's specific for a ghost: it should bounce at the screen edges!

    }

    public TextureRegion getPathDirectory() {
        time += Gdx.graphics.getDeltaTime();

        if (getSpeedX() < 0) {
            return flyAnimationLeft.getKeyFrame(time, true);
        } else if (getSpeedX() > 0) {
            return flyAnimationRight.getKeyFrame(time, true);
        }


        return null;


    }

}
