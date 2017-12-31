package com.serg.engine;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.serg.engine.math.MatrixUtils;
import com.serg.engine.math.Rect;
import com.serg.engine.sprite.Sprite;

/**
 * Базовый класс для экранов
 */

public class Base2DScreen implements Screen, InputProcessor {

    Sprite s;

    protected Game game;
    private Rect screenBounds; // границы области рисования в пикселях
    protected Rect worldBounds; // границы проекции мировых координат
    private Rect glBounds; // дефолтные границы проекции мир - gl

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    Vector2 touch = new Vector2();

    protected SpriteBatch batch;

    public Base2DScreen(Game game) {
        this.game = game;
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0,0,1f,1f);
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();
        if (this.batch != null) {
            throw new RuntimeException("Повторная установка screen без dispose");
        }
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {
        System.out.println("show");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + " height = " + height);
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f * aspect);
        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);

        MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds, worldBounds);
        resize(worldBounds);

    }

    protected void resize(Rect worldBounds) {

    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight() - 1f - screenY).mul(screenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    public void touchDown(Vector2 touch, int pointer) {

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight() - 1f - screenY).mul(screenToWorld);
        touchUp(touch, pointer);
        return false;
    }

    public void touchUp(Vector2 touch, int pointer) {

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, screenBounds.getHeight() - 1f - screenY).mul(screenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    public void touchDragged(Vector2 touch, int pointer) {

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
