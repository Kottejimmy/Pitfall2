package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by TRAF on 2016-11-21.
 */
public class Bats extends Figure {



    private TextureRegion image;
    public Bats(String textureFilePath, float x, float y, int size) {

        super(x, y, size,size);

        image = new TextureRegion(new Texture(Gdx.files.internal(textureFilePath)));

    }


    public void updatePosition(){
        //First call the method "updatePositionFromSpeed" in the Figure super class
        super.updatePosition();

        bounceAtEdge();


        //Then do what's specific for a ghost: it should bounce at the screen edges!

    }
    @Override
    public TextureRegion getPathDirectory() {
        return image;
    }

}
