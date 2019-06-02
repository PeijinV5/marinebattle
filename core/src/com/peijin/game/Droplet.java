package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Droplet extends Moveable {
    private static final Texture image = new Texture(Gdx.files.internal("droplet.png"));
    public Droplet(int windowWidth, int windowHeight) {
        super(300);
        this.x = MathUtils.random(0, windowWidth-64);
        this.y = windowHeight;
        this.width = 64;
        this.height = 64;
    }
    public static Texture image(){
        return image;
    }

    @Override // override these methods to make them do nothing
    public void moveUp(float deltatime) {
    }

    @Override
    public void moveLeft(float deltaTime) {
    }

    @Override
    public void moveRight(float deltaTime) {
    }

    public boolean isOutOfScreen(){
        return this.y + this.height < 0;
    }
}
