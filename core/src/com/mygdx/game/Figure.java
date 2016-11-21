package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kottejimmy on 2016-11-18.
 */
public class Figure {
    private int speedX = 0;
    private int speedY = 0;
    private Sprite sprite;



    public Figure(String textureFilePath, float x, float y, int size){

        sprite = new Sprite(new Texture(textureFilePath));
        sprite.setX(x);
        sprite.setY(y);
        sprite.setSize(size,size);

    }

    public float getX (){
        return sprite.getX();
    }
    public void setX (float x){
        sprite.setX(x);
    }
    public float getY (){
        return sprite.getY();
    }
    public void setY (float y){
        sprite.setY(y);
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
    public Sprite getSprite() {
        return sprite;
    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
    public void updatePosition(){
        setX(getX()+getSpeedX());
        setY(getY()+getSpeedY());
    }
    public Rectangle getCollisionRectangle(){
        return new Rectangle(
                getSprite().getX(),
                getSprite().getY(),
                getSprite().getWidth(),
                1);
    }
    public boolean collidesWith(Rectangle otherRect){
        if (getCollisionRectangle().overlaps(otherRect)){
        return getCollisionRectangle().overlaps(otherRect);
    }
    else
        return false;
}
}
