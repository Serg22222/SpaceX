package com.serg.mygdx.game.ship;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.serg.engine.math.Rect;
import com.serg.engine.sprite.Sprite;
import com.serg.mygdx.game.bullet.Bullet;
import com.serg.mygdx.game.explosion.Explosion;
import com.serg.mygdx.game.pool.BulletPool;
import com.serg.mygdx.game.pool.ExplosionPool;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;
    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    protected final Vector2 v = new Vector2();
    protected Rect worldBounds;

    protected ExplosionPool explosionPool;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;

    protected final Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;

    protected Sound shootSound;

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        super(region, rows, cols, frames);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    public Ship(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        damageAnimateTimer += deltaTime;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        if (shootSound.play() == -1) {
            throw new RuntimeException("shootSound.play() == -1");
        }
    }

    public void boom() {
        hp = 0;
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }

    public void damage(int damage) {
        frame = 1;
        damageAnimateTimer = 0f;
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
        if (hp == 0) {
            boom();
            destroy();
        }
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public int getHp() {
        return hp;
    }
}