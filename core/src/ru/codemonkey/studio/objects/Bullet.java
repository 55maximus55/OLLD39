package ru.codemonkey.studio.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import ru.codemonkey.studio.DET;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Bullet extends Sprite {
    private Body body;
    public boolean a;

    public Bullet (TextureRegion textureRegion, GameWorld gameWorld, Vector2 pos, float speed) {
        super(textureRegion);

        a = true;

        setScale(8f / getWidth());
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set((float) (pos.x + Math.sin(Math.toRadians(DETControlHandler.mouseControl())) * 10 / DET.S),
                (float) (pos.y - Math.cos(Math.toRadians(DETControlHandler.mouseControl())) * 10 / DET.S));
        bDef.bullet = true;
        body = gameWorld.world.createBody(bDef);
        body.setLinearVelocity((float) (Math.cos(Math.toRadians(DETControlHandler.mouseControl())) * speed), (float) (Math.sin(Math.toRadians(DETControlHandler.mouseControl())) * speed));

        CircleShape shape = new CircleShape();
        shape.setRadius(4 / DET.S);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 0;
        fDef.density = 0;

        body.createFixture(fDef);
        body.setUserData("bullet");
    }

    public void update() {
        if (a) {
            setPosition(body.getPosition().x * DET.S - getWidth() / 2, body.getPosition().y * DET.S - getHeight() / 2);
        }
    }

    public Body getBody() {
        return body;
    }
}