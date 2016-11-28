package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Hero extends Figure {

    //This allows us to create our animation and give it traits
    Animation walkAnimationLeft, walkAnimationRight, walkAnimationUp, walkAnimationDown;

    //Columns and rows for our animated figure
    private int COLUMNS = 4;
    private int ROWS = 4;

    //representing how long it should stay on each frame
    float FRAME_DURATION = 0.040f;
    float time;


    public Hero (String animationSheet, float x, float y, int size){
        super(x,y,size,size);

        //load spritesheet
        HeroAnimationSheet(animationSheet);

        time = 0;

    }

    private void HeroAnimationSheet(String animationSheet) {
        //holds our whole animation as one picture
        Texture heroAnimation = new Texture(Gdx.files.internal(animationSheet));

        //With multidemensial array it allows you to hold rows + columns
        TextureRegion[][] animationFrames = TextureRegion.split(heroAnimation,heroAnimation.getWidth()/COLUMNS,heroAnimation.getHeight()/ROWS);


        //walkAnimationDown represent the row where the figure is walking down....
        walkAnimationDown = new Animation(FRAME_DURATION, animationFrames[0]);
        walkAnimationLeft = new Animation(FRAME_DURATION,animationFrames[1]);
        walkAnimationRight = new Animation(FRAME_DURATION,animationFrames[2]);
        walkAnimationUp = new Animation(FRAME_DURATION,animationFrames[3]);


    }

    public TextureRegion getPathDirectory() {

        time +=Gdx.graphics.getDeltaTime();

        if (getSpeedY() < 0)
            return walkAnimationDown.getKeyFrame(time, true);
         else if (getSpeedY() > 0)
            return walkAnimationUp.getKeyFrame(time, true);
         else if (getSpeedX() > 0)
                return walkAnimationRight.getKeyFrame(time, true);
         else
            return walkAnimationLeft.getKeyFrame(time, true);

    }

    public void updatePosition(){
        //First call the method "updatePositionFromSpeed" in the Figure super class
        super.updatePosition();

        stopAtEdge();
    }

    public void goUp(){
        setSpeedX(0);
        setSpeedY(3);
    }

    public void goDown(){
        setSpeedX(0);
        setSpeedY(-3);
    }
    public void goLeft(){
        setSpeedX(-3);
        setSpeedY(0);

    }

    public void goRight(){
        setSpeedX(3);
        setSpeedY(0);


    }






}

