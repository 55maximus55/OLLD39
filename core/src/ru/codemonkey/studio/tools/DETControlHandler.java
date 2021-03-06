package ru.codemonkey.studio.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mark on 29.07.17.
 */

public class DETControlHandler {
    public static Vector2 keyControl(){
        Vector2 ctrl = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) ctrl.y -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) ctrl.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) ctrl.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) ctrl.x += 1;
        return vectorSinCos(ctrl);
    }
    public static float mouseControl(){
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        Vector2 ctrl = new Vector2(x,y);
        ctrl.sub(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        return ctrl.angle();
    }

    public static Vector2 vectorSinCos(Vector2 vector) {
        Vector2 result = new Vector2(vector);

        if (result.x == 0) {
            if (result.y > 0) {
                return new Vector2(0, 1);
            }
            else if (result.y < 0){
                return new Vector2(0, -1);
            }
            else if (result.y == 0) {
                return new Vector2(0, 0);
            }
        }
        else if (result.y == 0) {
            if (result.x > 0) {
                return new Vector2(1, 0);
            }
            else if (result.x < 0){
                return new Vector2(-1, 0);
            }
        }
        else {
            float angle = 0;
            angle += Math.abs(Math.toDegrees(Math.atan(result.y / result.x)));

            float xx, yy;
            if (result.x > 0) {
                xx = (float) Math.cos(Math.toRadians(angle));
            }
            else {
                xx = (float) - Math.cos(Math.toRadians(angle));
            }
            if (result.y > 0) {
                yy = (float) Math.sin(Math.toRadians(angle));
            }
            else {
                yy = (float) - Math.sin(Math.toRadians(angle));
            }
            return new Vector2(xx, yy);
        }
        return null;
    }
}
