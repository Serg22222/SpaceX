package com.serg.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.serg.engine.math.Rect;
import com.serg.engine.sprite.Sprite;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}

