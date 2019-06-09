package com.peijin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Submaine extends Moveable {
    private static final Texture image = new Texture(Gdx.files.internal("submarine.png"));
    private Sea sea;

    public Submaine(Sea sea) { //submarine can reference to sea (boundary)
        super(200);
        this.sea = sea;
        this.width = 64;
        this.height = 48;
        this.x = sea.width;
        this.y = MathUtils.random(0, sea.height-this.height);
    }

    public static Texture image(){
        return image;
    }

    public void loopBack(){
        if(this.x< 0 - this.width){
            this.x = this.sea.width + this.width;
        }
    }

}
