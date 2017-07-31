package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import ru.codemonkey.studio.DET;
import ru.codemonkey.studio.objects.Bullet;
import ru.codemonkey.studio.objects.GameWorld;
import ru.codemonkey.studio.objects.Player;
import ru.codemonkey.studio.objects.Povestka;
import ru.codemonkey.studio.tools.GameRenderer;
import ru.codemonkey.studio.tools.MusicPlayer;
import ru.codemonkey.studio.tools.DETContactListener;

/**
 * Created by maximus on 29.07.2017.
 */

class GameScreen implements Screen {
    private DET game;

    private GameWorld gameWorld;
    private GameRenderer renderer;
    private MusicPlayer musicPlayer;

    public GameScreen(DET game) {
        this.game = game;

        musicPlayer = new MusicPlayer(0.5f);

        gameWorld = new GameWorld(game, "1");
        renderer = new GameRenderer(game.batch, gameWorld);
        gameWorld.player = new Player(game.skin.getRegion("player_gun"), gameWorld, renderer.rayHandler);

        gameWorld.world.setContactListener(new DETContactListener(gameWorld.player, gameWorld.bullets, gameWorld.enemies, gameWorld.povestkas));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        renderer.render(gameWorld);
    }

    private void update(float delta) {
        musicPlayer.update();

        if (Gdx.input.justTouched()) {
            Bullet bullet = new Bullet(game.skin.getRegion("bullet"), gameWorld, gameWorld.player.getPos(), 40);
            gameWorld.bullets.add(bullet);
        }
        for (int i = 0; i < gameWorld.enemies.size(); i++) {
            if (!gameWorld.enemies.get(i).isAlive){
                gameWorld.enemies.get(i).die(gameWorld.world,game.skin.getRegion("player_reload"));
            }
            if (gameWorld.enemies.get(i).timerAttack <= 0 && gameWorld.enemies.get(i).isAlive && gameWorld.enemies.get(i).isDie){
                if (gameWorld.enemies.get(i).getPos().dst(gameWorld.player.getPos()) < 400 / DET.S) {
                    Povestka povestka = new Povestka(game.skin.getRegion("povestka"), gameWorld, gameWorld.enemies.get(i).getPos(), 20, gameWorld.enemies.get(i).getRotation());
                    gameWorld.povestkas.add(povestka);
                    gameWorld.enemies.get(i).timerAttack = 3f;
                }
            }
            else{
                gameWorld.enemies.get(i).timerAttack -= delta;
            }
        }

        gameWorld.update(delta);
        renderer.update(gameWorld.player.getPos().x * DET.S, gameWorld.player.getPos().y * DET.S);

        if (gameWorld.player.HP <= 0) {
            game.setScreen(new GameOverScreen(game));
            musicPlayer.dispose();
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
        gameWorld.dispose();
        renderer.dispose();
    }
}
