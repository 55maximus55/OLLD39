package ru.codemonkey.studio.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mark on 29.07.17.
 */

public class Players implements Disposable {
    private PointLight light;
    private Body body;
    private Sound sound;

    



    @Override
    public void dispose() {

    }

}
