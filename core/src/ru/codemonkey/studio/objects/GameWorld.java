package ru.codemonkey.studio.objects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;

import ru.codemonkey.studio.tools.Box2dWorldCreator;
import ru.codemonkey.studio.tools.PowerContactListener;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameWorld {
    private TmxMapLoader mapLoader;
    public TiledMap map;

    public World world;

    public GameWorld(String level) {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/" + level + ".tmx");

        world = Box2dWorldCreator.createWorld(map);
    }

    public void update(float delta) {
        world.step(delta, 1, 1);
    }
}
