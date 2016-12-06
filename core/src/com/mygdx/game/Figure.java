package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public abstract class Figure {

    private final int collisionRadius;
    private float speedX = 0;
    private float speedY = 0;
    private int width;
    private int height;
    private float x;
    private float y;


    public Figure(float x, float y, int width, int height) { // path to the file, positions, size
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        collisionRadius = getWidth() / 15;


    }

    public void stopAtEdge() {
        if (getX() > Gdx.graphics.getWidth() - getWidth()) {
            setX(Gdx.graphics.getWidth() - getWidth());
        }
        if (getX() < 0) {
            setX(0);
        }
        if (getY() > Gdx.graphics.getHeight() - getHeight()) {
            setY(Gdx.graphics.getHeight() - getHeight());
            setSpeedY(-0.1f);
        }
        if (getY() < 0) {
            setY(0);
        }
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void updatePosition() { //updateposition from speed

        if (getSpeedX() == 0 && getSpeedY() == 0)
            return;
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());


    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(getPathDirectory(), getX(), getY(), getWidth(), getHeight());
    }

    public abstract TextureRegion getPathDirectory();

    public Rectangle getCollisionRectangle() {
        return new Rectangle(
                getX() + getWidth() / 2,
                getY() + getHeight() / 2,
                getWidth() - (80),
                getHeight() - (80));
    }

    public boolean collidesWith(Rectangle otherRect) {
        return getCollisionRectangle().overlaps(otherRect);
    }

    //If we are at the screen egde...
    //...change speed to opposite direction ("bounce").
    public void bounceAtEdge() {
        if ((getX() > Gdx.graphics.getWidth() - getWidth()) || (getX() < 0))
            setSpeedX(-getSpeedX());
        if ((getY() > Gdx.graphics.getHeight() - getHeight()) || (getY() < 0))
            setSpeedY(-getSpeedY());
    }
    public void stopAtPlatform() {
        if((getX() > Gdx.graphics.getWidth() - 180 || (getX() < 600)))
            setSpeedX(-getSpeedX());
    }


}
