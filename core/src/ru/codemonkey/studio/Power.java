package ru.codemonkey.studio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ru.codemonkey.studio.screens.MainMenuScreen;

public class Power extends Game {
	public SpriteBatch batch;
	private TextureAtlas atlas, atlas1;
	public Skin skin;
	public BitmapFont font16;
	public BitmapFont font24;
	public BitmapFont font32;

	public static final int S = 10;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		skin = new Skin();
		atlas = new TextureAtlas(Gdx.files.internal("characters/characters.pack"));
		atlas1 =  new TextureAtlas(Gdx.files.internal("objects/objects.pack"));
		skin.addRegions(atlas);
		setScreen(new MainMenuScreen(this));

		font16 = new BitmapFont(Gdx.files.internal("fonts/roboto_16.fnt"), Gdx.files.internal("fonts/roboto_16.png"), false);
		font24 = new BitmapFont(Gdx.files.internal("fonts/roboto_24.fnt"), Gdx.files.internal("fonts/roboto_24.png"), false);
		font32 = new BitmapFont(Gdx.files.internal("fonts/roboto_32.fnt"), Gdx.files.internal("fonts/roboto_32.png"), false);


	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
