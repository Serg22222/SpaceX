package com.serg.mygdx.game.common;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.serg.engine.math.Rect;
import com.serg.engine.math.Rnd;
import com.serg.engine.utils.Regions;
import com.serg.mygdx.game.pool.EnemyPool;
import com.serg.mygdx.game.ship.Enemy;

public class EnemiesEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.25f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 1f;
    private static final int ENEMY_BIG_HP = 20;

    private final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private final Vector2 enemyMediumV = new Vector2(0f, -0.03f);
    private final Vector2 enemyBigV = new Vector2(0f, -0.005f);


    private final EnemyPool enemyPool;
    private Rect worldBounds;
    private Sound shootSound;

    private final TextureRegion[] enemySmallRegion;
    private final TextureRegion[] enemyMediumRegion;
    private final TextureRegion[] enemyBigRegion;

    private TextureRegion bulletRegion;

    private float generateInterval = 4f;
    private float generateTimer;

    private int stage = 1;

    public EnemiesEmitter(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas, Sound shootSound) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;

        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegion1, 1, 2, 2);
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generateEnemies(float delta, int frags) {
        stage = frags / 10 + 1;
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.7f) {
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE * stage,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        shootSound,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP * stage
                );
            } else if (type < 0.9f) {
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE * stage,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        shootSound,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP * stage
                );
            } else {
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE * stage,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        shootSound,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP * stage
                );
            }
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }

    public int getStage() {
        return stage;
    }

    public void setToNewGame() {
        stage = 1;
    }
}


