package ru.codemonkey.studio.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import box2dLight.RayHandler;
import ru.codemonkey.studio.objects.Bullet;
import ru.codemonkey.studio.objects.Enemy;
import ru.codemonkey.studio.objects.GameWorld;
import ru.codemonkey.studio.objects.Player;
import ru.codemonkey.studio.objects.Povestka;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameRenderer implements Disposable {
    public RayHandler rayHandler;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private OrthogonalTiledMapRenderer mapRenderer;

    public GameRenderer(SpriteBatch batch, GameWorld gameWorld) {
        this.batch = batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        rayHandler = new RayHandler(gameWorld.worldLight);
        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(false);
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);
        rayHandler.setShadows(true);
        rayHandler.setCulling(true);
        rayHandler.setAmbientLight(0.1f);

        mapRenderer = new OrthogonalTiledMapRenderer(gameWorld.map);
    }

    public void resize(int width, int height) {
        camera.viewportWidth = 700f;
        camera.viewportHeight = 700f * height / width;
        camera.update();
        rayHandler.resizeFBO(width / 2, height / 2);
    }

    public void update(float x, float y) {
        camera.position.x = x;
        camera.position.y = y;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        rayHandler.setCombinedMatrix(camera);
        mapRenderer.setView(camera);
    }

    public void render(GameWorld gameWorld) {
        mapRenderer.render();
        batch.begin();
        for (int i = 0; i < gameWorld.enemies.size(); i++) {
            gameWorld.enemies.get(i).draw(batch);
        }
        for (int i = 0; i < gameWorld.bullets.size(); i++) {
            gameWorld.bullets.get(i).draw(batch);
        }
        for (int i = 0; i < gameWorld.povestkas.size(); i++) {
            gameWorld.povestkas.get(i).draw(batch);
        }
        gameWorld.player.draw(batch);
        batch.end();
        rayHandler.updateAndRender();
    }

    @Override
    public void dispose() {
        rayHandler.dispose();
        mapRenderer.dispose();
    }
}
