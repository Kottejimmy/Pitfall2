package com.mygdx.game;

/**
 * Created by TRAF on 2016-11-21.
 */
public class Bats extends Figure {
    public Bats(String textureFilePath, float x, float y, int size) {

        super(textureFilePath, x, y, size);
    }


    public void updatePosition(){
        //First call the method "updatePositionFromSpeed" in the Figure super class
        super.updatePosition();

        bounceAtEdge();


        //Then do what's specific for a ghost: it should bounce at the screen edges!

    }
}
