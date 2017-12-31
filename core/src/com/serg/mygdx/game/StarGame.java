package com.serg.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture img1;
	Sprite spr;
	float speed = 30.0f;
	float x = 50.0f;
	float y = 50.0f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background.jpg");
		img1 = new Texture("badlogic.jpg");
		spr = new Sprite(img1);
	}

	@Override
	public void render () {

		x += Gdx.graphics.getDeltaTime() * speed*2;
		y += Gdx.graphics.getDeltaTime() * speed;

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(spr, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		img1.dispose();
	}

}
