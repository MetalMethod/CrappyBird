package tut.metalmethod.crappybird.helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Texture - you can think of this as an image file. We will combine many images into one Texture file to work with.
 * <p>
 * TextureRegion - this is a rectangular portion of the Texture. See the image below. This image has multiple texture regions,
 * including the background, the grass, Flaps and the skull.
 * <p>
 * Animation - we can take multiple texture regions and create an Animation object to specify how to animate Flaps.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation birdAnimation;
    public static TextureRegion bird, birdDown, birdUp;
    public static TextureRegion skullUp, skullDown, bar;

    public static BitmapFont font, shadow;

    public static Sound dead;
    public static Sound flap;
    public static Sound coin;

    public static Preferences prefs;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));

        // filters for pixel art, nearest neighbor scaling up
        texture.setFilter(TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        // image must be flipped because default coordinate system is Y Up and this game uses U Down
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);


        TextureRegion[] birds = { birdDown, bird, birdUp };
        // Creates a new Animation in which each frame is 0.06 seconds long, using the above array.
        birdAnimation = new Animation(0.06f, birds);
        // Sets play mode to be ping pong, in which we will see a bounce.
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);


        //Loads bitmap fonts
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(0.25f, -0.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        // Sound Fx
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        //HighScore
        // Create or retrieve preferences file
        prefs = Gdx.app.getPreferences("CrappyBird");
        //provide default highsocre
        if(!prefs.contains("highscore")) {
            prefs.putInteger("highscore", 0);
        }
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();

        dead.dispose();
        flap.dispose();
        coin.dispose();
    }

    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

}
