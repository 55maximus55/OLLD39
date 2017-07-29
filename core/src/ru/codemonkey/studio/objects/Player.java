package ru.codemonkey.studio.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;
import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Player implements Disposable {
    private PointLight light;
    private float volume;
    private int HP;
    private int lampPower;
    private int overload;
    private Sound hitSound;
    private boolean isAlive;

    private DETControlHandler controlHandler;

    public Player(World world, DETControlHandler controlHandler, RayHandler rayHandler, float volume){
        this.controlHandler = controlHandler;
        this.volume = volume;
        HP = 100;
        isAlive = true;
    }



    @Override
    public void dispose() {

    }

}
