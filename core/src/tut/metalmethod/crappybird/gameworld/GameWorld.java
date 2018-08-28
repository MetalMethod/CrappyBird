package tut.metalmethod.crappybird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import tut.metalmethod.crappybird.gameobjects.Bird;

// Gdx.app.log("GameWorld", "update");

/**
 * Class responsible for updating of the game
 */
public class GameWorld {

    private Bird bird;

    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12 );
    }

    public void update(float delta) {
        bird.update(delta);
    }

    public Bird getBird() {
        return bird;
    }

}
