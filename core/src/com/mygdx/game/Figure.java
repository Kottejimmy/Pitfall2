package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public abstract class Figure {

    private int speedX = 0;
    private int speedY = 0;


    private int width;
    private int height;
    private float x;
    private float y;



    public Figure(float x, float y, int width,int height){ // path to the file, positions, size
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);


    }
    public void stopAtEdge(){
        if (getX()>Gdx.graphics.getWidth()-getWidth())
            setX(Gdx.graphics.getWidth()-getWidth());
        if (getX()<0)
            setX(0);
        if (getY()>Gdx.graphics.getHeight()-getHeight())
            setY(Gdx.graphics.getHeight()-getHeight());
        if (getY()<0)
            setY(0);
    }

    private void setHeight(int height) {
        this.height = height;
    }


    private void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX (){
        return x;
    }
    public void setX (float x){
        this.x = x;
    }
    public float getY (){
        return y;
    }
    public void setY (float y){
        this.y = y;
    }
    public int getSpeedX() {
        return speedX;
    }
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    public int getSpeedY() {
        return speedY;
    }
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    public void updatePosition(){ //updateposition from speed

        if (getSpeedX()==0 && getSpeedY()==0)
            return;
        setX(getX()+getSpeedX());
        setY(getY()+getSpeedY());


    }

    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(getPathDirectory(), getX(), getY(), getWidth(), getHeight());
    }

    public abstract TextureRegion getPathDirectory();

    public Rectangle getCollisionRectangle(){
        return new Rectangle(
                getX(),
             getY(),
                getWidth(),
               getHeight());
    }
    public boolean collidesWith(Rectangle otherRect){
            return getCollisionRectangle().overlaps(otherRect);
    }
    //If we are at the screen egde...
    //...change speed to opposite direction ("bounce").
    public void bounceAtEdge(){
        if ((getX()>Gdx.graphics.getWidth()-getWidth()) || (getX()<0))
            setSpeedX(-getSpeedX());
        if ((getY()>Gdx.graphics.getHeight()-getHeight()) || (getY()<0))
            setSpeedY(-getSpeedY());
    }



}
