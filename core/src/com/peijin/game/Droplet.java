package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Droplet extends Moveable {
    private static final Texture image = new Texture(Gdx.files.internal("droplet.png"));
    private Sea sea;
    private boolean isReleased;

    public Droplet(Sea sea) {
        super(300);
        this.sea = sea;
        this.x = MathUtils.random(0, sea.width-64);
        this.y =sea.height;
        this.width = 32;
        this.height = 32;
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

    public void movetobucket(Bucket bucket) {this.x=bucket.x+this.width/2; this.y=bucket.y-this.height;}

    public boolean isOutOfScreen(){
        return this.y + this.height < 0;
    }

    public boolean isReleased() {
        return this.isReleased;
    }

    public void setIsReleased(boolean isReleased){
        this.isReleased = isReleased;
    }

    public void remove() {
        this.x = -100;
        this.isReleased = false;
    }
}
