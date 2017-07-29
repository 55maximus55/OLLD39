package ru.codemonkey.studio.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;
import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Bullet extends Sprite implements Disposable {
    private PointLight light;
    private Body body;

    public Bullet(Texture texture, World world, RayHandler rayHandler, Vector2 pos, DETControlHandler controlHandler, float speed){
        super(texture);
        setScale(4f / 256f);
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set((float) (pos.x + Math.sin(Math.toRadians(controlHandler.mouseControl())) * 5 / Power.S),
                (float) (pos.y - Math.cos(Math.toRadians(controlHandler.mouseControl())) * 5 / Power.S));
        bDef.bullet = true;
        body = world.createBody(bDef);
        body.setLinearVelocity((float) (Math.cos(Math.toRadians(controlHandler.mouseControl())) * speed), (float) (Math.sin(Math.toRadians(controlHandler.mouseControl())) * speed));

        CircleShape shape = new CircleShape();
        shape.setRadius(2 / Power.S);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 0;
        fDef.density = 0;

        body.createFixture(fDef);
    }

    public void update() {
        setPosition(body.getPosition().x * Power.S - 128, body.getPosition().y * Power.S - 128);
    }

    @Override
    public void dispose() {

    }
}
