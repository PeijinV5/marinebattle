package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket extends Rectangle {
    private static final Texture image=new Texture(Gdx.files.internal("bucket.png"));
    private final int windowWidth;
    private final int speed=200;
    public Bucket(int windowWidth) {
        this.windowWidth = windowWidth;

        this.x = windowWidth / 2 - 64 / 2;
        this.y = 20;
        this.width = 64;
        this.height = 64;
    }

    public static Texture image(){
        return image;
    }
    public void ensureInScreen() {
        if(this.x < 0) this.x=0;
        if(this.x > this.windowWidth-this.width) this.x=this.windowWidth-this.width;
    }
    public void moveLeft(float deltaTime) {
        this.x -= speed * deltaTime;
    }
    public void moveRight(float deltaTime){
        this.x += speed * deltaTime;
    }

}
