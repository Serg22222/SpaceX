package com.serg.mygdx.game.screen.menu.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.serg.engine.math.Rect;
import com.serg.engine.ui.ActionListener;
import com.serg.engine.ui.ScaledTouchUpButton;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas, ActionListener actionListener, float pressScale) {
        super(atlas.findRegion("btExit"), actionListener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
