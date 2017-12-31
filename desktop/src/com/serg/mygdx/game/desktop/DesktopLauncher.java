package com.serg.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.serg.mygdx.game.StarGame;
import com.serg.mygdx.game.Start2DGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","Public");
		//System.setProperty("user.name","\\xD0\\xA1\\xD0\\xB5\\xD1\\x80\\xD0\\xB3\\xD0\\xB5\\xD0\\xB9");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//config.width = 1280;
		//config.height = 720;
		//config.fullscreen = true;
		//float aspect = 480f/854f;

		float aspect = 3f/4f;
		config.title = "SpaceX";
		config.height = 500;
		config.width = (int) (config.height * aspect);
		new LwjglApplication(new Start2DGame(), config);





	}
}
