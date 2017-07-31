package ru.codemonkey.studio.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import ru.codemonkey.studio.DET;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 30.07.17.
 */

public class Povestka extends Sprite {
    private Body body;
    public boolean a;

    public Povestka(TextureRegion textureRegion, GameWorld gameWorld, Vector2 pos, float speed, float rotation) {
        super(textureRegion);

        a = true;

        setScale(32f / getWidth());
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(pos);
        bDef.bullet = true;
        body = gameWorld.world.createBody(bDef);
        body.setLinearVelocity((float) (Math.cos(Math.toRadians(rotation)) * speed), (float) (Math.sin(Math.toRadians(rotation)) * speed));

        CircleShape shape = new CircleShape();
        shape.setRadius(16f / DET.S);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 0;
        fDef.density = 0;

        body.createFixture(fDef);
        body.setUserData("povestka");
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
