package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ru.codemonkey.studio.Power;

/**
 * Created by maximus on 29.07.2017.
 */

public class MainMenuScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;

    private Sound sound;

    public MainMenuScreen(final Power game) {

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav"));


        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        Gdx.input.setInputProcessor(stage);


        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Table tableB = new Table();
        tableB.bottom().right();
        tableB.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font24;
        labelStyle.fontColor = Color.GRAY;

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.font32;
        textButtonStyle.up = game.skin.getDrawable("btn_unpressed");
        textButtonStyle.down = game.skin.getDrawable("btn_pressed");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;

        TextButton newGame = new TextButton("New Game", textButtonStyle);
        newGame.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                sound.play();
                stage.dispose();
                game.setScreen(new GameScreen(game));
            }
        });

        table.add(newGame);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.act(delta);
        stage.setDebugAll(false);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
