package tut.metalmethod.crappybird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import tut.metalmethod.crappybird.gameobjects.Bird;
import tut.metalmethod.crappybird.gameobjects.ScrollHandler;
import tut.metalmethod.crappybird.helpers.AssetLoader;

// Gdx.app.log("GameWorld", "update");

/**
 * Class responsible for updating of the game
 */
public class GameWorld {

    private Bird bird;

    private ScrollHandler scrollHandler;

    // Collision object for ground
    private Rectangle ground;

    private boolean isAlive = true;

    private int score = 0;

    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);

        //Sets the grass 66pixels below midpointY
        scrollHandler = new ScrollHandler(midPointY + 66);

        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scrollHandler.update(delta);

        if (scrollHandler.collides(bird) && bird.isAlive()) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
        }
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }
}
