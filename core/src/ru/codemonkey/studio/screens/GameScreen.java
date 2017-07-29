package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.objects.GameWorld;
import ru.codemonkey.studio.objects.Player;
import ru.codemonkey.studio.tools.DETControlHandler;
import ru.codemonkey.studio.tools.GameRenderer;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameScreen implements Screen {
    private Power game;

    private GameWorld gameWorld;
    private GameRenderer renderer;
    private DETControlHandler controlHandler;
    private Player player;

    public GameScreen(Power game) {
        this.game = game;

        gameWorld = new GameWorld("2");
        renderer = new GameRenderer(game.batch, gameWorld.map, gameWorld.world);
        controlHandler = new DETControlHandler();

        player = new Player((Texture) game.skin.getDrawable("1") ,gameWorld.world, gameWorld.map, controlHandler, renderer.rayHandler, 1f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.update(player.getPos().x, player.getPos().y);
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
