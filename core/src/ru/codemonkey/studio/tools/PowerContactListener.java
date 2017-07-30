package ru.codemonkey.studio.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import ru.codemonkey.studio.objects.Bullet;
import ru.codemonkey.studio.objects.Enemy;
import ru.codemonkey.studio.objects.Player;
import ru.codemonkey.studio.objects.Povestka;

/**
 * Created by maximus on 29.07.2017.
 */

public class PowerContactListener implements ContactListener {
    private World world;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> mobs;
    private ArrayList<Povestka> povestkas;

    public PowerContactListener(World world, Player player, ArrayList<Bullet> bullets , ArrayList<Enemy> mobs, ArrayList<Povestka> povestkas) {
        this.world = world;
        this.player = player;
        this.bullets = bullets;
        this.mobs = mobs;
        this.povestkas = povestkas;
    }

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getBody().getUserData().equals("enemy") && contact.getFixtureB().getBody().getUserData().equals("bullet")) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getBody() == contact.getFixtureB().getBody()) {
                    if (bullets.get(i).getBody() != null) {
                        bullets.get(i).a = false;
                    }
                }
            }
            for (int i = 0; i < mobs.size();i++){
                if (mobs.get(i).getBody() ==  contact.getFixtureA().getBody()){
                     mobs.get(i).hurt();
                }
            }
        }
        else if (contact.getFixtureB().getBody().getUserData().equals("enemy") && contact.getFixtureA().getBody().getUserData().equals("bullet")) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getBody() == contact.getFixtureA().getBody()) {
                    if (bullets.get(i).getBody() != null) {
                        bullets.get(i).a = false;
                    }
                }
            }
            for (int i = 0; i < mobs.size();i++){
                if (mobs.get(i).getBody() ==  contact.getFixtureB().getBody()){
                    if(mobs.get(i).getBody().isActive()){
                        mobs.get(i).hurt();
                    }
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
//        contact.setEnabled(false);
        if (contact.getFixtureA().getBody().getUserData().equals("player") && contact.getFixtureB().getBody().getUserData().equals("bullet")) {
            contact.setEnabled(false);
        }
        else if (contact.getFixtureB().getBody().getUserData().equals("player") && contact.getFixtureA().getBody().getUserData().equals("bullet")) {
            contact.setEnabled(false);
        }

        if (contact.getFixtureA().getBody().getUserData().equals("wall") && contact.getFixtureB().getBody().getUserData().equals("bullet")) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getBody() == contact.getFixtureB().getBody()) {
                    if (bullets.get(i).getBody() != null) {
                        bullets.get(i).a = false;
                    }
                }
            }
        }
        else if (contact.getFixtureB().getBody().getUserData().equals("wall") && contact.getFixtureA().getBody().getUserData().equals("bullet")) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getBody() == contact.getFixtureA().getBody()) {
                    if (bullets.get(i).getBody() != null) {
                        bullets.get(i).a = false;
                    }
                }
            }
        }

        if (contact.getFixtureA().getBody().getUserData() == "povestka" || contact.getFixtureB().getBody().getUserData() == "povestka") {
            if ((contact.getFixtureA().getBody().getUserData() == "enemy" || contact.getFixtureB().getBody().getUserData() == "enemy")) {
                contact.setEnabled(false);
            } else {
                for (int i = 0; i < povestkas.size(); i++) {
                    if (povestkas.get(i).getBody() == contact.getFixtureA().getBody()) {
                        if (povestkas.get(i).getBody() != null) {
                            povestkas.get(i).a = false;
                        }
                    }
                }
                for (int i = 0; i < povestkas.size(); i++) {
                    if (povestkas.get(i).getBody() == contact.getFixtureB().getBody()) {
                        if (povestkas.get(i).getBody() != null) {
                            povestkas.get(i).a = false;
                        }
                    }
                }
                if ((contact.getFixtureA().getBody().getUserData() == "player" || contact.getFixtureB().getBody().getUserData() == "player")) {
                    player.HP--;
                }
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
