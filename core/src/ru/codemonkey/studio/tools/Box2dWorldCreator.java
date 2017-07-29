package ru.codemonkey.studio.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import ru.codemonkey.studio.Power;

/**
 * Created by maximus on 29.07.2017.
 */

public class Box2dWorldCreator {
    public static World createWorld (TiledMap map) {
        World world = new World(new Vector2(0, 0), true);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get("walls").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / Power.S + rect.getWidth() / Power.S / 2, rect.getY() / Power.S + rect.getHeight() / Power.S / 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / Power.S / 2, rect.getHeight() / Power.S / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        return world;
    }
}
