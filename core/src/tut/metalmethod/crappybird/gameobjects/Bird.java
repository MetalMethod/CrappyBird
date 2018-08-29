package tut.metalmethod.crappybird.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import tut.metalmethod.crappybird.helpers.AssetLoader;

/**
 * Bird Animation:
 * Rotation is controlled using the Y velocity.
 * The bird accelerates downwards due to gravity (meaning that the velocity increases).
 * When our velocity is negative (meaning that the bird is moving upwards), the bird will start rotating counterclockwise.
 * When our velocity is higher than some positive value, the bird will start to rotate clockwise
 * but don't start rotating until the Bird starts speeding up a bit.
 */
public class Bird {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    /**
     * positive change in rotation is a clockwise rotation and that a negative change in rotation is a counterclockwise rotation.
     */
    private float rotation;

    private int width;
    private int height;

    /**
     * Object for collision detection
     */
    private Circle boundingCircle;

    private boolean isAlive;


    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);

        boundingCircle = new Circle();

        isAlive = true;
    }

    /**
     * Called when GameWorld updates
     *
     * @param delta
     */
    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));

        boundingCircle.set(position.x + 9, position.y +6, 6.5f);


        // Rotate counterclockwise
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }

        // Rotate clockwise
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    /**
     * Called when the sceen is clicked or touched
     */
    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play();
            velocity.y = -140;
        }
    }

    /**
     * Decide when the bird should begin rotating downwards.
     *
     * @return
     */
    public boolean isFalling() {
        return velocity.y > 110;
    }

    /**
     * Determine when the bird should stop animating.
     *
     * @return
     */
    public boolean shouldNotFlap() {
        return velocity.y > 70 || !isAlive;
    }

    public void die(){
        isAlive = false;
        velocity.y = 0;
    }

    /**
     * The bird to stop accelerating downwards once it is dead.
     */
    public void decelerate(){

        acceleration.y = 0;
    }


    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    /**
     *  Gets the collision object
     * @return
     */
    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
