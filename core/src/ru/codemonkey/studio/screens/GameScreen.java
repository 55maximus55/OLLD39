package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.objects.Bullet;
import ru.codemonkey.studio.objects.GameWorld;
import ru.codemonkey.studio.objects.Player;
import ru.codemonkey.studio.tools.DETControlHandler;
import ru.codemonkey.studio.tools.GameRenderer;
import ru.codemonkey.studio.tools.PowerContactListener;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameScreen implements Screen {
    private Power game;

    private GameWorld gameWorld;
    private GameRenderer renderer;
    private DETControlHandler controlHandler;
    private Player player;
    ArrayList<Bullet> bullets;

    private Texture texture;

    public GameScreen(Power game) {
        this.game = game;

        gameWorld = new GameWorld("1");
        renderer = new GameRenderer(game.batch, gameWorld.map, gameWorld.world);
        controlHandler = new DETControlHandler();

        player = new Player(game.skin.getRegion("manOld_gun"),gameWorld.world, gameWorld.map, controlHandler, renderer.rayHandler, 1f);
        gameWorld.world.setContactListener(new PowerContactListener(player));
        bullets = new ArrayList<Bullet>();

        texture = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update();
        if (Gdx.input.justTouched()) {
            Bullet bullet = new Bullet(texture, gameWorld.world, renderer.rayHandler, player.getPos(), controlHandler, 20);
            bullets.add(bullet);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }

        gameWorld.update(delta);
        renderer.update(player.getPos().x * Power.S, player.getPos().y * Power.S);

        renderer.render(player, bullets);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
