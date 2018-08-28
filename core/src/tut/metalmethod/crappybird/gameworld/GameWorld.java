package tut.metalmethod.crappybird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import tut.metalmethod.crappybird.gameobjects.Bird;
import tut.metalmethod.crappybird.gameobjects.ScrollHandler;

// Gdx.app.log("GameWorld", "update");

/**
 * Class responsible for updating of the game
 */
public class GameWorld {

    private Bird bird;

    private ScrollHandler scrollHandler;

    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);

        //Sets the grass 66pixels below midpointY
        scrollHandler = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        bird.update(delta);
        scrollHandler.update(delta);
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }
}
