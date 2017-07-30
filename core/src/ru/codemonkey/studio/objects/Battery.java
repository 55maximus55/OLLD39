package ru.codemonkey.studio.objects;

import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Battery implements Disposable {
    private PointLight light;
    private Body body;
    private boolean isAlive;

    public Battery(World world, RayHandler rayHandler, float x , float y){
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x,y);
        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        fDef.friction = 0;
        fDef.restitution = 1;
        fDef.density = 0;
        
        body.createFixture(fDef);
        isAlive = true;
    }


    @Override
    public void dispose() {

    }
}
