package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by kottejimmy on 11/18/2016.
 */
public class Obstacle {

    Sprite sprite;



    public Obstacle(String textureFilepath, float x_position, float y_position, int width, int height) {

        sprite = new Sprite(new Texture(textureFilepath));
        sprite.setX(x_position);
        sprite.setY(y_position);
        sprite.setSize(width, height);



    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Rectangle getCollisionRectangle() {
        return new Rectangle(
                sprite.getX()+40 ,
                sprite.getY()+sprite.getHeight(),
                sprite.getWidth()-90,
                1);


    }
}
