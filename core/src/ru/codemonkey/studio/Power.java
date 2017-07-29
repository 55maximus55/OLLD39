package ru.codemonkey.studio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ru.codemonkey.studio.screens.GameScreen;

public class Power extends Game {
	public SpriteBatch batch;
	private TextureAtlas atlas, atlas1;
	public Skin skin;
	
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
