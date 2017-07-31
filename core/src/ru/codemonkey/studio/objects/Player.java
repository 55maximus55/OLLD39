package ru.codemonkey.studio.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import ru.codemonkey.studio.DET;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Player extends Sprite implements Disposable {
    private Body body;

    private ConeLight light;
    private PointLight light1;

    public int HP;

    public Player(TextureRegion textureRegion, GameWorld gameWorld, RayHandler rayHandler) {
        super(textureRegion);

        HP = 3;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        for(MapObject object : gameWorld.map.getLayers().get("player").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bDef.position.set(rect.getX() / DET.S + rect.getWidth() / 2 / DET.S, rect.getY() / DET.S + rect.getHeight() / 2 / DET.S);
        }
        body = gameWorld.world.createBody(bDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(16 / DET.S);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 1;
        fDef.density = 0;

        body.createFixture(fDef);
        body.setUserData("player");

        light = new ConeLight(rayHandler, 500, Color.BLACK, 500, 0, 0, 0, 35);
        light.setSoftnessLength(64);
        light1 = new PointLight(rayHandler, 500, Color.BLACK, 60, 0, 0);
    }

    public void update() {
        friction();
        control();

        setPosition(body.getPosition().x * DET.S - getWidth() / 2, body.getPosition().y * DET.S - getHeight() / 2);
        setRotation(DETControlHandler.mouseControl());

        light.setDirection(DETControlHandler.mouseControl());
        light.setPosition(getPos().x * DET.S, getPos().y * DET.S);

        light1.setPosition(getPos().x * DET.S, getPos().y * DET.S);
    }

    private void friction() {
        float s = 3f;
        Vector2 velocity = new Vector2();
        velocity.set(body.getLinearVelocity());
        Vector2 v = new Vector2(velocity);
        velocity.sub(DETControlHandler.vectorSinCos(velocity).x * s, DETControlHandler.vectorSinCos(velocity).y * s);
        if ((v.x > 0 && velocity.x < 0) || (v.x < 0 && velocity.x > 0) || (v.y > 0 && velocity.y < 0) || (v.y < 0 && velocity.y > 0)) {
            velocity.set(0, 0);
        }
        body.setLinearVelocity(velocity);
    }

    private void control(){
        Vector2 c = DETControlHandler.keyControl();
        if(c.x * c.x + c.y * c.y > 1){
            c = DETControlHandler.vectorSinCos(c);
        }
        c.x *= 3.3f;
        c.y *= 3.3f;
        body.applyLinearImpulse(c, body.getWorldCenter(), true);
    }

    public Vector2 getPos() {
        return body.getPosition();
    }

    @Override
    public void dispose() {
        light.dispose();
        light1.dispose();
    }
}
