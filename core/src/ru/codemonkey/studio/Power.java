package ru.codemonkey.studio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.codemonkey.studio.screens.GameScreen;

public class Power extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
