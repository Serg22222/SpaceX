package com.serg.mygdx.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.serg.engine.pool.SpritesPool;
import com.serg.mygdx.game.explosion.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private Sound sound;

    private final TextureRegion explosionRegion;

    public ExplosionPool(TextureAtlas atlas, Sound sound) {
        explosionRegion = atlas.findRegion("explosion");
        this.sound = sound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(explosionRegion, 9,9,74, sound);
    }

    @Override
    protected void debugLog() {
        System.out.println("Explosion pool change active/free : " + activeObjects.size() + " / " + freeObjects.size());
    }
}

