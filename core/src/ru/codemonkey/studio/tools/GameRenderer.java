package ru.codemonkey.studio.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameRenderer implements Disposable {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    public RayHandler rayHandler;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private OrthogonalTiledMapRenderer mapRenderer;

    public GameRenderer(SpriteBatch batch, TiledMap map, World world) {
        this.batch = batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        debugRenderer = new Box2DDebugRenderer();
        this.world = world;

        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    public void render() {
        mapRenderer.render();
        debugRenderer.render(world, camera.combined);
    }

    public void update(float x, float y) {
        camera.position.x = x;
        camera.position.y = y;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        mapRenderer.setView(camera);
    }

    @Override
    public void dispose() {

    }
}
