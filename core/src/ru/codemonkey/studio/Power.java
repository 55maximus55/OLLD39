package ru.codemonkey.studio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ru.codemonkey.studio.screens.GameScreen;

public class Power extends Game {
	public SpriteBatch batch;
	private TextureAtlas atlas;
	public Skin skin;

	public static final int S = 10;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		skin = new Skin();
		atlas = new TextureAtlas(Gdx.files.internal("characters/player.pack"));
		skin.addRegions(atlas);
		setScreen(new GameScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
