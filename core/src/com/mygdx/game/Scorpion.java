package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by TRAF on 2016-12-05.
 */
public class Scorpion extends Figure {

    Animation walkLeft, walkRight;

    float FRAME_DURATION = 0.040f;
    float time;
    private int COLUMNS = 6;
    private int ROWS = 4;

    public Scorpion(String animationSheet, float x, float y, int width, int height) {
        super(x, y, width, height);

        EnemyAnimationSheet(animationSheet);
        setSpeedX(1.5f);
    }

    public void EnemyAnimationSheet(String animationSheet) {

        Texture enemyAnimation = new Texture(Gdx.files.internal(animationSheet));

        TextureRegion[][] animationFrames = TextureRegion.split(enemyAnimation, enemyAnimation.getWidth() / COLUMNS, enemyAnimation.getHeight() / ROWS);

        walkLeft = new Animation(FRAME_DURATION, animationFrames[3]);
        walkRight = new Animation(FRAME_DURATION, animationFrames[2]);
    }

    public void updatePosition() {
        //First call the method "updatePositionFromSpeed" in the Figure super class
        super.updatePosition();

        stopAtPlatform();



        //Then do what's specific for a ghost: it should bounce at the screen edges!

    }

    public TextureRegion getPathDirectory() {
        time += Gdx.graphics.getDeltaTime();

        if (getSpeedX() < 0) {
            return walkRight.getKeyFrame(time, true);
        } else if (getSpeedX() > 0) {
            return walkLeft.getKeyFrame(time, true);
        }


        return null;


    }

}
