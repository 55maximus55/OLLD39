package ru.codemonkey.studio.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import box2dLight.RayHandler;
import ru.codemonkey.studio.DET;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Enemy extends Sprite {
    private Body body;
    private Body bodyL;

    private float timer;
    public float timerAttack;

    Enemy(TextureRegion textureRegion, GameWorld gameWorld, Vector2 pos) {
        super(textureRegion);

        timer = 0;
        timerAttack = 3f;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(pos);
        body = gameWorld.world.createBody(bDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(24 / DET.S);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 1;
        fDef.density = 0;
        body.createFixture(fDef);
        body.setUserData("enemy");

        bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(pos);
        bodyL = gameWorld.worldLight.createBody(bDef);
        shape = new CircleShape();
        shape.setRadius(24);
        fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 1;
        fDef.density = 0;
        bodyL.createFixture(fDef);
    }

    public void update(float delta, Vector2 posPlayer, ArrayList<Enemy> enemies) {
        timer -= delta;
        if (timer < 0) {
            goToPlayer(posPlayer);
            avoid(enemies);
        }

        setPosition(body.getPosition().x * DET.S - getWidth() / 2, body.getPosition().y * DET.S - getHeight() / 2);
        bodyL.setTransform(getX() + getWidth() / 2, getY() + getHeight() / 2, 0);
    }

    private void avoid(ArrayList<Enemy> enemies) {
        for(Enemy enemy : enemies){
            if (enemy != this){
                Vector2 dist = this.getPos().sub(enemy.getPos());
                if(dist.len() > 0 && dist.len() < 80 / DET.S){
                    Vector2 c =body.getLinearVelocity();
                    c.add(dist.nor());
                    body.setLinearVelocity(c);
                }
            }
        }
    }

    private void goToPlayer(Vector2 posPlayer) {
        Vector2 c = posPlayer.sub(body.getPosition());
        c = DETControlHandler.vectorSinCos(c);
        c.x *= 3.3f;
        c.y *= 3.3f;
        body.setLinearVelocity(c);
        setRotation(c.angle());
    }

    public void hurt() {
        timer = 0.1f;
        Vector2 c = body.getLinearVelocity();
        body.setLinearVelocity(c.x * -0.05f, c.y * -0.05f);
    }

    public Vector2 getPos() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }
}
