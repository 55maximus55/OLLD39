package ru.codemonkey.studio.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import ru.codemonkey.studio.DET;
import ru.codemonkey.studio.tools.Box2dLightWorldCreator;
import ru.codemonkey.studio.tools.Box2dWorldCreator;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameWorld implements Disposable {
    public TiledMap map;

    public World world;
    public World worldLight;

    public Player player;
    public ArrayList<Bullet> bullets;

    public ArrayList<Enemy> enemies;
    public ArrayList<Povestka> povestkas;

    public GameWorld(DET game, String levelName) {
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/" + levelName + ".tmx");

        world = Box2dWorldCreator.createWorld(map);
        worldLight = Box2dLightWorldCreator.createWorld(map);

        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        povestkas = new ArrayList<Povestka>();

        for(MapObject object : map.getLayers().get("enemy").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            Vector2 pos = new Vector2(rect.getX() / DET.S + rect.getWidth() / 2 / DET.S, rect.getY() / DET.S + rect.getHeight() / 2 / DET.S);
            enemies.add(new Enemy(game.skin.getRegion("soldier"), this, pos));
        }
    }

    public void update(float delta) {
        player.update();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(delta, player.getPos(), enemies);
        }

        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).a) {
                bullets.get(i).update();
            } else {
                world.destroyBody(bullets.get(i).getBody());
                bullets.remove(i);
            }
        }
        for (int i = 0; i < povestkas.size(); i++) {
            if (povestkas.get(i).a) {
                povestkas.get(i).update();
            } else {
                world.destroyBody(povestkas.get(i).getBody());
                povestkas.remove(i);
            }
        }

        world.step(delta, 1, 1);
    }

    @Override
    public void dispose() {
        map.dispose();
        player.dispose();
    }
}
