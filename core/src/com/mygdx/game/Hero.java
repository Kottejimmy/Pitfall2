package com.mygdx.game;


import com.badlogic.gdx.math.Rectangle;

public class Hero extends Figure{


    public Hero (String textureFilePath, float x, float y, int size){
        super(textureFilePath,x,y,size);


    }
    public void updatePosition(){
        //First call the method "updatePositionFromSpeed" in the Figure super class
        super.updatePosition();

        //Then do what's specific for Pacman: it should stop at the screen edges!
        stopAtEdge();
    }



    public void goRight(){
        setSpeedX(3);
        setSpeedY(0);
        getSprite().setFlip(getSpeedX()<0, false);
        getSprite().setRotation(0);
    }
    public void goLeft(){
        setSpeedX(-3);
        setSpeedY(0);
        getSprite().setFlip(getSpeedX()<0, false);
        getSprite().setRotation(0);
    }
    public void goUp(){
        setSpeedX(0);
        setSpeedY(3);
        getSprite().setFlip(false, false);
        getSprite().setRotation(90);
    }


}

