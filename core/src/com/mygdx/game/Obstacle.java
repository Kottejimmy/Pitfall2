package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kottejimmy on 11/18/2016.
 */
public class Obstacle {

    Sprite sprite;

    public Obstacle (String textureFilepath, float x_position, float y_position, int width,int height){

        sprite = new Sprite(new Texture(textureFilepath));
        sprite.setX(x_position);
        sprite.setX(y_position);
        sprite.setSize(width,height);

    }


    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
}
