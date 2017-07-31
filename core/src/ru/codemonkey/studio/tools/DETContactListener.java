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

public class DETContactListener implements ContactListener {
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Povestka> povestkas;

    public DETContactListener(Player player, ArrayList<Bullet> bullets , ArrayList<Enemy> enemies, ArrayList<Povestka> povestkas) {
        this.player = player;
        this.bullets = bullets;
        this.enemies = enemies;
        this.povestkas = povestkas;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if (contact.getFixtureA().getBody().getUserData() == "bullet" || contact.getFixtureB().getBody().getUserData() == "bullet") {
            if (contact.getFixtureA().getBody().getUserData() == "player" || contact.getFixtureB().getBody().getUserData() == "player") {
                contact.setEnabled(false);
            } else {
                if (contact.getFixtureA().getBody().getUserData() == "enemy" || contact.getFixtureB().getBody().getUserData() == "enemy") {
                    for (int i = 0; i < enemies.size(); i++) {
                        if (contact.getFixtureA().getBody() == enemies.get(i).getBody() || contact.getFixtureB().getBody() == enemies.get(i).getBody()) {
                            enemies.get(i).hurt();
                        }
                    }
                }
                if (contact.getFixtureA().getBody().getUserData() == "povestka" || contact.getFixtureB().getBody().getUserData() == "povestka") {
                    for (int i = 0; i < povestkas.size(); i++) {
                        if (contact.getFixtureA().getBody() == povestkas.get(i).getBody() || contact.getFixtureB().getBody() == povestkas.get(i).getBody()) {
                            povestkas.get(i).a = false;
                        }
                    }
                }
                for (int i = 0; i < bullets.size(); i++) {
                    if (contact.getFixtureA().getBody() == bullets.get(i).getBody() || contact.getFixtureB().getBody() == bullets.get(i).getBody()) {
                        bullets.get(i).a = false;
                    }
                }
            }
        } else if (contact.getFixtureA().getBody().getUserData() == "povestka" || contact.getFixtureB().getBody().getUserData() == "povestka") {
            if (contact.getFixtureA().getBody().getUserData() == "enemy" || contact.getFixtureB().getBody().getUserData() == "enemy") {
                contact.setEnabled(false);
            } else {
                if (contact.getFixtureA().getBody().getUserData() == "player" || contact.getFixtureB().getBody().getUserData() == "player") {
                    player.HP--;
                }
                for (int i = 0; i < povestkas.size(); i++) {
                    if (contact.getFixtureA().getBody() == povestkas.get(i).getBody() || contact.getFixtureB().getBody() == povestkas.get(i).getBody()) {
                        povestkas.get(i).a = false;
                    }
                }
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
