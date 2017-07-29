package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.objects.GameWorld;
import ru.codemonkey.studio.tools.GameRenderer;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameScreen implements Screen {
    private Power game;

    private GameWorld gameWorld;
    private GameRenderer renderer;

    float x = 0;
    float y = 0;

    public GameScreen(Power game) {
        this.game = game;
        gameWorld = new GameWorld("2");
        renderer = new GameRenderer(game.batch, gameWorld.map, gameWorld.world);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 10;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 10;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) y -= 10;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y += 10;

        renderer.update(x, y);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
