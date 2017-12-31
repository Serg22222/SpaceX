package com.serg.mygdx.game;

import com.badlogic.gdx.Game;
import com.serg.mygdx.game.screen.menu.MenuScreen;

public class Start2DGame extends Game{
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
