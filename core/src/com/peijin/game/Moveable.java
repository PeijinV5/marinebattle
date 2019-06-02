package com.peijin.game;

// abstract class: the classes share the same properties.

import com.badlogic.gdx.math.Rectangle;

public abstract class Moveable extends Rectangle {
    protected final int speed;

    public Moveable(int speed) {
        this.speed = speed;
    }


    public void moveDown(float deltatime){
        this.y -= speed *deltatime;
    }
    public void moveUp(float deltatime){
        this.y += speed *deltatime;
    }
    public void moveLeft(float deltaTime) {
        this.x -= speed * deltaTime;
    }
    public void moveRight(float deltaTime){
        this.x += speed * deltaTime;
    }
}
