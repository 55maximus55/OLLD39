package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ru.codemonkey.studio.Power;
import ru.codemonkey.studio.objects.Bullet;
import ru.codemonkey.studio.objects.Enemy;
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
    ArrayList<Enemy> mobs;


    private Texture texture;

    public GameScreen(Power game) {
        this.game = game;

        gameWorld = new GameWorld("1");
        renderer = new GameRenderer(game.batch, gameWorld.map, gameWorld.world);
        controlHandler = new DETControlHandler();
        mobs = new ArrayList<Enemy>();
        player = new Player(game.skin.getRegion("player_gun"),gameWorld.world, gameWorld.map, controlHandler, renderer.rayHandler, 1f);

        for(MapObject object : gameWorld.map.getLayers().get("enemy").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            Vector2 pos = new Vector2(rect.getX() / Power.S + rect.getWidth() / 2 / Power.S, rect.getY() / Power.S + rect.getHeight() / 2 / Power.S);
            mobs.add(new Enemy(game.skin.getRegion("soldier"), gameWorld.world, gameWorld.map, renderer.rayHandler, 1f, pos));
        }
        bullets = new ArrayList<Bullet>();
        gameWorld.world.setContactListener(new PowerContactListener(gameWorld.world, player, bullets));

        texture = new Texture("objects/bullet.png");
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
            Bullet bullet = new Bullet(texture, gameWorld.world, renderer.rayHandler, player.getPos(), controlHandler, 40);
            bullets.add(bullet);
        }
        for (int i = 0; i < mobs.size();i++){
            mobs.get(i).update(player.getPos(), mobs);
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).a) {
                bullets.get(i).update();
            } else {
                gameWorld.world.destroyBody(bullets.get(i).getBody());
                bullets.remove(i);
            }
        }

        gameWorld.update(delta);
        renderer.update(player.getPos().x * Power.S, player.getPos().y * Power.S);

        renderer.render(player, bullets, mobs);
    }


    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
