package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by TRAF on 2016-12-07.
 */
public class Blocks extends Obstacle {

    public Blocks(String textureFilepath, float x_position, float y_position, int width, int height) {
        super(textureFilepath, x_position, y_position, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(
                sprite.getX() + (sprite.getWidth() -10),
                sprite.getY() + (sprite.getHeight()),
                sprite.getWidth() - (sprite.getWidth()),
                sprite.getHeight() - (sprite.getHeight() / 3) + 1);

    }
}
