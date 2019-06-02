package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Droplet extends Rectangle {
    private static final Texture image = new Texture(Gdx.files.internal("droplet.png"));
    public Droplet() {
        this.x = MathUtils.random(0, 800-64);
        this.y = 480;
        this.width = 64;
        this.height = 64;
    }
    public static Texture image(){
        return image;
    }
    public void moveDown(int speed){
        this.y -= speed * Gdx.graphics.getDeltaTime();
    }
    public boolean isOutOfScreen(){
        return this.y + this.height < 0;
    }
}
