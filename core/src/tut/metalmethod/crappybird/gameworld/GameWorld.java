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

    //Current game state
    private GameState currentState;

    public int midPointY;


    public GameWorld(int midPointY) {
        this.bird = new Bird(33, midPointY - 5, 17, 12);

        //Sets the grass 66pixels below midpointY
        scrollHandler = new ScrollHandler(this, midPointY + 66);

        ground = new Rectangle(0, midPointY + 66, 136, 11);

        currentState = GameState.READY;

        this.midPointY = midPointY;
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    public void updateRunning(float delta) {
        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scrollHandler.update(delta);

        // Character hits obstacles
        if (scrollHandler.collides(bird) && bird.isAlive()) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        // Character dies when hit ground
        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
        }
    }

    private void updateReady(float delta) {

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

    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public void start(){
        currentState = GameState.RUNNING;
    }

    public void restart(){
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scrollHandler.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }
}
