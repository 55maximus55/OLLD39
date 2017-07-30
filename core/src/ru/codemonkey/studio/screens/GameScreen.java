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
import ru.codemonkey.studio.objects.Povestka;
import ru.codemonkey.studio.tools.DETControlHandler;
import ru.codemonkey.studio.tools.GameRenderer;
import ru.codemonkey.studio.tools.MusicPlayer;
import ru.codemonkey.studio.tools.PowerContactListener;

/**
 * Created by maximus on 29.07.2017.
 */

public class GameScreen implements Screen {
    private Power game;

    private GameWorld gameWorld;
    private GameRenderer renderer;
    private DETControlHandler controlHandler;
    public MusicPlayer musicPlayer;
    private Player player;
    ArrayList<Bullet> bullets;
    ArrayList<Enemy> mobs;
    ArrayList<Povestka> povestkas;

    private Texture texture;

    public GameScreen(Power game) {
        this.game = game;

        gameWorld = new GameWorld("1");
        renderer = new GameRenderer(game.batch, gameWorld.map, gameWorld.worldLight);
        controlHandler = new DETControlHandler();
        mobs = new ArrayList<Enemy>();
        player = new Player(game.skin.getRegion("player_gun"),gameWorld.world, gameWorld.map, controlHandler, renderer.rayHandler, 1f);

        for(MapObject object : gameWorld.map.getLayers().get("enemy").getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            Vector2 pos = new Vector2(rect.getX() / Power.S + rect.getWidth() / 2 / Power.S, rect.getY() / Power.S + rect.getHeight() / 2 / Power.S);
            mobs.add(new Enemy(game.skin.getRegion("soldier"), gameWorld.world, gameWorld.worldLight, gameWorld.map, renderer.rayHandler, 1f, pos));
        }
        bullets = new ArrayList<Bullet>();
        povestkas = new ArrayList<Povestka>();
        gameWorld.world.setContactListener(new PowerContactListener(gameWorld.world, player, bullets,mobs, povestkas));

        musicPlayer = new MusicPlayer(0.5f);

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

        for (int i = 0; i < mobs.size(); i++) {
            if (mobs.get(i).timerAttack <= 0){
                if (mobs.get(i).getPos().dst(player.getPos()) < 400 / Power.S) {
                    Povestka povestka = new Povestka(game.skin.getRegion("povestka"), gameWorld.world, mobs.get(i).getPos(), controlHandler, 20, mobs.get(i).getRotation());
                    povestkas.add(povestka);
                    mobs.get(i).timerAttack = 3f;
                }
            }
            else{
                mobs.get(i).timerAttack -= delta;
            }
        }
        musicPlayer.update();
        player.update();
        if (Gdx.input.justTouched()) {
            Bullet bullet = new Bullet(texture, gameWorld.world, renderer.rayHandler, player.getPos(), controlHandler, 40);
            bullets.add(bullet);
        }
        for (int i = 0; i < mobs.size();i++){
            mobs.get(i).update(delta,player.getPos(), mobs);

        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).a) {
                bullets.get(i).update();
            } else {
                gameWorld.world.destroyBody(bullets.get(i).getBody());
                bullets.remove(i);
            }
        }
        for (int i = 0; i < povestkas.size(); i++) {
            if (povestkas.get(i).a) {
                povestkas.get(i).update();
            } else {
                gameWorld.world.destroyBody(povestkas.get(i).getBody());
                povestkas.remove(i);
            }
        }

        gameWorld.update(delta);
        renderer.update(player.getPos().x * Power.S, player.getPos().y * Power.S);

        renderer.render(player, bullets, mobs, povestkas);

        if (player.HP < 0) {
            game.setScreen(new GameOverScreen(game));
        }
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
