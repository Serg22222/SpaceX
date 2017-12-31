package com.serg.mygdx.game.star;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class TrackingStar extends Star {

    private final Vector2 trackingV;

    private final Vector2 sumV = new Vector2();


    public TrackingStar(TextureAtlas atlas, float vx, float vy, float width, Vector2 trackingV) {
        super(atlas, vx, vy, width);
        this.trackingV = trackingV;
    }

    @Override
    public void update(float deltaTime) {
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        pos.mulAdd(sumV, deltaTime);
        checkAndHandleBounds();
    }
}
