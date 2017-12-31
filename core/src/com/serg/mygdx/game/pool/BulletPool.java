package com.serg.mygdx.game.pool;

import com.serg.engine.pool.SpritesPool;
import com.serg.mygdx.game.bullet.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
       // System.out.println("Bullet pool change active/free : " + activeObjects.size() + " / " + freeObjects.size());
    }
}

