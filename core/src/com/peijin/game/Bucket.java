package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket extends Moveable {
    private static final Texture image=new Texture(Gdx.files.internal("bucket.png"));
    private final int windowWidth;
    public Bucket(int windowWidth) {
        super(200);
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

    @Override
    public void moveUp(float deltaTime) {
    }
    
    @Override
    public void moveDown(float deltaTime) {
    }
}
