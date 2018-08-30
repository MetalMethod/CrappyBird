package tut.metalmethod.crappybird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import tut.metalmethod.crappybird.gameobjects.Bird;
import tut.metalmethod.crappybird.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

    private Bird bird;

    private GameWorld gameWorld;

    public InputHandler(GameWorld gameWorld) {

        this.gameWorld = gameWorld;
        bird = gameWorld.getBird();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("touched", String.valueOf(button));

        if (gameWorld.isReady()) {
            gameWorld.start();
        }

        bird.onClick();

        if (gameWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            gameWorld.restart();
        }

        return true; // true means that the touch is handled
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("touched", String.valueOf(keycode));

        if (gameWorld.isReady()) {
            gameWorld.start();
        }

        bird.onClick();

        if (gameWorld.isGameOver()) {
            // Reset all variables, go to GameState.READ
            gameWorld.restart();
        }

        return true; // true means that the touch is handled

    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
