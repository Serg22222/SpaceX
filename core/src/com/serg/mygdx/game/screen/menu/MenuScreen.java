package com.serg.mygdx.game.screen.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.serg.engine.Base2DScreen;
import com.serg.engine.Sprite2DTexture;
import com.serg.engine.math.Rect;
import com.serg.engine.math.Rnd;
import com.serg.engine.ui.ActionListener;
import com.serg.mygdx.game.Background;
import com.serg.mygdx.game.screen.game.GameScreen;
import com.serg.mygdx.game.screen.menu.ui.ButtonExit;
import com.serg.mygdx.game.screen.menu.ui.ButtonNewGame;
import com.serg.mygdx.game.star.Star;

/**
 * Меню
 */
public class MenuScreen extends Base2DScreen implements ActionListener {

    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private static final float BUTTON_HEIGHT = 0.15f;
    private static final int STAR_COUNT = 256;
    private static final float STAR_WIDTH = 0.01f;

    private Sprite2DTexture textureBackground;
    private Background background;
    private TextureAtlas textureAtlas;
    private ButtonExit buttonExit;
    private ButtonNewGame buttonNewGame;

    private Star star[];

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureBackground = new Sprite2DTexture("textures/background.jpg");
        background = new Background(new TextureRegion(textureBackground));
        textureAtlas = new TextureAtlas("textures/menuAtlas.tpack");
        buttonExit = new ButtonExit(textureAtlas, this, BUTTON_PRESS_SCALE);
        buttonExit.setHeightProportion(BUTTON_HEIGHT);
        buttonNewGame = new ButtonNewGame(textureAtlas, this, BUTTON_PRESS_SCALE);
        buttonNewGame.setHeightProportion(BUTTON_HEIGHT);

        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(textureAtlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), STAR_WIDTH);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    /**
     * Метод обновления информации об объектах
     *
     * @param delta
     */
    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
    }


    /**
     * Метод отрисовки
     */
    public void draw() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        buttonExit.draw(batch);
        buttonNewGame.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas.dispose();
        textureBackground.dispose();
        batch = null;
    }

    @Override
    protected void resize(Rect worldBounds) {
        background.resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
    }

    @Override
    public void touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch, pointer);
        buttonNewGame.touchDown(touch, pointer);
        System.out.println("touchDown touch.x = " + touch.x + "  touch.y = " + touch.y);
    }

    @Override
    public void touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonNewGame.touchUp(touch, pointer);
        System.out.println("touchUp touch.x = " + touch.x + "  touch.y = " + touch.y);
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonExit) {
            Gdx.app.exit();
        } else if (src == buttonNewGame) {
            game.setScreen(new GameScreen(game));
        } else {
            throw new RuntimeException("Unknown src = " + src);
        }
    }
}
