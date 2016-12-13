package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jimmyjonsson on 2016-11-29.
 */
public class InteractiveObject extends Obstacle {

    public InteractiveObject(float x_position,float y_position, int width, int height){
        super(x_position,y_position,width,height);
    }

    public InteractiveObject(String textureFilepath, float x_position, float y_position, int width, int height) {
        super(textureFilepath, x_position, y_position, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(
                sprite.getX() + (sprite.getWidth() / 2),
                sprite.getY() + (sprite.getHeight() / 3),
                sprite.getWidth() - (sprite.getWidth()),
                sprite.getHeight() - (sprite.getHeight() / 3) + 1);


    }
}
