package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Droplet extends Rectangle {
    private static final Texture image = new Texture(Gdx.files.internal("droplet.png"));
    private final int speed=200;
    public Droplet(int windowWidth, int windowHeight) {
        this.x = MathUtils.random(0, windowWidth-64);
        this.y = windowHeight;
        this.width = 64;
        this.height = 64;
    }
    public static Texture image(){
        return image;
    }
    public void moveDown(float deltatime){
        this.y -= speed *deltatime;
    }
    public boolean isOutOfScreen(){
        return this.y + this.height < 0;
    }
}
