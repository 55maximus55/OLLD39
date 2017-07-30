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
import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Enemy extends Sprite implements Disposable{
    private PointLight pointLight;

    private Body body;
    private Body bodyL;
    private Sound sound;
    private int HP;
    private boolean isAlive;
    private float volume;
    private float timer;

    public Enemy(TextureRegion texture, World world, World worldL, TiledMap map, RayHandler rayHandler, float volume, Vector2 pos){
        super(texture);
        this.volume = volume;
        HP = 100;
        isAlive = true;
        timer = 0;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(pos);
        body = world.createBody(bDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(24 / Power.S);

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
        bodyL = worldL.createBody(bDef);
        shape = new CircleShape();
        shape.setRadius(24);

        fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.friction = 0;
        fDef.restitution = 1;
        fDef.density = 0;

        bodyL.createFixture(fDef);
    }

    public void update(float delta,Vector2 posHero, ArrayList<Enemy> mobs){
        timer -= delta;

        if (timer < 0) {
            goToHero(posHero);
            avoid(mobs);
        }

        setPosition(body.getPosition().x * Power.S - getWidth() / 2, body.getPosition().y * Power.S - getHeight() / 2);
        bodyL.setTransform(getX() + getWidth() / 2, getY() + getHeight() / 2, 0);
    }
    public Vector2 getPos(){
        return body.getPosition();
    }

    public void goToHero(Vector2 posHero){
        Vector2 c = posHero.sub(body.getPosition());
        c = DETControlHandler.vectorSinCos(c);
        c.x *= 3.3f;
        c.y *= 3.3f;
        body.setLinearVelocity(c);
//        c.sub(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        setRotation(c.angle());
    }
    public void avoid(ArrayList<Enemy> mobs){
        for(Enemy mob : mobs){
            if (mob != this){
                Vector2 dist = this.getPos().sub(mob.getPos());
                if(dist.len() > 0 && dist.len() < 80 / Power.S){
                    Vector2 c =body.getLinearVelocity();
                    c.add(dist.nor());
                    body.setLinearVelocity(c);
                }
            }
        }
    }
    public void hurt(){
        timer = 0.1f;
//        System.out.println("C==3");
        Vector2 c = body.getLinearVelocity();
        body.setLinearVelocity(c.x * -0.05f, c.y * -0.05f);
    }





    @Override
    public void dispose() {

    }
    public Body getBody() {
        return body;
    }
}
