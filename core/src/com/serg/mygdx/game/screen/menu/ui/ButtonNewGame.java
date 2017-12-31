package com.serg.mygdx.game.screen.menu.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.serg.engine.math.Rect;
import com.serg.engine.ui.ActionListener;
import com.serg.engine.ui.ScaledTouchUpButton;

public class ButtonNewGame extends ScaledTouchUpButton {

    public ButtonNewGame(TextureAtlas atlas, ActionListener actionListener, float pressScale) {
        super(atlas.findRegion("btPlay"), actionListener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
