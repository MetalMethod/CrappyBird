package tut.metalmethod.crappybird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import tut.metalmethod.crappybird.gameworld.GameRenderer;
import tut.metalmethod.crappybird.gameworld.GameWorld;

/**
 * Class for the Game Screen, uses helper classes to do rendering and updating tasks
 */
public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private GameRenderer gameRenderer;

    public GameScreen() {
        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld);

        Gdx.app.log("GameScreen", "Attached");
    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        gameRenderer.render();
    }


    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {

    }
}
