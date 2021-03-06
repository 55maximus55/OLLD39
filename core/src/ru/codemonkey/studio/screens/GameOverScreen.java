package ru.codemonkey.studio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ru.codemonkey.studio.DET;

/**
 * Created by 1 on 30.07.2017.
 */

class GameOverScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;

    private Sound sound;

    GameOverScreen(final DET game) {
        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        Gdx.input.setInputProcessor(stage);

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav"));

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.font32;
        textButtonStyle.up = game.skin.getDrawable("btn_unpressed");
        textButtonStyle.down = game.skin.getDrawable("btn_pressed");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.font24;
        labelStyle.fontColor = Color.GRAY;

        Label label = new Label("Game Over", labelStyle);

        TextButton newGame = new TextButton("Back to Main Menu", textButtonStyle);
        newGame.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                sound.play();
                stage.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(label);
        table.row();
        table.add(newGame);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
        sound.dispose();
    }
}
