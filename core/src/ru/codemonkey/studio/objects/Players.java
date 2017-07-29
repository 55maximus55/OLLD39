package ru.codemonkey.studio.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

import ru.codemonkey.studio.tools.DETControlHandler;

/**
 * Created by mark on 29.07.17.
 */

public class Players implements Disposable {
    private PointLight light;
    private Body body;
    private float volume;
    private int HP;
    private int lampPower;
    private int overload;
    private Sound sound;

    private DETControlHandler controlHandler;



    @Override
    public void dispose() {

    }

}
