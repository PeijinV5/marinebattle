package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket extends Moveable {
    private final static Texture image=new Texture(Gdx.files.internal("bucket.png"));
    private final int windowWidth;
    //In Java, if a field is declared
    // static, then exactly a single copy of that field is created and shared among all instances of that class. It doesn’t
    // matter how many times we initialize a class; there will always be only one copy of static field belonging to it.
    // The value of this static field will be shared across all object of either same of any different class. Image is a class variable.

    public Bucket(int windowWidth) { // this is the constructor.
        super(200);
        this.windowWidth = windowWidth;
        this.x = windowWidth / 2 - 64 / 2;
        this.y = (float) (480*0.67);
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
