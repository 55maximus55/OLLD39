package ru.codemonkey.studio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ru.codemonkey.studio.screens.MainMenuScreen;

public class DET extends Game {
	public SpriteBatch batch;

	public Skin skin;
	private TextureAtlas characters, objects;

	public BitmapFont font16;
	public BitmapFont font24;
	public BitmapFont font32;

	public static final float S = 10f;

	@Override
	public void create() {
		batch = new SpriteBatch();
		skin = new Skin();

		characters = new TextureAtlas("textures/characters.pack");
		objects = new TextureAtlas("textures/objects.pack");

		skin.addRegions(characters);
		skin.addRegions(objects);

		font16 = new BitmapFont(Gdx.files.internal("fonts/roboto_16.fnt"), Gdx.files.internal("fonts/roboto_16.png"), false);
		font24 = new BitmapFont(Gdx.files.internal("fonts/roboto_24.fnt"), Gdx.files.internal("fonts/roboto_24.png"), false);
		font32 = new BitmapFont(Gdx.files.internal("fonts/roboto_32.fnt"), Gdx.files.internal("fonts/roboto_32.png"), false);

		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();

		skin.dispose();
		characters.dispose();
		objects.dispose();

		font16.dispose();
		font24.dispose();
		font32.dispose();
	}
}
