package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.objects.GameWorld;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameScreen implements Screen {
    private Power game;

    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private OrthogonalTiledMapRenderer mapRenderer;

    private GameWorld gameWorld;

    float x = 0;
    float y = 0;

    public GameScreen(Power game) {
        this.game = game;
        gameWorld = new GameWorld("2");

        camera = new OrthographicCamera();
        camera.setToOrtho(true);
        debugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(gameWorld.map);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = x;
        camera.position.y = y;
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        mapRenderer.setView(camera);

        mapRenderer.render();
        debugRenderer.render(gameWorld.world, camera.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 10;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 10;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) y -= 10;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y += 10;
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
