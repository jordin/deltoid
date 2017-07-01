package me.jordin.deltoid.world;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.utils.MathUtilities;
import me.jordin.deltoid.vector.Rotation;
import me.jordin.deltoid.vector.Vec3;

/**
 * Created by Jordin on 6/20/2017.
 * Jordin is still best hacker.
 */
public class PhysicsObject {
    public static final PhysicsObject ORIGIN = new PhysicsObject();
    /**
     * The <b>previous position</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("previous_position")
    private Vec3 previousPosition;

    /**
     * The <b>current position</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("position")
    private Vec3 position;

    /**
     * The <b>previous rotation</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("previous_rotation")
    private Rotation previousRotation;

    /**
     * The <b>current rotation</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("rotation")
    private Rotation rotation;

    /**
     * The <b>velocity</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("velocity")
    private Vec3 velocity;

    /**
     * The <b>constant acceleration</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("acceleration")
    private Vec3 acceleration;

    /**
     * The <b>mass</b> of the <b>PhysicsObject</b>.
     *
     * @serial
     */
    @SerializedName("mass")
    private double mass = 0;

    /**
     * Constructs a newly allocated <b>PhysicsObject</b> object.
     *
     * @param initialPosition the initial location for the <b>PhysicsObject</b>.
     * @param initialRotation the initial rotation for the <b>PhysicsObject</b>.
     * @param velocity        the initial velocity for the <b>PhysicsObject</b>.
     * @param acceleration    the initial acceleration for the <b>PhysicsObject</b>.
     */
    public PhysicsObject(Vec3 initialPosition, Rotation initialRotation, Vec3 velocity, Vec3 acceleration) {
        this.previousPosition = this.position = initialPosition;
        this.previousRotation = this.rotation = initialRotation;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    /**
     * Constructs a newly allocated <b>PhysicsObject</b> object,
     * with an initial <b>rotation</b> of <b>0.0</b>.
     *
     * @param initialPosition the initial location for the <b>PhysicsObject</b>.
     * @param velocity        the initial velocity for the <b>PhysicsObject</b>.
     * @param acceleration    the initial acceleration for the <b>PhysicsObject</b>.
     */
    public PhysicsObject(Vec3 initialPosition, Vec3 velocity, Vec3 acceleration) {
        this(initialPosition, Rotation.ORIGIN, velocity, acceleration);
    }

    /**
     * Constructs a newly allocated <b>PhysicsObject</b> object,
     * with an initial <b>acceleration</b> of <b>0.0</b>.
     *
     * @param initialPosition the initial location for the <b>PhysicsObject</b>.
     * @param initialRotation the initial rotation for the <b>PhysicsObject</b>.
     * @param velocity        the initial velocity for the <b>PhysicsObject</b>.
     */
    public PhysicsObject(Vec3 initialPosition, Rotation initialRotation, Vec3 velocity) {
        this(initialPosition, initialRotation, velocity, Vec3.ORIGIN);
    }

    /**
     * Constructs a newly allocated <b>PhysicsObject</b> object,
     * with an initial <b>velocity</b> and <b>acceleration</b> of <b>0.0</b>.
     *
     * @param initialPosition the initial location for the <b>PhysicsObject</b>.
     * @param initialRotation the initial rotation for the <b>PhysicsObject</b>.
     */
    public PhysicsObject(Vec3 initialPosition, Rotation initialRotation) {
        this(initialPosition, initialRotation, Vec3.ORIGIN, Vec3.ORIGIN);
    }

    /**
     * Constructs a newly allocated <b>PhysicsObject</b> object,
     * with an initial <b>rotation</b>, <b>velocity</b>,
     * and <b>acceleration</b> of <b>0.0</b>.
     *
     * @param initialPosition the initial location for the <b>PhysicsObject</b>.
     */
    public PhysicsObject(Vec3 initialPosition) {
        this(initialPosition, Rotation.ORIGIN, Vec3.ORIGIN, Vec3.ORIGIN);
    }

    /**
     * Constructs a newly allocated <b>PhysicsObject</b> object,
     * with an initial <b>position</b>, <b>rotation</b>, <b>velocity</b>,
     * and <b>acceleration</b> of <b>0.0</b>.
     */
    public PhysicsObject() {
        this(Vec3.ORIGIN, Rotation.ORIGIN, Vec3.ORIGIN, Vec3.ORIGIN);
    }

    /**
     * Returns a <b>Vec3</b> of the weighted average of the
     * <b>previous</b> and <b>current</b> positions, using a weight of <b>ratio</b>.
     *
     * @param ratio the weight for the weighted average.
     * @return the interpolated <b>Vec3</b>
     */
    public Vec3 getRenderPosition(double ratio) {
        return MathUtilities.interpolate(previousPosition, position, ratio);
    }

    /**
     * Returns a <b>Rotation</b> of the weighted average of the
     * <b>previous</b> and <b>current</b> rotations, using a weight of <b>ratio</b>.
     *
     * @param ratio the weight for the weighted average.
     * @return the interpolated <b>Rotation</b>
     */
    public Rotation getRenderRotation(double ratio) {
        return MathUtilities.interpolate(previousRotation, rotation, ratio);
    }

    /**
     * Simulates the <b>PhysicsObject</b> for a time of <b>deltaTime</b>.
     * This first caches the current <b>position</b> and  <b>rotation</b> as
     * <b>previousPosition</b> and <b>previousRotation</b>, respectively.
     * <p>
     * The new <b>position</b> is then calculated with:
     * <p>
     * <b><i>d\u209C = d\u2080 + vt</i></b>.
     * <p>
     * The new <b>velocity</b> is then calculated with:
     * <p>
     * <b><i>v\u209C = v\u2080 + at</i></b>.
     *
     * @param deltaTime the elapsed time to simulate this <b>PhysicsObject</b> for.
     */
    public void update(double deltaTime) {
        previousPosition = position;
        previousRotation = rotation;
        position = position.add(velocity.scale(deltaTime));

        if (velocity != null && acceleration != null) {
            velocity = velocity.add(acceleration.scale(deltaTime));
        }
    }

    /**
     * Moves the <b>PhysicsObject</b> to the position <b>position</b>,
     * caching the previous position as <b>previousPosition</b>.
     *
     * @param position the new <b>position</b>.
     */
    public void setPosition(Vec3 position) {
        this.previousPosition = this.position;
        this.position = position;
    }

    /**
     * Rotates <b>PhysicsObject</b> to the rotation <b>rotation</b>,
     * caching the previous rotation as <b>previousRotation</b>.
     *
     * @param rotation the new <b>rotation</b>.
     */
    public void setRotation(Rotation rotation) {
        this.previousRotation = this.rotation;
        this.rotation = rotation;
    }

    /**
     * Returns the current position of this <b>PhysicsObject</b>.
     *
     * @return the current position
     */
    public Vec3 getPosition() {
        return position;
    }

    /**
     * Returns the current rotation of this <b>PhysicsObject</b>.
     *
     * @return the current rotation
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Returns the current velocity of this <b>PhysicsObject</b>.
     *
     * @return the current velocity
     */
    public Vec3 getVelocity() {
        return velocity;
    }

    /**
     * Sets the <b>velocity</b> of the <b>PhysicsObject</b> to <b>velocity</b>.
     *
     * @param velocity the new <b>velocity</b>.
     */
    public void setVelocity(Vec3 velocity) {
        this.velocity = velocity;
    }

    /**
     * Returns the current acceleration of this <b>PhysicsObject</b>.
     *
     * @return the current acceleration
     */
    public Vec3 getAcceleration() {
        return acceleration;
    }

    /**
     * Sets the <b>acceleration</b> of the <b>PhysicsObject</b> to <b>acceleration</b>.
     *
     * @param acceleration the new <b>acceleration</b>.
     */
    public void setAcceleration(Vec3 acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Returns the current mass of this <b>PhysicsObject</b>.
     *
     * @return the current mass
     */
    public double getMass() {
        return this.mass;
    }

    /**
     * Sets the <b>mass</b> of the <b>PhysicsObject</b> to <b>mass</b>.
     *
     * @param mass the new <b>mass</b>.
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * Modifies the <b>velocity</b> of the <b>PhysicsObject</b>, by
     * calculating the <b>acceleration</b> with:
     * <p>
     * <b><i>F = ma \u2263 a = F / m</i></b>.
     * <p>
     * This <b>acceleration</b> is then simulated for <b>deltaTime</b> by using:
     * <p>
     * <b><i>v\u209C = v\u2080 + at</i></b>.
     *
     * @param force     the applied force vector.
     * @param deltaTime the time elapsed since this force was applied.
     */
    public void applyForce(Vec3 force, double deltaTime) {
        this.velocity = this.velocity.add(force.scale(deltaTime / this.mass));
    }
}