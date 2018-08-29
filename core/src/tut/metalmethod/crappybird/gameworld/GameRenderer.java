package tut.metalmethod.crappybird.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import tut.metalmethod.crappybird.gameobjects.Bird;
import tut.metalmethod.crappybird.gameobjects.Grass;
import tut.metalmethod.crappybird.gameobjects.Pipe;
import tut.metalmethod.crappybird.gameobjects.ScrollHandler;
import tut.metalmethod.crappybird.helpers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Class responsible for rendering of the game
 */
public class GameRenderer {

    private GameWorld gameWorld;
    private OrthographicCamera camera;

    /**
     * Responsible for rendering shapes
     */
    private ShapeRenderer shapeRenderer;

    /**
     * Responsible for rendering images with indices x, y, width and height
     */
    private SpriteBatch batcher;

    /**
     * for calculating the window size
     */
    private int midPointY;
    private int gameHeight;

    /**
     * Game Objects
     */
    private Bird bird;
    private TextureRegion bg, grass;
    private Animation birdAnimation;
    private TextureRegion birdMid, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

    private ScrollHandler scrollHandler;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    /**
     *  Constructor of Game Renderer
     * @param gameWorld
     * @param gameHeight
     * @param midPointY
     */
    public GameRenderer(GameWorld gameWorld, int gameHeight, int midPointY) {
        this.gameWorld = gameWorld;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, 204);

        batcher = new SpriteBatch();
        //Attatch Batch to camera
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects(){
        bird = gameWorld.getBird();
        scrollHandler = gameWorld.getScrollHandler();

        frontGrass = scrollHandler.getFrontGrass();
        backGrass = scrollHandler.getBackGrass();
        pipe1 = scrollHandler.getPipe1();
        pipe2 = scrollHandler.getPipe2();
        pipe3 = scrollHandler.getPipe3();
    }

    private void initAssets(){
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    private void drawGrass() {
        // Draw the grass
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.

        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    /**
     *  runTime is reponsible to determine which frame the bird animation should display.
     *  The Animation object will use this value (and the frame duration) to determine which TextureRegion to display.
     * @param runTime
     */
    public void render(float runTime) {

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // End ShapeRenderer
        shapeRenderer.end();

        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        // Draw Grass
        drawGrass();

        // Draw Pipes
        drawPipes();

        // Draw Skulls (requires transparency)
        batcher.enableBlending();
        drawSkulls();

        if (bird.shouldNotFlap()) {
            batcher.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(),
                    1, 1,
                    bird.getRotation());

        } else {
            // Draw bird at its coordinates. Retrieve the Animation object from
            // AssetLoader
            // Pass in the runTime variable to get the current frame, casted to Texture Region
            batcher.draw((TextureRegion) AssetLoader.birdAnimation.getKeyFrame(runTime),
                    bird.getX(),
                    bird.getY(),
                    bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f,
                    bird.getWidth(),
                    bird.getHeight(),
                    1, 1,
                    bird.getRotation());
        }

        // End SpriteBatch
        batcher.end();
    }

    private void drawDebugCollisionObjects(){
        /**
         * temp code for view collision logic in  character
         */
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(bird.getBoundingCircle().x, bird.getBoundingCircle().y, bird.getBoundingCircle().radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(bird.getBoundingCircle().x,
                bird.getBoundingCircle().y, bird.getBoundingCircle().radius);


        // Bar up for pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getBarUp().x, pipe1.getBarUp().y,
                pipe1.getBarUp().width, pipe1.getBarUp().height);
        shapeRenderer.rect(pipe2.getBarUp().x, pipe2.getBarUp().y,
                pipe2.getBarUp().width, pipe2.getBarUp().height);
        shapeRenderer.rect(pipe3.getBarUp().x, pipe3.getBarUp().y,
                pipe3.getBarUp().width, pipe3.getBarUp().height);

        // Bar down for pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getBarDown().x, pipe1.getBarDown().y,
                pipe1.getBarDown().width, pipe1.getBarDown().height);
        shapeRenderer.rect(pipe2.getBarDown().x, pipe2.getBarDown().y,
                pipe2.getBarDown().width, pipe2.getBarDown().height);
        shapeRenderer.rect(pipe3.getBarDown().x, pipe3.getBarDown().y,
                pipe3.getBarDown().width, pipe3.getBarDown().height);

        // Skull up for Pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getSkullUp().x, pipe1.getSkullUp().y,
                pipe1.getSkullUp().width, pipe1.getSkullUp().height);
        shapeRenderer.rect(pipe2.getSkullUp().x, pipe2.getSkullUp().y,
                pipe2.getSkullUp().width, pipe2.getSkullUp().height);
        shapeRenderer.rect(pipe3.getSkullUp().x, pipe3.getSkullUp().y,
                pipe3.getSkullUp().width, pipe3.getSkullUp().height);

        // Skull down for Pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getSkullDown().x, pipe1.getSkullDown().y,
                pipe1.getSkullDown().width, pipe1.getSkullDown().height);
        shapeRenderer.rect(pipe2.getSkullDown().x, pipe2.getSkullDown().y,
                pipe2.getSkullDown().width, pipe2.getSkullDown().height);
        shapeRenderer.rect(pipe3.getSkullDown().x, pipe3.getSkullDown().y,
                pipe3.getSkullDown().width, pipe3.getSkullDown().height);

        shapeRenderer.end();
    }
}
