package ru.codemonkey.studio.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Enemy implements Disposable{
    private PointLight pointLight;

    private Body body;
    private Sound sound;
    private int HP;
    private boolean isAlive;

    public Enemy(World world, RayHandler rayHandler, float x ,float y){
        isAlive = true;
    }


    @Override
    public void dispose() {

    }
}
