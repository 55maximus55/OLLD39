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
import ru.codemonkey.studio.objects.Player;
import ru.codemonkey.studio.objects.Povestka;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameRenderer implements Disposable {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    public RayHandler rayHandler;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private SpriteBatch hp;

    private OrthogonalTiledMapRenderer mapRenderer;

    public GameRenderer(SpriteBatch batch, TiledMap map, World world) {
        this.batch = batch;
        hp = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true);
        camera.zoom = 1;

        this.world = world;
        debugRenderer = new Box2DDebugRenderer();

        rayHandler = new RayHandler(world);
        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(false);
        rayHandler.setBlur(true);
        rayHandler.setBlurNum(1);
        rayHandler.setShadows(true);
        rayHandler.setCulling(true);
        rayHandler.setAmbientLight(0.05f);

        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    public void render(Player player, ArrayList<Bullet> bullets, ArrayList<Enemy> mobs, ArrayList<Povestka> povestkas, BitmapFont font) {
        mapRenderer.render();
        batch.begin();
        for (int i = 0; i < mobs.size();i++){
            mobs.get(i).draw(batch);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(batch);
        }
        for (int i = 0; i < povestkas.size(); i++) {
            povestkas.get(i).draw(batch);
        }

        player.draw(batch);
        batch.end();
        rayHandler.setCombinedMatrix(camera);
        hp.begin();
        font.draw(hp, "HP " + player.HP, 100, 100);
        hp.end();
        rayHandler.updateAndRender();
//        debugRenderer.render(world, camera.combined);
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

    public void resize(int width, int height) {
        camera.viewportWidth = 700f;
        camera.viewportHeight = 700f * height / width;
        camera.update();
        rayHandler.resizeFBO(width / 2, height / 2);
    }
}
